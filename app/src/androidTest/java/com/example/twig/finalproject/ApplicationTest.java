package com.example.twig.finalproject;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.example.twig.dataObjects.User;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);

        test_addFriend();
    }

    public void test_addFriend() {
        User u1 = new User("User1", "one@email.com", "Pass1");
        User u2 = new User("User2", "two@email.com", "Pass2");
        User u3 = new User("User3", "three@email.com", "Pass3");
        User u4 = new User("User4", "four@email.com", "Pass4");

        assert(u1.getName() == "User2");
    }
}