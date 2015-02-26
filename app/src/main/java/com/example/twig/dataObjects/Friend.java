package com.example.twig.dataObjects;

/**
 * Wrapper for a user, that also includes, their
 * rating as a friend.
 *
 * Created by Piyakorn on 2/12/2015.
 */
public class Friend {
    private User user;
    private int rating;


    /**
     * Constructor for a Friend object.
     *
     * @param u - the user friend
     */
    public Friend(User u) {
        user = u;
        rating = 0;
    }

    /**
     * Getter for name.
     * @return the name
     */
    public User getUser() {
        return user;
    }

    /**
     * Getter for the friend's rating.
     *
     * @return the rating
     */
    public int getRating() {
        return rating;
    }

    /**
     * Setter to change the user's rating.
     *
     * @param rate - the new rating
     */
    public void setRating(int rate) {
        rating = rate;
    }
    public String toString(){ return user.getName();}
}