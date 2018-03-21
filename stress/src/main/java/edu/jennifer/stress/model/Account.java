package edu.jennifer.stress.model;

import java.util.Random;

/**
 * @author Khalid
 * @Created 3/21/18 2:23 PM.
 */
public class Account {

    private final String[] users = {"admin","sami","elma", "david", "sally", "julia", "tom12", "user9", "user10", "user11", "user12", "user13"};

    private String username;
    private final String password = "admin";

    public Account() {
        username = users[new Random().nextInt(users.length)];
    }
    
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
