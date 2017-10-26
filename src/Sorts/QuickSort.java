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
        // Get the pivot element from the middle of the list
        int pivot = array[begin + (end-begin)/2];

        // Divide into two lists
        while (i <= j) {
            // If the current value from the left list is smaller than the pivot
            // element then get the next element from the left list
            while (array[i] < pivot) {
                i++;
            }
            // If the current value from the right list is larger than the pivot
            // element then get the next element from the right list
            while (array[j] > pivot) {
                j--;
            }

            // If we have found a value in the left list which is larger than
            // the pivot element and if we have found a value in the right list
            // which is smaller than the pivot element then we exchange the
            // values.
            // As we are done we can increase i and j
            if (i <= j) {
                numberOfActions++;
                int tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
                i++;
                j--;
            }
        }
        // Recursion
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

