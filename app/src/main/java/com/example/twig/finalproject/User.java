package com.example.twig.finalproject;
import java.util.ArrayList;


/**
 * Class containing the information for a user.
 *
 * @author Andrew
 */
public class User {
    private String name;
    private String password;
    static ArrayList<Friend> friendList = new ArrayList<Friend>();
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
     * Method to add new friend.
     *
     * @param u
     */
    public void addFriend(User u) {
        Friend newFriend = new Friend(u);
        boolean found = false;
        for (Friend i: friendList) {
            if (i.getUser().getName() == u.getName()) {
                found = true;
            }
        }
        if (!found) {friendList.add(newFriend);}
    }

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

    /**
     * Override of equals method. Two user's are equal if
     * they have the same username.
     *
     * @param obj - the object we are comparing to
     * @return true if the object is a User with the same username
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User))
            return false;

        return name.equals(((User)obj).getName());
    }

    /**
     * Whenever equals is overridden, so should the hash method,
     * to ensure hashtables work properly.
     *
     * The hashcode should only depend on attributes that are checked
     * when checking for equality.
     */
    @Override
    public int hashCode() {
        return name.hashCode();
    }
>>>>>>> 8dacfd7040d461062fcf67b56b10df31b2d71a21
}
