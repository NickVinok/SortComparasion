import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import  javafx.event.*;

import java.beans.EventHandler;
import java.io.IOException;

public class SortSimulation extends Application{
    private int [][]arrayOfArrays = new int[10][0];
    private int arraySize;
    public static void main(String[] args) throws IOException{
        /*
        int [][]arrayOfArrays = new int[3][10000000];
        for(int i=0;i<3;i++){
            for(int j = 0;j<10000000;j++){
                arrayOfArrays[i][j] = (int)(Math.random() * 10000000 - 5000000);
            }
        }
        TestThread quick = new TestThread("Quick",arrayOfArrays[0]);
        TestThread bubble = new TestThread("Bubble",arrayOfArrays[1]);
        TestThread merge = new TestThread("Merge",arrayOfArrays[2]);
        */
        launch(args);
    }

    public void start(Stage primaryStage){
        GridPane rootNode = new GridPane();
        //Устанавливаем размеры отступов
        rootNode.setPadding(new Insets(10, 10, 10, 10) );
        //Устанавливаем горизонтальный зазор между плитками
        rootNode.setHgap(10);
        //Устанавливаем вертикальный зазор
        rootNode.setVgap(10);
        //
        rootNode.getColumnConstraints().add(new ColumnConstraints(200)); // column 0 is 100 wide
        primaryStage.setTitle("Сравнение сортировок");
        Scene scene = new Scene(rootNode, 400, 300);


        Label sortHint = new Label("Выберите тип сортировки: ");
        ChoiceBox<String> sortChoiceBox = new ChoiceBox<>();
        Label chosenTypesOfSorts = new Label("Выбранные типы сортировки");
        Label chosenTypesOfSortsPart2 = new Label("Слияние ");
        Label numberOfElementsText = new Label("Кол-во элементов в массиве: ");
        TextField numberOfElementsInput = new TextField();
       /* numberOfElementsInput.getOnAction((event)-> {
            for(int i = 0; i<10;i++) {
                arraySize[i] = new int[Integer.parseInt(numberOfElementsInput.getText())];
            }
        });*/
        Button startSorting = new Button("StartTest");
        Label time = new Label("Время сортировки");

        sortChoiceBox.getItems().addAll("Сортировка пузырьком", "Сортировка слиянием", "Быстрая сортировка");




        rootNode.add(sortHint,0,0);
        rootNode.add(sortChoiceBox,1,0);
        rootNode.add(numberOfElementsText, 0, 1);
        rootNode.add(numberOfElementsInput, 1,1);
        rootNode.add(chosenTypesOfSorts,0,2);
        rootNode.add(chosenTypesOfSortsPart2,0,3);
        rootNode.add(startSorting,1,3);
        rootNode.add(time, 0,4);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

}
