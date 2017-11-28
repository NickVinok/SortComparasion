package Sorts;

import java.io.FileWriter;
import java.io.IOException;

public class MergeSort extends Sort{
    private int[] mainArray;
    private int[] helpArray;

    public void sort(int[] array) {

        mainArray = array;
        helpArray = new int[array.length];
        doMergeSort(0, array.length - 1);
    }

    private void doMergeSort(int lowerIndex, int higherIndex) {
        if (lowerIndex < higherIndex) {
            int middle = lowerIndex + (higherIndex - lowerIndex) / 2;
            // Below step sorts the left side of the array
            doMergeSort(lowerIndex, middle);
            // Below step sorts the right side of the array
            doMergeSort(middle + 1, higherIndex);
            // Now merge both sides
            mergeParts(lowerIndex, middle, higherIndex);
        }
    }

    private void mergeParts(int lowerIndex, int middle, int higherIndex) {

        for (int i = lowerIndex; i <= higherIndex; i++) {
            numberOfActions++;
            helpArray[i] = mainArray[i];
        }
        int i = lowerIndex;
        int j = middle + 1;
        int k = lowerIndex;
        while (i <= middle && j <= higherIndex) {
            if (helpArray[i] <= helpArray[j]) {
                numberOfActions++;
                mainArray[k] = helpArray[i];
                i++;
            } else {
                numberOfActions++;
                mainArray[k] = helpArray[j];
                j++;
            }
            k++;
        }
        while (i <= middle) {
            numberOfActions++;
            mainArray[k] = helpArray[i];
            k++;
            i++;
        }
    }

    public long getNumberOfActions() { return numberOfActions; }
}
