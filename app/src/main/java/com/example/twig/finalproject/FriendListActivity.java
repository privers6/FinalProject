package com.example.twig.finalproject;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;



import java.util.ArrayList;

/**
 * Activity that allows the user to log in.
 *
 * Created by Andrew on 1/29/2015.
 */
public class FriendListActivity extends Activity {
    EditText txt;
    Button add;
    ListView friends;
    /**
     * Called upon activity creation.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friendslist);

        txt = (EditText) findViewById(R.id.txtInput);
        add = (Button) findViewById(R.id.btnAdd);
        friends = (ListView) findViewById(R.id.friendList);
        //ArrayAdapter<User> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,
          //      ApplicationActivity.getCurrentUser().getFriendList());
        //friends.setAdapter(adapter);
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