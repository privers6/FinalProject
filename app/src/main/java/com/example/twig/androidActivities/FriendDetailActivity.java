package com.example.twig.androidActivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.twig.dataObjects.CurrentUser;
import com.example.twig.dataObjects.Friend;
import com.example.twig.dataObjects.User;
import com.example.twig.dataObjects.UserList;
import com.example.twig.finalproject.R;

/**
 * Created by Andrew on 2/26/2015.
 */
public class FriendDetailActivity extends Activity {

    /**
     * Called upon activity creation. Sets content view
     * and reads in all persisent data.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frienddetail);

        System.out.println("debug1");

        TextView name = (TextView)findViewById(R.id.txtName);
        TextView email = (TextView)findViewById(R.id.txtEmail);
        TextView rating = (TextView)findViewById(R.id.txtRating);
        TextView salesReported = (TextView)findViewById(R.id.txtSalesReported);

        System.out.println("debug2");

        Intent intent = getIntent(); //gets the intent that started this activity
        String user = intent.getStringExtra("USER_CLICKED");

        System.out.println("debug3");

        for(Friend f: CurrentUser.getCurrentUser().getFriendList()) {
            System.out.println("looping1");
            if(f.getUser().getName().equalsIgnoreCase(user)) {
                System.out.println("looping2");
                name.setText(f.getUser().getName());
                System.out.println("looping3");
                email.setText(f.getUser().getEmail());
                System.out.println("looping4");
                String ratingString = (f.getRating() == 0) ? "Not yet rated." : "" + f.getRating();
                rating.setText("Rating: " + ratingString);
                System.out.println("looping5");
                salesReported.setText("Sales Reported: " + f.getUser().getSalesReported());

                System.out.println("looping6");
                break;
            }
        }
    }

    /**
     * Method called when the back button is pressed.
     *
     * @param view - the back button
     */
    public void backPressed(View view) {
        Intent intent = new Intent(this, FriendListActivity.class);
        startActivity(intent);
    }
}
