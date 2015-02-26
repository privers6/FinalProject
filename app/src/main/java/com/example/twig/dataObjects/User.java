package com.example.twig.dataObjects;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class containing the information for a user.
 *
 * @author Andrew
 */
public class User implements Serializable {
    private String name;
    private String password;
    private ArrayList<Friend> friendList;
    private ArrayList<Interest> interestList;
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
        friendList = new ArrayList<Friend>();
        interestList = new ArrayList<Interest>();
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

        //TODO: save userlist
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
    public void setEmail(String newEmail) {
        email = newEmail;

        //TODO: save userlist
    }

    /**
     * Getter for the number of sales reported.
     *
     * @return the number of sales reported.
     */
    public int getSalesReported() { return salesReported; }

    /**
     * Getter for the friends list.
     *
     * @return the friends list.
     */
    public ArrayList<Friend>getFriendList() {
        return friendList;
    }

    /**
     * Getter for the interests list.
     *
     * @return the interests list.
     */
    public ArrayList<Interest>getInterestList() {
        return interestList;
    }

    /**
     * Adds a user to this user's friend list. If user
     * is already in friend list, do not do the addition.
     *
     * @param u - User to get wrapped in a Friend object
     *          and added to the friends list.
     * @return whether or not the addition occured
     */
    public boolean addFriend(User u) {
        Friend newFriend = new Friend(u);

        //no repeat additions
        for (Friend i: friendList) {
            if (i.getUser().getName().equalsIgnoreCase(u.getName())) {
                return false;
            }
        }

        friendList.add(newFriend);

        //ensures additions are mutual
        if(!u.getFriendList().contains(this)) {
            u.addFriend(this);
        }

        UserList.saveUserList();
        return true;
    }

    /**
     * Removes the specified user from this user's
     * friend list.
     *
     * @param u - the user to remove from the friend list
     * @return whether or not a removal occured
     */
    public boolean removeFriend(User u) {
        if(friendList.remove(getFriendFromUser(u))) {
            u.removeFriend(this);
            UserList.saveUserList();
            return true;
        }

        return false;
    }

    /**
     * Takes in a user object, and returns the corresponding
     * friend wrapper for this user. Returns null if the user
     * is not on the friends list.
     *
     * @param u - the user whose friend wrapper is to be returned
     * @return the friend wrappper for u, null if u is not a friend
     */
    public Friend getFriendFromUser(User u) {
        for(Friend f: friendList) {
            if (u.equals(f.getUser())) {
                return f;
            }
        }

        return null;
    }

    /**
     * Adds an interest to this user's interest list.
     *
     * @param name - Name of the interest.
     * @param price - Price of the interest.
     */
    public void addInterest(String name, double price) {
        Interest i = new Interest(name, price);
        interestList.add(i);
    }

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

        return name.equalsIgnoreCase(((User) obj).getName());
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
}
