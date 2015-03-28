package com.example.twig.finalproject;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.example.twig.dataObjects.Friend;
import com.example.twig.dataObjects.User;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Andrew on 3/28/2015.
 */
public class AddFriendTest extends ApplicationTestCase<Application> {

    public AddFriendTest() {
        super(Application.class);

        User u1 = new User("User1", "one@email.com", "Pass1");
        User u2 = new User("User2", "two@email.com", "Pass2");
        User u3 = new User("User3", "three@email.com", "Pass3");
        User u4 = new User("User4", "four@email.com", "Pass4");

        //Friend list starts off empty
        assertEquals(u1.getFriendList().size(), 0);

        /*****add 2 friends*******/
        u1.addFriend(u2);
        u1.addFriend(u3);

        //size equals 2
        assertEquals(u1.getFriendList().size(), 2);

        //friendlist contains both users that were added
        assertTrue(friendInList(u1.getFriendList(), u2));
        assertTrue(friendInList(u1.getFriendList(), u3));

        //friendList doesn't contain the user that wasn't added (sanity check)
        assertFalse(friendInList(u1.getFriendList(), u4));




        /*********attempt adding a duplicate friend********/
        u1.addFriend(u2);


        //size should still equal 2
        assertEquals(u1.getFriendList().size(), 2);

        //both u2 and u3 should still be in the friends list
        assertTrue(friendInList(u1.getFriendList(), u2));
        assertTrue(friendInList(u1.getFriendList(), u3));

        //list should only contain 1 occurrence of u2
        assertEquals(countInstancesOfFriend(u1.getFriendList(), u2), 1);
    }

    /**
     * Helper method to find if a Friend list contains a specific User.
     * I can't just use 'contains' because the Friend list does not store
     * 'User's-- it stores 'Friend's.
     */
    private boolean friendInList(ArrayList<Friend> friends, User u) {
        for(Friend f: friends) {
            if (f.getUser().equals(u))
                return true;
        }

        return false;
    }

    /**
     * Counts the instances of a given User in a given FriendList.
     * Can't use Collections.frequency() because, once again, I have
     * to take into account the disparity between a 'User' and a 'Friend'
     */
    private int countInstancesOfFriend(ArrayList<Friend> friends, User u) {
        int count = 0;

        for(Friend f: friends) {
            if (f.getUser().equals(u))
                count++;
        }

        return count;
    }
}
