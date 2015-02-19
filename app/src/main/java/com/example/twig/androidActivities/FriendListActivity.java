package com.example.twig.androidActivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import com.example.twig.finalproject.R;
import com.example.twig.dataObjects.Friend;
import com.example.twig.dataObjects.User;
import com.example.twig.dataObjects.CurrentUser;

/**
 * Activity that allows the user to log in.
 *
 * Created by Andrew on 1/29/2015.
 */
public class FriendListActivity extends Activity {
    private TextView txt;
    private Button add;
    private ListView friends;
    /**
     * Called upon activity creation.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friendlist);

        ArrayList<Friend> friendList = CurrentUser.getCurrentUser().getFriendList();
        txt = (TextView) findViewById(R.id.txtNumFriends);
        add = (Button) findViewById(R.id.btnAdd);
        friends = (ListView) findViewById(R.id.friendList);

        ArrayAdapter<User> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,
                friendList);
        friends.setAdapter(adapter);

        txt.setText("You have " + friendList.size() + " friends.");
    }

    public void addFriendPressed(View view) {
        Intent intent = new Intent(this, FriendSearchActivity.class);
        startActivity(intent);
    }

    public void backPressed(View view) {
        Intent intent = new Intent(this, ApplicationActivity.class);
        startActivity(intent);
    }
}