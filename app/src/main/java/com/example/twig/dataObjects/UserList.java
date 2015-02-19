package com.example.twig.dataObjects;

import java.util.ArrayList;

/**
 * The list of registered users. Extends an arraylist of type user,
 * and follows the singleton design pattern.
 *
 * Created by Andrew on 2/18/2015.
 */
public class UserList {
    private static ArrayList<User> userlist = new ArrayList<User>();

    /**
     * prevents other objects from calling the constructor.
     */
    protected UserList() {}

    public static ArrayList<User> getUserList() {
        return userlist;
    }
}
