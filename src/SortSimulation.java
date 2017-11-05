import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import  javafx.event.*;

import java.beans.EventHandler;
import java.io.IOException;

public class SortSimulation extends Application{
    private int [][]arrayOfArrays = new int[10][];
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
        primaryStage.setTitle("Сравнение сортировок");
        Scene scene = new Scene(rootNode, 350, 300);


        Label sortHint = new Label("Выберите тип сортировки: ");
        ChoiceBox<String> sortChoiceBox = new ChoiceBox<>();
        Label chosenTypesOfSorts = new Label("Выбранные типы сортировки");
        Label chosenTypesOfSortsPart2 = new Label("");
        Label numberOfElementsText = new Label("Кол-во элементов в массиве: ");
        TextField numberOfElementsInput = new TextField();
        Button startSorting = new Button("StartTest");
        Label time = new Label("");
        sortChoiceBox.getItems().addAll("Сортировка пузырьком", "Сортировка слиянием", "Быстрая сортировка");




        rootNode.add(sortHint,0,0);
        rootNode.add(sortChoiceBox,1,0);
        rootNode.add(numberOfElementsText, 0, 1);
        rootNode.add(numberOfElementsInput, 1,1);
        //rootNode.add(chosenTypesOfSorts,0,1);
        //rootNode.add(chosenTypesOfSortsPart2,0,2);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

}
