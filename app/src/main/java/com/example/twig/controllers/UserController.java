package com.example.twig.controllers;

import android.graphics.Color;

import com.example.twig.androidActivities.RegisterActivity;
import com.example.twig.dataObjects.CurrentUser;
import com.example.twig.dataObjects.User;
import com.example.twig.dataObjects.UserList;

import java.util.ArrayList;

/**
 * Controller for Friend data objects. Views can create and add users by interfacing
 * with this controller, as well as access data about them.
 *
 * Created by Andrew on 3/5/2015.
 */
public class UserController {
    private static UserController singleton;

    /**
     * Make constructor private so other classes can't create one.
     */
    protected UserController() {
    }

    /**
     * Called whenever a new user is to be registered. Data is not
     * trustworthy, so everything is validated/checked here.
     *
     * @param username
     * @param password
     * @param confirmPassword
     * @param email
     * @param activity
     * @return whether the registration was succesful
     */
    public boolean registerUser(String username, String password, String confirmPassword,
                             String email, RegisterActivity activity) {

        ArrayList<User> userList = UserList.getUserList();

        //doesn't check if email is a valid email. this feature could be added later using regex
        //but for the purpose of this class it probably isn't that important to verify

        if(username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            activity.displayMessage("One or more fields are empty", Color.RED);
            return false;
        }

        if(!password.equals(confirmPassword)) {
            activity.displayMessage("Confirm password does not match password", Color.RED);
            return false;
        }

        for(User u: userList) {
            if(username.equalsIgnoreCase(u.getName())) {
                activity.displayMessage("Username already taken. Please try a different one.", Color.RED);
                return false;
            }
        }

        UserList.addUser(username, email, password);
        return true;
    }

    /**
     * Access point for other classes to get the singleton.
     *
     * @return a UserController
     */
    public static UserController getUserController() {
        if(singleton == null) {
          singleton = new UserController();
        }

        return singleton;
    }

    /**
     * Returns the email of the chosen user.
     *
     * @param user the user whose email is being returned
     * @return the user's email
     */
    public String getEmail(String user) {
        User u;
        if((u = UserList.getUserByName(user)) == null) {
            return null;
        }

        return u.getEmail();
    }

    /**
     * Returns the number of sales reported by the user.
     *
     * @param user the user whose sales reported is being returned
     * @return the # of sales reported by the user
     */
    public int getSalesReported(String user) {
        User u;
        if((u = UserList.getUserByName(user)) == null) {
            return 0;
        }

        return u.getSalesReported();
    }

    /**
     * Returns the name of the current User.
     */
    public String getCurrentUsername() {
        return CurrentUser.getCurrentUser().getName();
    }

    /**
     * Returns the email of the current User.
     */
    public String getCurrentEmail() {
        return CurrentUser.getCurrentUser().getEmail();
    }
}
