package com.example.twig.finalproject;

/**
 * Class containing the information for a user.
 *
 * @author Andrew
 */
public class User {
    private String name;
    private String password;
    private String email;
    private int salesReported;

    /**
     * Constructor for a User object.
     *
     * @param n - the user's username
     * @param p - the user's password
     */
    public User(String n, String e, String p) {
        name = n;
        email = e;
        password = p;
        salesReported = 0;
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

    /**
     * Getter for the user's email.
     *
     * @return the email
     */
    public String getEmail() { return email; }

    /**
     * Setter to change the user's email.
     *
     * @param newEmail - the new email
     */
    public void setEmail(String newEmail) { email = newEmail; }

    /**
     * Getter for the number of sales reported.
     *
     * @return the number of sales reported.
     */
    public int getSalesReported() { return salesReported; }
}
