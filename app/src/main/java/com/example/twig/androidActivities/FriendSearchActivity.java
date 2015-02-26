package com.example.twig.androidActivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import com.example.twig.dataObjects.CurrentUser;
import com.example.twig.dataObjects.Friend;
import com.example.twig.dataObjects.UserList;
import com.example.twig.finalproject.R;
import com.example.twig.dataObjects.User;

/**
 * Activity that allows the user to log in.
 *
 * Created by Thad on 2/17/2015.
 */
public class FriendSearchActivity extends Activity {
    private EditText txt;
    private Button add;
    private TextView status;

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

    /**
     * Method called when add to friends button is pressed. Searches
     * the userlist for a user with a matching name, and adds them to
     * the current user's friendlist (duplicate adds is handled by
     * the addFriend method, no need to check for that here). Also,
     * doesn't allow a user to add their own username as a friend.
     *
     * @param view the add to friends button
     * @return true if addition is made, false otherwise
     */
    public void addToFriendsPressed(View view) {
        User currentUser = CurrentUser.getCurrentUser();
        ArrayList<User> userlist = UserList.getUserList();
        String queryString = txt.getText().toString();

        //no adding yourself as a friend!
        if(queryString.equalsIgnoreCase(CurrentUser.getCurrentUser().getName())) {
            status.setText("You cannot add yourself as a friend.");
            status.setTextColor(0xFFFF0000);
            status.setVisibility(View.VISIBLE);
            return;
        }

        for(User u: userlist) {
            if (queryString.equalsIgnoreCase(u.getName())) {
                //matching user found!
                boolean success = currentUser.addFriend(u);

                if(success) {
                    status.setText(u.getName() + " added as a friend.");
                    status.setTextColor(0xFF00FF00);
                    status.setVisibility(View.VISIBLE);
                } else {
                    status.setText("You are already friends with " + u.getName() + ".");
                    status.setTextColor(0xFFFF0000);
                    status.setVisibility(View.VISIBLE);
                }
                return;
            }
        }

        status.setText("User not found.");
        status.setTextColor(0xFFFF0000);
        status.setVisibility(View.VISIBLE);
        return;
    }

    public void deleteFriendPressed(View view) {
        User currentUser = CurrentUser.getCurrentUser();
        ArrayList<User> userlist = UserList.getUserList();
        String queryString = txt.getText().toString();

        for(Friend f: currentUser.getFriendList()) {
            if (queryString.equalsIgnoreCase(f.getUser().getName())) {
                currentUser.removeFriend(f.getUser());
                status.setText(f.getUser().getName() + " removed as a friend.");
                status.setTextColor(0xFF00FF00);
                status.setVisibility(View.VISIBLE);
                return;
            }
        }


        status.setText(queryString + " is not on your friend's list.");
        status.setTextColor(0xFFFF0000);
        status.setVisibility(View.VISIBLE);
    }
}