
import controller.AuthController;
import controller.LabelController;
import controller.TaskController;
import model.Label;
import model.Task;
import model.User;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        AuthController authController = new AuthController();
        TaskController taskController = new TaskController();
        LabelController labelController = new LabelController();

        authController.loadUsersFromCSV("data/users.csv");
        labelController.loadLabelsFromCSV("data/labels.csv");
        taskController.loadTasksFromCSV("data/tasks.csv", authController.getUsers() ,labelController.getLabels());


        // test to print out all tasks of all users
        for (User user : authController.getUsers()){
            System.out.println("User: " + user.getUsername() +
                                "\nid:" + user.getId());
            System.out.println("===============");
            for(Task task : taskController.getTasks()){
                System.out.println("Task id: " + task.getId());
                System.out.println("Task name: " + task.getTitle());
                System.out.println("Task description: " + task.getDescription());
                System.out.println("Task due date: " + task.getDueDate());
                System.out.println("Task priority: " + task.getPriority());
                System.out.println("Labels: ");
                for(Label label : task.getLabels()){
                    System.out.println(label.getName());
                }

                System.out.println("\n");

            }
        }

        // test to get tasks by priority of a user
        User user = authController.getUsers().get(0);
        ArrayList<Task> highPriorityTasks = taskController.getTasksByPriority(user, "high");
        System.out.println("High priority tasks of " + user.getUsername());
        for (Task task : highPriorityTasks){
            System.out.println("-" + task.getTitle() + " Due: " + task.getDueDate());
        }
    }
}