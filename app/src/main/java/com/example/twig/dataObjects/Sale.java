package com.example.twig.dataObjects;

import java.io.Serializable;

/**
 * Created by Piyakorn on 3/5/2015.
 */
public class Sale implements Serializable {
    private String name;
    private double price;
    private String location;

    /**
     * Constructor for a Sale object.
     *
     * @param n - the item name
     * @param p - price of sale item
     */
    public Sale(String n, double p, String l) {
        name = n;
        price = p;
        location = l;
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
    public String getLocation() {
        return location;
    }
    /**
     * Setter for sale item location
     * @param l  - new location
     */
    public void setLocation(String l) {
        location = l;
    }

}
