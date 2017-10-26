import Sorts.BubbleSort;
import Sorts.QuickSort;
import Sorts.MergeSort;

import java.util.Date;

public class TestThread implements Runnable{
    private Thread thread;
    private int[] array;

    TestThread(String name, int[] arrayToSort){
        thread = new Thread(this,name);
        array = arrayToSort;
        thread.start();
    }

    public void run(){
        if(thread.getName().equals("Bubble")) {
            System.out.println("Поток " + thread.getName() + " запущен");

            BubbleSort bs = new BubbleSort();
            Date dateBefore = new Date();
            bs.sort(array);
            Date dateAfter = new Date();

            System.out.println("Массив " + thread.getName() + " сортировался " + (double) (dateAfter.getTime()
                    - dateBefore.getTime()) / 1000 + " секунд");
            System.out.println("Было соверщенно " + bs.getNumberOfActions() + " Действий");

        } else if(thread.getName().equals("Quick")){
            System.out.println("Поток " + thread.getName() + " запущен");

            QuickSort qs = new QuickSort();
            Date dateBefore = new Date();
            qs.sort(array);
            System.out.println();
            qs.printMasToFile();
            Date dateAfter = new Date();

            System.out.println("Массив " + thread.getName() + " сортировался " + (double)(dateAfter.getTime()
                    - dateBefore.getTime())/1000 + " секунд");
            System.out.println("Было соверщенно " + qs.getNumberOfActions() + " Действий");

        } else if(thread.getName().equals("Merge")){
            System.out.println("Поток " + thread.getName() + " запущен");

            MergeSort ms = new MergeSort();
            Date dateBefore = new Date();
            ms.sort(array);
            System.out.println();
            ms.printMasToFile();
            Date dateAfter = new Date();

            System.out.println("Массив " + thread.getName() + " сортировался " + (double)(dateAfter.getTime()
                    - dateBefore.getTime())/1000 + " секунд");
            System.out.println("Было соверщенно " + ms.getNumberOfActions() + " Действий");

        }
    }
}
