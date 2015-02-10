package com.example.twig.finalproject;

/**
 * Class containing the information for a user.
 *
 * @author Andrew
 */
public class User {
    private String name;
    private String password;

    /**
     * Constructor for a User object.
     *
     * @param n - the user's username
     * @param p - the user's password
     */
    public User(String n, String p) {
        name = n;
        password = p;
    }

    /**
     * Getter for the username.
     * @return the username
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the user's password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter to change the user's password.
     *
     * @param newPass - the new password
     */
    public void setPassword(String newPass) {
        password = newPass;
    }
}
