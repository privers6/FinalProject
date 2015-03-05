package com.example.twig.dataObjects;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Piyakorn on 3/5/2015.
 */
public class SaleList implements Serializable {
    private static ArrayList<Sale> salelist = new ArrayList<Sale>();
    private static String filename;

    /**
     * prevents other objects from calling the constructor.
     */
    protected SaleList() {}

    /**
     * Loads the salelist from file.
     */
    public static void loadSaleList() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
            salelist = (ArrayList<Sale>)in.readObject();
            in.close();
        } catch(Exception e) {
            System.out.println("Error reading in data: " + e.getMessage());
        }
    }

    /**
     * Saves the salelist to file.
     */
    public static void saveSaleList() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
            out.writeObject(SaleList.getSaleList());
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
    public static ArrayList<Sale> getSaleList() {
        return salelist;
    }
}
