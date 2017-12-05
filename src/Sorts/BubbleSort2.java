package Sorts;

public class BubbleSort2 extends Sort {
    public void sort(int[] arr) {
        boolean k = false;
        while (!k) {
            k = true;
            for (int i = 0; i < arr.length - 1; i++) {
                if (arr[i] > arr[i + 1]) {
                    k = false;
                    numberOfActions++;
                    int tmp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = tmp;
                }
            }
        }
    }

    public long getNumberOfActions() {
        return numberOfActions;
    }
}
