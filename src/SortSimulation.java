import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SortSimulation extends Application {
    private int[][] arrayOfArrays;
    private int arraySize;
    private TestThread[] arrayOfThreads = new TestThread[3];
    private ActionAndTime[] arrayOfComparisionElements = new ActionAndTime[3];

    public static void main(String[] args) throws IOException {
        launch();
    }

    public void start(Stage primaryStage) {
        GridPane rootNode = new GridPane();
        //Устанавливаем размеры отступов
        rootNode.setPadding(new Insets(10, 5, 10, 5));
        //Устанавливаем горизонтальный зазор между плитками
        rootNode.setHgap(10);
        //Устанавливаем вертикальный зазор
        rootNode.setVgap(10);
        //Устанавливаем ширину компоновочного элемента
        rootNode.getColumnConstraints().add(new ColumnConstraints(200)); // column 0 is 100 wide
        primaryStage.setTitle("Сравнение сортировок");
        Scene scene = new Scene(rootNode, 400, 300);


        Label sortHint = new Label("Выберите тип сортировки: ");
        ChoiceBox<String> sortChoiceBox = new ChoiceBox<>();
        Label chosenTypesOfSorts = new Label("Выбранные типы сортировки");
        Label chosenTypesOfSortsPart2 = new Label("----\t----\t----");
        Label numberOfElementsText = new Label("Кол-во элементов в массиве: ");
        TextField numberOfElementsInput = new TextField();
        Button startSorting = new Button("StartTest");
        Label[] comparisionResults = new Label[6];
        for (int i = 0; i < 6; i++) {
            comparisionResults[i] = new Label("");
        }

        sortChoiceBox.getItems().addAll("Bubble sort", "Merge sort", "Quick sort");
        sortChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> v, String nv, String ov) {
                String[] tmpTypes;
                String tmpString;
                boolean isExisting = false;

                if (chosenTypesOfSortsPart2.getText().equals("----\t----\t----")) {
                    chosenTypesOfSortsPart2.setText(sortChoiceBox.getValue().split(" ")[0]);
                    return;
                }

                tmpTypes = chosenTypesOfSortsPart2.getText().split("\t");

                if (tmpTypes.length == 3) return;

                for (int i = 0; i < tmpTypes.length; i++) {
                    if (sortChoiceBox.getValue().split(" ")[0].equals(tmpTypes[i])) {
                        isExisting = true;
                        break;
                    }
                }

                if (!isExisting) {
                    tmpString = chosenTypesOfSortsPart2.getText();
                    tmpString += "\t" + sortChoiceBox.getValue().split(" ")[0];
                    chosenTypesOfSortsPart2.setText(tmpString);
                }
            }
        });

        startSorting.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                arraySize = Integer.parseInt(numberOfElementsInput.getText());
                String[] tmpTypes = chosenTypesOfSortsPart2.getText().split("\t");
                for (int i = 0; i < tmpTypes.length; i++) {
                    arrayOfComparisionElements[i] = new ActionAndTime();
                    arrayOfThreads[i] = new TestThread(tmpTypes[i], arraySize, arrayOfComparisionElements[i]);
                }
                try {
                    for (int i = 0; i < tmpTypes.length * 2; i += 2) {
                        arrayOfThreads[i / 2].getThread().join();
                        comparisionResults[i].setText(arrayOfThreads[i / 2].getThread().getName() +
                                " Время: " + arrayOfComparisionElements[i / 2].getTimeForSort());
                        comparisionResults[i + 1].setText("Кол-во действий: " +
                                arrayOfComparisionElements[i / 2].getNumberOfActions());
                    }
                } catch (InterruptedException exc) {
                    System.out.println(exc);
                }
            }
        });

        rootNode.add(sortHint, 0, 0);
        rootNode.add(sortChoiceBox, 1, 0);
        rootNode.add(numberOfElementsText, 0, 1);
        rootNode.add(numberOfElementsInput, 1, 1);
        rootNode.add(chosenTypesOfSorts, 0, 2);
        rootNode.add(chosenTypesOfSortsPart2, 1, 2);
        rootNode.add(startSorting, 0, 3);
        rootNode.add(comparisionResults[0], 0, 4);
        rootNode.add(comparisionResults[1], 1, 4);
        rootNode.add(comparisionResults[2], 0, 5);
        rootNode.add(comparisionResults[3], 1, 5);
        rootNode.add(comparisionResults[4], 0, 6);
        rootNode.add(comparisionResults[5], 1, 6);

        primaryStage.setScene(scene);

        primaryStage.show();
    }
}
