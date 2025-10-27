package controller;

import model.User;

import java.io.*;
import java.util.ArrayList;

public class AuthController {
    private ArrayList<User> users = new ArrayList<>();

    public ArrayList<User> getUsers() {
        return users;
    }

    public void loadUsersFromCSV(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        br.readLine();
        String line;

        while((line = br.readLine()) != null){
            String[] userItems = line.split(",");

            int id = Integer.parseInt(userItems[0]);
            String username = userItems[1];
            String email = userItems[2];
            String password = userItems[3];

            User user = new User(id, username, email, password);
            users.add(user);
        }
        br.close();
    }



    public User singUp(String username, String email, String password) throws IOException {
        // check if it already exists
        for (User user : users){
            if(user.getEmail().equals(email)){
                System.out.println("Email already registered.");
                return null;
            }
        }

        // generate a new Id
        int newId = users.isEmpty() ? 1001 : users.get(users.size()+ 1000).getId() + 1;

        User newUser = new User(newId, username, email, password);

        // save it to csv file
        saveUserToCsv(newUser);

        System.out.println("Sign up successful! Welcome, " + username);
        return newUser;
    }

    private void saveUserToCsv(User user) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("data/users.csv"));
        String line = String.format("%d, %s, %s, %s\n",
                        user.getId(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getPassword());

        bw.write(line);
        bw.close();
    }

    public User login(String email, String password){
        for(User user: users){
            if(user.getEmail().equals(email) && user.getPassword().equals(password)){
                System.out.println("Log in successful! Welcome " + user.getUsername());
                return user;
            }
        }
        System.out.println("Invalid email or password.");
        return null;
    }

    public void logout(User user){
        if(user != null){
            System.out.println("Goodbye, " + user.getUsername());
        }
    }

    // helper to find the user
    public void getUserById(int id){}
}
