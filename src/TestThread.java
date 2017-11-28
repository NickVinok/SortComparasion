import Sorts.*;

import java.util.Date;

public class TestThread implements Runnable{
    private Thread thread;
    private int[] array;
    private ActionAndTime actionAndTime;


    TestThread(String name,int arraySize, ActionAndTime actionAndTime){
        thread = new Thread(this,name);
        this.actionAndTime = actionAndTime;
        array = new int[arraySize];
        for(int i =0;i < arraySize;i++){
            array[i] = (int)(Math.random()*1000000) - 500000;
        }
        thread.start();
    }

    public void run(){
        //Получает имя потока и в зависимости от него запускает
        //Нужную сортировку

        switch(thread.getName()){
            //Сортировка пузырьком
            case "Bubble": {
                BubbleSort bs = new BubbleSort();
                //Засекает время начала сортировки
                //Вообще стоит поискать код для нормального таймера
                //Ибо это похоже на ебанный костыль
                Date dateBefore = new Date();
                bs.sort(array);
                //Засекает время после сортировки
                Date dateAfter = new Date();
                actionAndTime.setTimeForSort(String.valueOf((double) (dateAfter.getTime() -
                        dateBefore.getTime()) / 1000));
                actionAndTime.setNumberOfActions(String.valueOf(bs.getNumberOfActions()));
                break;
            }
            //Вторая вариация сортировки пузырьком
            case "Bubble2": {
                BubbleSort2 bs2 = new BubbleSort2();
                Date dateBefore = new Date();
                bs2.sort(array);
                System.out.println();
                Date dateAfter = new Date();

                actionAndTime.setTimeForSort(String.valueOf((double) (dateAfter.getTime() -
                        dateBefore.getTime()) / 1000));
                actionAndTime.setNumberOfActions(String.valueOf(bs2.getNumberOfActions()));
                break;
            }
            case "Selection": {
                SelectionSort ss = new SelectionSort();
                //Засекает время начала сортировки
                //Вообще стоит поискать код для нормального таймера
                //Ибо это похоже на ебанный костыль
                Date dateBefore = new Date();
                ss.sort(array);
                //Засекает время после сортировки
                Date dateAfter = new Date();
                actionAndTime.setTimeForSort(String.valueOf((double) (dateAfter.getTime() -
                        dateBefore.getTime()) / 1000));
                actionAndTime.setNumberOfActions(String.valueOf(ss.getNumberOfActions()));
                break;
            }
            //Сортировка слиянием
            case "Merge": {
                MergeSort ms = new MergeSort();
                Date dateBefore = new Date();
                ms.sort(array);
                System.out.println();
                Date dateAfter = new Date();

                actionAndTime.setTimeForSort(String.valueOf((double) (dateAfter.getTime() -
                        dateBefore.getTime()) / 1000));
                actionAndTime.setNumberOfActions(String.valueOf(ms.getNumberOfActions()));
                break;
            }
            //"Быстрая" сортировка
            case "Quick":{
                QuickSort qs = new QuickSort();
                Date dateBefore = new Date();
                qs.sort(array);
                System.out.println();
                Date dateAfter = new Date();

                actionAndTime.setTimeForSort(String.valueOf((double) (dateAfter.getTime() -
                        dateBefore.getTime()) / 1000));
                actionAndTime.setNumberOfActions(String.valueOf(qs.getNumberOfActions()));
                break;
            }
        }
    }

    public Thread getThread() {
        return thread;
    }
}
