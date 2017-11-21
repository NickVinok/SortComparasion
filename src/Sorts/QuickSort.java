package Sorts;

import java.io.*;

public class QuickSort extends Sort {
    private int[] array;

    public void sort(int[] arr){
        array = arr;
        qSort(0,array.length-1);
    }


    private void qSort(int begin, int end){
        int i = begin, j = end;

        int pivot = array[begin + (end-begin)/2];


        while (i <= j) {

            while (array[i] < pivot) {
                i++;
            }

            while (array[j] > pivot) {
                j--;
            }

            if (i <= j) {
                numberOfActions++;
                int tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
                i++;
                j--;
            }
        }

        if (begin < j)
            qSort(begin, j);
        if (i < end)
            qSort(i, end);
    }

    public long getNumberOfActions() {
        return numberOfActions;
    }

    public void printMasToFile(){
        try(FileWriter fw = new FileWriter("test1.txt")){
            for(int i = 0; i < array.length;i++){
                if(i%20 == 0){
                    fw.write('\n');
                }
                fw.write(Integer.toString(array[i]));
                fw.write(' ');
            }
        }
        catch(IOException exc){
            System.out.println(exc);
        }

    }
}

