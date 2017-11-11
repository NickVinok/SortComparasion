import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SortSimulation extends Application {
    private int[][] arrayOfArrays;
    private int arraySize = -1;
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


        Alert typeSelectionAlert = new Alert(Alert.AlertType.ERROR, "", ButtonType.OK);
        typeSelectionAlert.setTitle("Не выбран тип сортировки");
        typeSelectionAlert.setHeaderText("Пожалуйста, выберите тип сортировки");
        typeSelectionAlert.setContentText("Должна быть выбрана хотя бы одна сортировка");

        Alert noArraySizeInputAlert = new Alert(Alert.AlertType.ERROR, "", ButtonType.OK);
        noArraySizeInputAlert.setTitle("Не введён размер массива");
        noArraySizeInputAlert.setHeaderText("Пожалуйста, введите размер массива");
        noArraySizeInputAlert.setContentText("У массива должен быть размер, который должен быть представлен в виде целого положительного числа");

        Alert wrongArraySizeInputAlert = new Alert(Alert.AlertType.ERROR, "", ButtonType.OK);
        wrongArraySizeInputAlert.setTitle("Введено не число");
        wrongArraySizeInputAlert.setHeaderText("Пожалуйста, введите положительное число");
        wrongArraySizeInputAlert.setContentText("Размер массива должен быть представлен в виде целого положительного числа");

        Alert negativeArraySizeInputAlert = new Alert(Alert.AlertType.ERROR, "", ButtonType.OK);
        negativeArraySizeInputAlert.setTitle("Введено отрицательное число");
        negativeArraySizeInputAlert.setHeaderText("Пожалуйста, введите положительное число");
        negativeArraySizeInputAlert.setContentText("Размер массива не может быть отрицательным");

        Alert bubbleSortArraySize = new Alert(Alert.AlertType.INFORMATION, "", ButtonType.OK);
        bubbleSortArraySize.setTitle("Внимание");
        bubbleSortArraySize.setHeaderText("Пузырьковая сортировка будет работать с массивом в 100,000 элементов");
        bubbleSortArraySize.setContentText("Пузырьковая сортировка не может эффективно работать с массивами, в которых больше 100,000 элементов");


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
                String[] tmpTypes = chosenTypesOfSortsPart2.getText().split("\t");

                if (tmpTypes[0].equals("----")) {
                    typeSelectionAlert.showAndWait();
                } else {

                    try {
                        arraySize = Integer.parseInt(numberOfElementsInput.getText());
                        if (arraySize < 1) {
                            negativeArraySizeInputAlert.showAndWait();
                        }
                    } catch (NumberFormatException exc) {
                        if (numberOfElementsInput.getText().equals("")) {
                            noArraySizeInputAlert.showAndWait();
                        } else {
                            wrongArraySizeInputAlert.showAndWait();
                        }
                    }

                    if (arraySize > 0) {
                        for (int i = 0; i < tmpTypes.length; i++) {
                            arrayOfComparisionElements[i] = new ActionAndTime();
                            if (tmpTypes[i].equals("Bubble") && arraySize > 100000) {
                                bubbleSortArraySize.showAndWait();
                                bubbleSortArraySize.getOnCloseRequest();
                                arrayOfThreads[i] = new TestThread(tmpTypes[i], 100000, arrayOfComparisionElements[i]);
                            } else {
                                arrayOfThreads[i] = new TestThread(tmpTypes[i], arraySize, arrayOfComparisionElements[i]);
                            }
                        }

                        for (int i=0; i < tmpTypes.length * 2; i += 2) {
                            try {
                                arrayOfThreads[i/2].getThread().join();
                                if (!arrayOfThreads[i / 2].getThread().isAlive()) {
                                    comparisionResults[i].setText(arrayOfThreads[i / 2].getThread().getName() +
                                            " Время: " + arrayOfComparisionElements[i / 2].getTimeForSort());
                                    comparisionResults[i + 1].setText("Кол-во действий: " +
                                            arrayOfComparisionElements[i / 2].getNumberOfActions());
                                } else {
                                    comparisionResults[i].setText("Производится сортировка");
                                }
                            } catch(InterruptedException exc){
                                System.out.println(exc);
                            }
                        }
                    }
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
