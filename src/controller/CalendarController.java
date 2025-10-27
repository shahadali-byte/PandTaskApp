package controller;

import model.Task;
import model.User;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class CalendarController {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Returns all tasks for a user that match the exact given date.
     */
    public ArrayList<Task> getTasksByDate(User user, Date date) {
        ArrayList<Task> matched = new ArrayList<>();
        String targetDate = dateFormat.format(date);

        for (Task task : user.getTasks()) {
            if (task.getDueDate().equals(targetDate)) {
                matched.add(task);
            }
        }
        return matched;
    }

    /**
     * Returns all tasks for a user that occur in the specified month (e.g., "2025-10").
     */
    public ArrayList<Task> getTasksByMonth(User user, String month) {
        ArrayList<Task> matched = new ArrayList<>();
        for (Task task : user.getTasks()) {
            if (task.getDueDate().startsWith(month)) {
                matched.add(task);
            }
        }
        return matched;
    }

    /**
     * Returns all tasks for a user that fall within a specific week.
     * Input week should be in the format "yyyy-WW" (e.g., "2025-43").
     */
    public ArrayList<Task> getTasksByWeek(User user, String week) {
        ArrayList<Task> matched = new ArrayList<>();

        Calendar cal = Calendar.getInstance();
        for (Task task : user.getTasks()) {
            try {
                Date dueDate = dateFormat.parse(task.getDueDate());
                cal.setTime(dueDate);
                int taskWeek = cal.get(Calendar.WEEK_OF_YEAR);
                int taskYear = cal.get(Calendar.YEAR);

                String formattedWeek = String.format("%d-%02d", taskYear, taskWeek);
                if (formattedWeek.equals(week)) {
                    matched.add(task);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return matched;
    }

    /**
     * Simulates opening a task creation form for the selected date.
     * Placeholder method for UI integration.
     */
    public void openTaskForm(Date date) {
        System.out.println("Opening task form for: " + dateFormat.format(date));
    }
}
