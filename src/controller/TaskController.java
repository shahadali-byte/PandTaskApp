package controller;

import model.Label;
import model.Task;
import model.User;

import java.io.*;
import java.util.ArrayList;

public class TaskController {
    private ArrayList<Task> tasks = new ArrayList<>();

    public ArrayList<Task> getTasks() {
        return tasks;
    }


    public void loadTasksFromCSV(String filename, ArrayList<User> users ,ArrayList<Label> allLabels) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        br.readLine();
        String line;

        while((line = br.readLine()) != null){
            String[] taskItems = line.split(",");

            int id = Integer.parseInt(taskItems[0]);
            int userId = Integer.parseInt(taskItems[1]);
            String title = taskItems[2];
            String description = taskItems[3];
            String dueDate = taskItems[4];
            String priority= taskItems[5];
            String labelItems = taskItems.length > 6 ? taskItems[6] : "";

            Task task = new Task(id, userId, title, description, dueDate, priority);


            // assigns labels to each task
            if(!labelItems.isEmpty()){
                String[] labelNames = labelItems.split(";");
                for (String name : labelNames){
                    for (Label label: allLabels){
                        if(label.getName().equals(name.trim())){
                            task.getLabels().add(label);
                        }
                    }
                }
            }
            tasks.add(task);

            // assign tasks to users after loading
            for (User user : users) {
                if (user.getId() == task.getUserId()) {
                    user.getTasks().add(task);
                    break;
                }
            }

        }
        br.close();
    }


    public void addTask(User user, Task task) throws IOException {
        // add tasks to a global task list
        tasks.add(task);

        // add tasks to the user's task list
        user.getTasks().add(task);

        // save the task on the CSV file
        saveTaskToCSV(task);
    }

    private void saveTaskToCSV(Task task) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("tasks.csv"));

        // convert labels list to a list with semicolons in case there is more than one label
        StringBuilder labelSb = new StringBuilder();
        for (Label label : task.getLabels()){
            if(!labelSb.isEmpty()){
                labelSb.append(";");
                labelSb.append(label.getName());
            }
        }

        String line = String.format(("%d, %d, %s, %s, %s, %s, %s\n"),
                        task.getId(),
                        task.getUserId(),
                        task.getTitle(),
                        task.getDescription(),
                        task.getDueDate(),
                        task.getPriority(),
                        labelSb);

        bw.write(line);
        bw.close();

    }

    public void editTask(User user, Task taskId, Task updatedTaskData){}

    public void deleteTask(User user, Task taskId){
        // remove tasks to a global task list
        tasks.remove(taskId);

        // remove tasks to the user's task list
        user.getTasks().remove(taskId);

    }

    public ArrayList<Task> getTasksByPriority(User user, String priority){
        ArrayList<Task> priorityTasks= new ArrayList<>();
        for (Task task: user.getTasks()){
            if(task.getPriority().equalsIgnoreCase(priority)){
                priorityTasks.add(task);
            }
        }
        return priorityTasks;
    }

    public ArrayList<Task> getTasksByLabel(User user, ArrayList<Label> labels){
        ArrayList<Task> labeledTasks = new ArrayList<>();
        for (Task task : user.getTasks()){
            if(task.getLabels().equals(labels)){
                labeledTasks.add(task);
            }
        }
        return labeledTasks;
    }

    public void getTasksByDate(User User, String date){}

    public void getAllTasks(User user){
    }
}
