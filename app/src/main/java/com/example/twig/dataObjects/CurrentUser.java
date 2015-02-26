package com.example.twig.dataObjects;

/**
 * Wrapper class for the currently logged in user.
 *
 * Created by Andrew on 2/18/2015.
 */
public class CurrentUser {
    private static User current;

    /**
     * Don't allow anyone to construct a CurrentUser
     */
    private CurrentUser() {
    }

    /**
     * Sets the current user.
     *
     * @param u - the user to set the current user to
     */
    public static void setCurrentUser(User u) {
        current = u;
    }

    /**
     * Gets the current user.
     *
     * @return the current user.
     */
    public static User getCurrentUser() {
        return current;
    }

    /**
     * Convenience method for logging out, instead
     * of calling setCurrentUser(null)
     */
    public static void logOut() {
        setCurrentUser(null);
    }
}
