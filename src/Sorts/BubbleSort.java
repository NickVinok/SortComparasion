package Sorts;

public class BubbleSort extends Sort {
    private int[] array;

    public void sort(int[] arr){
        this.array = arr;
        //Идём по массиву и сравниваем элемент со всеми последующими
        //Если меньше, то меняем местами и продолжаем
        //И сравниваем уже меньший элемент
        //После прохода для одного идекса
        //На этом индексе окажется наименьший элемент
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
