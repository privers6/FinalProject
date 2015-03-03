package com.example.twig.androidActivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import com.example.twig.dataObjects.CurrentUser;
import com.example.twig.dataObjects.User;
import com.example.twig.dataObjects.Friend;
import com.example.twig.dataObjects.UserList;
import com.example.twig.finalproject.R;

/**
 * Created by Andrew on 1/29/2015.
 */
public class ApplicationActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);

        Intent intent = getIntent(); //gets the intent that started this activity
        String usernameFromLoginActivity = intent.getStringExtra("LOGIN_MESSAGE");

        //this activity is sometimes started from activities other than login
        //so it is important to only perform these actions if the intent
        //starting this activity actually came from the login activity
        if (usernameFromLoginActivity != null) {
            ArrayList<User> userlist = UserList.getUserList();

            //because two users' equality is based solely on their username,
            //we can use a dummy user with just the username that we are searching for
            //to see if the list contains that user.
            User dummyUser = new User(usernameFromLoginActivity, null, null);

            //this assumes that the userlist contains the dummy user we are searching for.
            //this should be a safe assumption to make since we can only log in if the user
            //is already registered. if this assumption were to fail, currentUser would
            //remain null (or whatever it's previous value was) which could cause
            //problems. it SHOULDN'T ever fail though...
            CurrentUser.setCurrentUser(userlist.get(userlist.indexOf(dummyUser)));
        }

        TextView loginText = (TextView) findViewById(R.id.successText);
        loginText.setText("You have successfully logged in as " + CurrentUser.getCurrentUser().getName() + ".");
        //re-centers the text since it's length can vary based on
        loginText.setGravity(Gravity.CENTER_HORIZONTAL);
    }

    /**
     * Method called when the login button is pressed.
     *
     * @param view - the login button
     */
    public void logoutPressed(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        CurrentUser.logOut();
        startActivity(intent);
    }

    /**
     * Method called when the friend button is pressed.
     *
     * @param view - the friend button
     */
    public void friendsPressed(View view) {
        Intent intent = new Intent(this, FriendListActivity.class);
        startActivity(intent);
    }

    /**
     * Method called when the friend button is pressed.
     *
     * @param view - the friend button
     */
    public void interestsPressed(View view) {
        Intent intent = new Intent(this, InterestListActivity.class);
        startActivity(intent);
    }

    /**
     * Method called when the notification button is pressed.
     *
     * @param view - the notification button
     */
    public void notificationButtonPressed(View view) {
        //TODO: add notificationActivity
    }
}