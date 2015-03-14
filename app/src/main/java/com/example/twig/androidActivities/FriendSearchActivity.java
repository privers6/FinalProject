package com.example.twig.androidActivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.twig.controllers.FriendController;
import com.example.twig.finalproject.R;

/**
 * Activity in which user can add/remove friends. They type in the name of a user,
 * and hit either the add or delete button.
 *
 * Created by Thad on 2/17/2015.
 */
public class FriendSearchActivity extends Activity {
    private EditText txt;

    /**
     * Called upon activity creation.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friendsearch);
        txt = (EditText)findViewById(R.id.txtInput);
    }

    /**
     * Called when the back button is pressed. Sends user
     * back to friends list activity.
     *
     * @param view the back button
     */
    public void backPressed(View view) {
        Intent intent = new Intent(this, FriendListActivity.class);
        startActivity(intent);
    }

    /**
     * Method called when add to friends button is pressed.
     *
     * @param view the add to friends button
     *
     */
    public void addToFriendsPressed(View view) {
        String name = txt.getText().toString();

        FriendController friendController = FriendController.getFriendController();
        friendController.addFriend(name, this);
    }

    /**
     * Method called when delete friend button is pressed.
     *
     * @param view the delete friend button.
     */
    public void deleteFriendPressed(View view) {
        String name = txt.getText().toString();

        FriendController friendController = FriendController.getFriendController();
        friendController.deleteFriend(name, this);
    }

    /**
     * Display message upon successful/failed friend addition.
     *
     * @param str the message to display
     * @param color the color the message should display
     */
    public void displayMessage(String str, int color) {
        TextView msg = (TextView)findViewById(R.id.txtStatus);
        msg.setText(str);
        msg.setTextColor(color);
        msg.setVisibility(View.VISIBLE);
    }
}