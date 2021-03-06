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
                numberOfActions+=3;
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

}

