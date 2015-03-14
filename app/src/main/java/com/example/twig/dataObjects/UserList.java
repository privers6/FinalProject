package com.example.twig.dataObjects;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * The list of registered users. Extends an arraylist of type user. ALL PERSISTENT
 * APP DATA CAN BE SAVED BY SAVING THIS USER LIST. UserList contains Users, which contain
 * FriendsLists, SaleLists, InterestLists... thus all data is encompassed by the UserList
 * if one simply follows the references.
 *
 * Created by Andrew on 2/18/2015.
 */
public class UserList implements Serializable {
    private static ArrayList<User> userlist = new ArrayList<User>();
    private static String filename;

    /**
     * prevents other objects from calling the constructor.
     */
    protected UserList() {}

    /**
     * Creates a user, adds it to the list, then saves the list.
     */
    public static void addUser(String username, String email, String password) {
        userlist.add(new User(username, email, password));
        saveUserList();
    }

    /**
     * Loads the userlist from file.
     */
    public static void loadUserList() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
            userlist = (ArrayList<User>)in.readObject();
            in.close();
        } catch(Exception e) {
            System.out.println("Error reading in data: " + e.getMessage());
        }
    }

    /**
     * Saves the userlist to file.
     */
    public static void saveUserList() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
            out.writeObject(UserList.getUserList());
            out.close();
        } catch (Exception e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Only called for testing purposes. Called by registering a user named "Delete All Data"
     * and typing "Team 57" into the password field (leave email and confirm-pass blank).
     */
    public static void deleteUserData() {
        userlist = new ArrayList<User>();
        saveUserList();
    }

    /**
     * Set the name of the file that this class should save to/load from.
     * Should be set during app initialisation.
     *
     * @param str - the filename to save to
     */
    public static void setSaveFilename(String str) {
        filename = str;
    }

    /**
     * Get the name of the file that this class saves to/loads from.
     */
    public static String getSaveFilename() {
        return filename;
    }

    /**
     * Get the user list.
     * @return
     */
    public static ArrayList<User> getUserList() {
        return userlist;
    }

    /**
     * Utility method to get a user object by the username.
     *
     * @param username username of the User to return
     * @return the user
     */
    public static User getUserByName(String username) {
        //because two users' equality is based solely on their username,
        //we can use a dummy user with just the username that we are searching for
        //to see if the list contains that user.
        User dummyUser = new User(username, null, null);

        int index;
        if((index = userlist.indexOf(dummyUser)) == -1) {
            return null;
        }

        return userlist.get(index);
    }
}
