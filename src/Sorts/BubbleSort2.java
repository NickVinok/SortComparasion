package Sorts;

public class BubbleSort2 extends Sort {
    public void sort(int[] arr) {
        boolean k = false;
        while (!k) {
            int n = 0;
            for (int i = 0; i < arr.length - 1; i++) {
                if (arr[i] > arr[i + 1]) {
                    numberOfActions++;
                    n++;
                    int tmp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = tmp;
                }
            }
            if (n == 0) {
                k = true;
            } else {
                n = 0;
            }
        }
    }

    public long getNumberOfActions() {
        return numberOfActions;
    }
}
