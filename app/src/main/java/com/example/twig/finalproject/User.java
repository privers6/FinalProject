package com.example.twig.finalproject;

/**
 * Created by Andrew on 2/5/2015.
 */
public class User {
    private String name;
    private String password;

    public User(String n, String p) {
        name = n;
        password = p;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String newPass) {
        password = newPass;
    }
}
