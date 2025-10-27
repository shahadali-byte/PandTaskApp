package model;

import java.util.ArrayList;
import java.util.Date;

public class Calendar {
    private ArrayList<Task> tasks;
    private String currentMonth;
    private String currentWeek;

    public String getCurrentMonth() {
        return currentMonth;
    }

    public void setCurrentMonth(String currentMonth) {
        this.currentMonth = currentMonth;
    }

    public String getCurrentWeek() {
        return currentWeek;
    }

    public void setCurrentWeek(String currentWeek) {
        this.currentWeek = currentWeek;
    }
}
