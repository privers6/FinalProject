package com.example.twig.controllers;

import android.graphics.Color;

import com.example.twig.androidActivities.FriendSearchActivity;
import com.example.twig.dataObjects.CurrentUser;
import com.example.twig.dataObjects.Friend;
import com.example.twig.dataObjects.User;
import com.example.twig.dataObjects.UserList;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller for Friend data objects. Views can create and add friends by interfacing
 * with this controller, as well as access data about friends.
 *
 * Created by Andrew on 3/5/2015.
 */
public final class FriendController {
    private static FriendController singleton;

    /**
     * Make constructor private so other classes can't create one.
     */
    protected FriendController() {
    }

    /**
     * Adds the user with the given name to the friends
     * list of the current user. Cannot add self as a friend,
     * and cannot add a friend more than once.
     *
     * @param name name of the user to add as a friend
     * @return whether the addition was succesful
     */
    public boolean addFriend(String name, FriendSearchActivity activity) {
        //no adding yourself as a friend!
        if(name.equalsIgnoreCase(CurrentUser.getCurrentUser().getName())) {
            activity.displayMessage("You cannot add yourself as a friend.", Color.RED);
            return false;
        }

        for(User u: UserList.getUserList()) {
            if (name.equalsIgnoreCase(u.getName())) { //matching user found!
                boolean success = CurrentUser.getCurrentUser().addFriend(u); //User:addFriend checks for duplicate additions

                if(success) {
                    activity.displayMessage(u.getName() + " added as a friend.", Color.GREEN);
                } else {
                    activity.displayMessage("You are already friends with " + u.getName() + ".", Color.RED);
                }

                return success;
            }
        }

        activity.displayMessage("User not found.", Color.RED);
        return false;
    }

    /**
     * Deletes the friend with the given name from the friends
     * list of the current user.
     *
     * @param name name of the user to delete as a friend
     * @return whether the deletion was succesful
     */
    public boolean deleteFriend(String name, FriendSearchActivity activity) {
        for(Friend f: CurrentUser.getCurrentUser().getFriendList()) {
            if (name.equalsIgnoreCase(f.getUser().getName())) {
                CurrentUser.getCurrentUser().removeFriend(f.getUser());
                activity.displayMessage(f.getUser().getName() + " removed as a friend.", Color.GREEN);
                return true;
            }
        }


        activity.displayMessage(name + " is not on your friend's list.", Color.RED);
        return false;
    }

    /**
     * Return list of friends for the currently logged in user.
     * Useful to plug into List Adapter.
     *
     * @return current user's friend list.
     */
    public List<Friend> getFriendList() {
        return CurrentUser.getCurrentUser().getFriendList();
    }

    /**
     * Returns the size of the friend list of the currently
     * logged in user.
     *
     * @return current user's friend list size
     */
    public int friendListSize() {
        return CurrentUser.getCurrentUser().getFriendList().size();
    }

    /**
     * Gets the Friend object of the given name, assosciated with the
     * current user.
     *
     * @param name the name of the friend
     * @return the Friend
     */
    public Friend getFriend(String name) {
        ArrayList<Friend> friendlist = CurrentUser.getCurrentUser().getFriendList();

        for(Friend f: friendlist) {
            if(f.getUser().getName().equalsIgnoreCase(name)) {
                return f;
            }
        }

        return null;
    }

    /**
     * Returns the rating the current user has given to the friend
     * of the given name.
     *
     * @param name friend whose rating is being returned
     * @return the rating
     */
    public int getRating(String name) {
        Friend f = getFriend(name);

        if(f == null) {
            return 0;
        }

        return f.getRating();
    }

    /**
     * Sets the rating the current user has given to the friend
     * of the given name.
     *
     * @param name friend whose rating is being set
     * @param rating the rating being given
     */
    public void setRating(String name, int rating) {
        Friend f = getFriend(name);

        if(f == null) {
            return;
        }

        f.setRating(rating);
    }

    /**
     * Returns the num of sales reported by the given user to
     * the current user.
     *
     * @param name friend whose # sales is being returned
     * @return the num of sales reported by this user to the current user
     */
    public int getSalesReported(String name) {
        Friend f = getFriend(name);

        if(f== null) {
            return 0;
        }

        return f.getSalesReported();
    }

    /**
     * Access point for other classes to get the singleton.
     *
     * @return a UserController
     */
    public static FriendController getFriendController() {
        if(singleton == null) {
            singleton = new FriendController();
        }

        return singleton;
    }
}
