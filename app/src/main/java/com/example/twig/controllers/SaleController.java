package com.example.twig.controllers;

import android.graphics.Color;

import com.example.twig.androidActivities.SalesReportActivity;
import com.example.twig.dataObjects.CurrentUser;
import com.example.twig.dataObjects.Friend;
import com.example.twig.dataObjects.Interest;
import com.example.twig.dataObjects.Sale;
import com.example.twig.dataObjects.User;

import java.util.ArrayList;
import java.util.List;


/**
 * Controller for Sale data objects. Views can create and add sales by interfacing
 * with this controller, as well as access data about them.
 *
 * Created by Andrew on 3/5/2015.
 */
public final class SaleController {
    private static SaleController singleton;

    /**
     * Make constructor private so other classes can't create one.
     */
    protected SaleController() {
    }

    /**
     * Return list of sales for the currently logged in user.
     * Useful to plug into List Adapter.
     *
     * @return current user's sale list.
     */
    public List<Sale> getSaleList() {
        return CurrentUser.getCurrentUser().getSaleList();
    }

    /**
     * Returns the size of the sale list of the currently
     * logged in user.
     *
     * @return current user's sale list size
     */
    public int saleListSize() {
        return CurrentUser.getCurrentUser().getSaleList().size();
    }

    /**
     * Called when a sale is being reported by the user. Ensures all fields
     * are valid, then adds it to the user's sale list.
     *
     * @param name item name
     * @param price item price
     * @param location item location
     * @return whether the report was successful
     */
    public boolean reportSale(String name, String price, String location, SalesReportActivity activity) {
        if(name.isEmpty() || price.isEmpty() || location.isEmpty()) {
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

        User u = CurrentUser.getCurrentUser();
        u.addSale(name, priceValue, location);
        u.setSalesReported(u.getSalesReported() + 1);
        activity.displayMessage("Sale reported successfully!", Color.GREEN);
        return true;
    }

    /**
     * Returns an ArrayList of sales to be displayed
     *
     * @return displayed sales
     */
    public List<Sale> getDisplaySale() {
        ArrayList<Sale> sales = new ArrayList<Sale>();

        //add all sales from all friends
        for(Friend f: CurrentUser.getCurrentUser().getFriendList()) {
            sales.addAll(f.getUser().getSaleList());
        }

        //remove ones that don't match interest
        for(Sale s: sales) {
            boolean matchFound = false;

            for(Interest i: CurrentUser.getCurrentUser().getInterestList()) {
                if(s.getName().equalsIgnoreCase(i.getName()) && s.getPrice() <= i.getPrice()) {
                    matchFound = true;
                    break;
                }
            }

            if(!matchFound) {
                sales.remove(s);
            }
        }
        return sales;
    }

    /**
     * Access point for other classes to get the singleton.
     *
     * @return a UserController
     */
    public static SaleController getSaleController() {
        if(singleton == null) {
            singleton = new SaleController();
        }

        return singleton;
    }
}