package Sorts;

public abstract class Sort {
    protected long numberOfActions = 0;
    public abstract void sort(int[] array);
    public abstract long getNumberOfActions();
}
