package Sorts;

public class BubbleSort extends Sort {
    private int[] array;

    public void sort(int[] arr){
        this.array = arr;
        for(int i = 0;i<array.length-1;i++){
            for(int j = i;j<array.length;j++){
                if(array[i] > array[j]){
                    int tmp = array[i];
                    array[i] = array[j];
                    array[j] = tmp;
                    numberOfActions++;
                }
            }
        }
    }
    public long getNumberOfActions() {
        return numberOfActions;
    }
}
