package com.example.twig.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Activity that allows the user to log in.
 *
 * Created by Andrew on 1/29/2015.
 */
public class FriendSearchActivity extends Activity {
    EditText txt;
    Button add;
    TextView status;

    ListView friends;
    /**
     * Called upon activity creation.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friendsearch);

        txt = (EditText) findViewById(R.id.txtInput);
        add = (Button) findViewById(R.id.btnAdd);
        status = (TextView) findViewById(R.id.txtStatus);
    }

    public void backPressed(View view) {
        Intent intent = new Intent(this, FriendListActivity.class);
        startActivity(intent);
    }

    public void addToFriendsPressed(View view) {
        int i = 0;
        while (RegisterActivity.userList.get(i).getName() != txt.getText().toString()
                && i < RegisterActivity.userList.size()) {
            i++;
        }
        if (i == RegisterActivity.userList.size()) {
            status.setText("User not found.  Try again.");
            status.setVisibility(View.VISIBLE);
        } else {
            ApplicationActivity.getCurrentUser().addFriend(RegisterActivity.userList.get(i));
            status.setText("Friend added.");
            status.setVisibility(View.VISIBLE);
        }

    }
}