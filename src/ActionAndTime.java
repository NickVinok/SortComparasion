public class ActionAndTime {
    private String timeForSort;
    private String numberOfActions;

    ActionAndTime(){
          timeForSort = "";
          numberOfActions = "";
    }

    public void setNumberOfActions(String numberOfActions) {
        this.numberOfActions = numberOfActions;
    }

    public void setTimeForSort(String timeForSort) {
        this.timeForSort = timeForSort;
    }

    public String getNumberOfActions() {
        return numberOfActions;
    }

    public String getTimeForSort() {
        return timeForSort;
    }
}
