package com.example.twig.androidActivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.twig.controllers.FriendController;
import com.example.twig.dataObjects.User;
import com.example.twig.finalproject.R;

/**
 * Activity that displays a list of all of the current user's friends.
 * Each friend may be clicked on to launch a FriendDetailActivit
 *
 * Created by Andrew on 1/29/2015.
 */
public class FriendListActivity extends Activity implements OnItemClickListener {
    private TextView txt;
    private Button add;
    private ListView friends;

    /**
     * Called upon activity creation. Creates the list/adapter,
     * and adds an onItemClickedListener() to register clicks.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friendlist);

        txt = (TextView) findViewById(R.id.txtNumFriends);
        add = (Button) findViewById(R.id.btnAdd);
        friends = (ListView) findViewById(R.id.friendList);

        FriendController friendController = FriendController.getFriendController();

        ArrayAdapter<User> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,
                friendController.getFriendList());
        friends.setAdapter(adapter);
        friends.setOnItemClickListener(this);

        txt.setText("You have " + friendController.friendListSize() + " friend"
                + ((friendController.friendListSize() == 1) ? "." : "s."));
    }

    /**
     * Method called when add friends button is pressed.
     * Sends the user to the friendSearchActivity.
     *
     * @param view - the add friends button.
     */
    public void addFriendPressed(View view) {
        Intent intent = new Intent(this, FriendSearchActivity.class);
        startActivity(intent);
    }

    /**
     * Method called when back button is pressed.
     * Sends the user to the applicationActivity.
     *
     * @param view - the back button.
     */
    public void backPressed(View view) {
        Intent intent = new Intent(this, ApplicationActivity.class);
        startActivity(intent);
    }

    /**
     * Called when a friend is clicked on. Brings user to that friend's
     * FriendDetailActivity
     *
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, FriendDetailActivity.class);
        String selectedTextString = ((TextView)view).getText().toString();
        intent.putExtra("USER_CLICKED", selectedTextString);

        startActivity(intent);
    }
}