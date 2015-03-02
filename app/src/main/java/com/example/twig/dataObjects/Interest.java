package com.example.twig.dataObjects;

import java.io.Serializable;
import java.text.DecimalFormat;

/**
 * Created by Piyakorn on 2/24/2015.
 */
public class Interest implements Serializable {
    private String name;
    private double price;

    /**
     * Constructor for a Interest object.
     *
     * @param n - the item name
     * @param p - price of interest item
     */
    public Interest(String n, double p) {
        name = n;
        price = p;
    }
    /**
     * Getter for name.
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * Setter for interest item name
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
     * Setter for interest item price
     * @param p  - new name of price
     */
    public void setPrice(double p) {
        price = p;
    }

    /**
     * toString method for the Interest class
     *
     * @return String representation of an Interest
     */
    public String toString() { return name + " --- Max Price: $" + new DecimalFormat("0.00").format(price); }

}