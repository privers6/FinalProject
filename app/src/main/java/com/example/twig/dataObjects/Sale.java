package com.example.twig.dataObjects;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;
import java.text.DecimalFormat;

/**
 * Data object representing a sale that a user has reported. Reported sales show up in the "feed"
 * of a user's friends.
 *
 * Created by Piyakorn on 3/5/2015.
 */
public class Sale implements Serializable {
    private String name;
    private double price;

    //store as 2 doubles instead of as a LatLng since LatLng isn't serializable
    //however, getters and setters use LatLng, so from the client's perspective,
    //locations are stored as LatLng's
    private double latitude;
    private double longitude;

    /**
     * Constructor for a Sale object.
     *
     * @param n - the item name
     * @param p - price of sale item
     */
    public Sale(String n, double p, double lat, double lon) {
        name = n;
        price = p;
        latitude = lat;
        longitude = lon;
    }
    /**
     * Getter for name.
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * Setter for sale item name
     * @param n  - new name for item
     */
    public void setName(String n) {
        name = n;
    }
    /**
     * Getter for price.
     * @return the price
     */
    public double getPrice() {
        return price;
    }
    /**
     * Setter for sale item price
     * @param p  - new price
     */
    public void setPrice(double p) {
        price = p;
    }
    /**
     * Getter for location.
     * @return the location
     */
    public LatLng getLocation() {
        return new LatLng(latitude, longitude);
    }
    /**
     * Setter for sale item location
     * @param l  - new location
     */
    public void setLocation(LatLng l) {
        latitude = l.latitude;
        longitude = l.longitude;
    }

    /**
     * toString for a sale. Includes name, price, and location.
     * @return
     */
    public String toString() {
        return (name + ": $" + new DecimalFormat("0.00").format(price));
    }

}