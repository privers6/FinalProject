package com.example.twig.dataObjects;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * The list of registered users. Extends an arraylist of type user,
 * and follows the singleton design pattern.
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
}
