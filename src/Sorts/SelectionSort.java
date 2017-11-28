package Sorts;

public class SelectionSort extends Sort {
    public void sort(int[] arr) {
        int minNumbIndex;
        for(int i = 0;i<arr.length;i++){
            minNumbIndex = i;
            for(int j = i;j<arr.length;j++){
                if(arr[j]<arr[minNumbIndex]){
                    minNumbIndex = j;
                }
            }
            if(i != minNumbIndex) {
                numberOfActions++;
                int tmp = arr[minNumbIndex];
                arr[minNumbIndex] = arr[i];
                arr[i] = tmp;
            }
        }
    }

    public long getNumberOfActions() {
        return numberOfActions;
    }
}
