package com.example.twig.controllers;

import android.graphics.Color;

import com.example.twig.androidActivities.RegisterInterestActivity;
import com.example.twig.dataObjects.CurrentUser;
import com.example.twig.dataObjects.Interest;

import java.util.ArrayList;



/**
 * Controller for Interest data objects. Views can create and add Interests by interfacing
 * with this controller, as well as access data about them.
 *
 * Created by Andrew on 3/5/2015.
 */
public class InterestController {
    private static InterestController singleton;

    /**
     * Make constructor private so other classes can't create one.
     */
    protected InterestController() {
    }

    /**
     * Return list of interests for the currently logged in user.
     * Useful to plug into List Adapter.
     *
     * @return current user's interest list.
     */
    public ArrayList<Interest> getInterestList() {
        return CurrentUser.getCurrentUser().getInterestList();
    }

    /**
     * Returns the size of the interest list of the currently
     * logged in user.
     *
     * @return current user's interest list size
     */
    public int interestListSize() {
        return CurrentUser.getCurrentUser().getInterestList().size();
    }

    /**
     * Gets the Interest object of the given name, assosciated with the
     * current user.
     *
     * @param name the name of the Interest
     * @return the Interest
     */
    public Interest getInterest(String name) {
        ArrayList<Interest> interestList = CurrentUser.getCurrentUser().getInterestList();

        for(Interest interest: interestList) {
            if(interest.getName().equalsIgnoreCase(name)) {
                return interest;
            }
        }

        return null;
    }

    /**
     * Constructs an interest from the information given, and adds it to the current user's
     * interest list.
     *
     * @param name name of the item
     * @param price price of the item
     * @return whether the interest was succesfully registered
     */
    public boolean registerInterest(String name, String price, RegisterInterestActivity activity) {
        if(name.isEmpty() || price.isEmpty()) {
            activity.displayMessage("One or more fields are empty", Color.RED);
            return false;
        }

        double priceValue;
        try {
            priceValue = Double.parseDouble(price);
        } catch (Exception e) { //should never happen since input is limited to numbers!
            activity.displayMessage("Failed parsing price string.", Color.RED);
            return false;
        }

        if(priceValue <= 0) {
            activity.displayMessage("Max item price must be greater than 0", Color.RED);
            return false;
        }

        CurrentUser.getCurrentUser().addInterest(name, priceValue);
        activity.displayMessage("Interest added", Color.GREEN);
        return true;
    }

    /**
     * Access point for other classes to get the singleton.
     *
     * @return a UserController
     */
    public static InterestController getInterestController() {
        if(singleton == null) {
            singleton = new InterestController();
        }

        return singleton;
    }
}
