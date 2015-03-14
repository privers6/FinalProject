package com.example.twig.controllers;

import android.graphics.Color;

import com.example.twig.androidActivities.LoginActivity;
import com.example.twig.androidActivities.MainActivity;
import com.example.twig.androidActivities.RegisterActivity;
import com.example.twig.dataObjects.CurrentUser;
import com.example.twig.dataObjects.User;
import com.example.twig.dataObjects.UserList;

/**
 * Controller for all "big-picture" things that happen in the app, including
 * loading the data on app startup, and logging in and out.
 *
 * Created by Andrew on 3/5/2015.
 */
public final class AppController {
    private static AppController singleton;

    /**
     * Make constructor private so other classes can't create one.
     */
    protected AppController() {
    }

    /**
     * Access point for other classes to get the singleton.
     *
     * @return an AppController
     */
    public static AppController getAppController() {
        if (singleton == null) {
            singleton = new AppController();
        }

        return singleton;
    }

    /**
     * Called upon app initialization. Sets the filename for the UserList class
     * and loads any existing data from it.
     *
     * @param activity
     */
    public void loadApplicationData(MainActivity activity) {
        //initialize the user list to write to / read from the correct file
        UserList.setSaveFilename(activity.getFilesDir().getPath() + "userlist.dat");
        UserList.loadUserList();
    }

    /**
     * Only called for testing purposes. Called by registering a user named "Delete All Data"
     * and typing "Team 57" into the password field (leave email and confirm-pass blank).
     */
    public void deleteAllData(RegisterActivity activity) {
        UserList.deleteUserData();
        activity.displayMessage("All data deleted.", Color.YELLOW);
    }

    /**
     * Called when user attempts to log in. Checks that login info
     * is valid, then launches user to the main app screen.
     * If invalid, displays error message.
     *
     * @param username the string typed into the username field of the login screen
     * @param password the password typed into the password field of the login screen
     * @param activity the activity that called the method
     * @return whether the login was succesfull or not
     */
    public boolean login(String username, String password, LoginActivity activity) {
        for(User u: UserList.getUserList()) {
            if(username.equalsIgnoreCase(u.getName()) && password.equals(u.getPassword())) {
                CurrentUser.setCurrentUser(u);
                return true;
            }
        }

        activity.displayMessage("Invalid username/password. Try again.", Color.RED);
        return false;
    }

    /**
     * Called when user logs out. Sets current user to null,
     * and launches main activity
     */
    public void logout() {
        CurrentUser.logOut();
    }
}
