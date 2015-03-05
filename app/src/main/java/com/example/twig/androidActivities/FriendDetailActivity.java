package com.example.twig.androidActivities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.twig.dataObjects.CurrentUser;
import com.example.twig.dataObjects.Friend;
import com.example.twig.dataObjects.User;
import com.example.twig.dataObjects.UserList;
import com.example.twig.finalproject.R;

/**
 * Created by Andrew on 2/26/2015.
 */
public class FriendDetailActivity extends Activity implements AdapterView.OnItemSelectedListener {
    private final String[] POSSIBLE_RATINGS = {"-", "1", "2", "3", "4", "5"};
    private Friend friendBeingDisplayed = null;

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

        TextView name = (TextView)findViewById(R.id.txtName);
        TextView email = (TextView)findViewById(R.id.txtEmail);
        TextView salesReported = (TextView)findViewById(R.id.txtSalesReported);
        Spinner rating = (Spinner)findViewById(R.id.ratingSpinner);

        Intent intent = getIntent(); //gets the intent that started this activity
        String user = intent.getStringExtra("USER_CLICKED");

        for(Friend f: CurrentUser.getCurrentUser().getFriendList()) {
            if(f.getUser().getName().equalsIgnoreCase(user)) {
                name.setText(f.getUser().getName());

                email.setText(f.getUser().getEmail());

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, POSSIBLE_RATINGS);
                rating.setAdapter(adapter);
                rating.setOnItemSelectedListener(this);
                rating.setSelection(f.getRating());

                salesReported.setText("Sales Reported: " + f.getUser().getSalesReported());

                friendBeingDisplayed = f;
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String str = ((TextView)view).getText().toString();

        if(str.equals("-")) {   //no rating selected
            friendBeingDisplayed.setRating(0);
        } else {                //rating selected
            friendBeingDisplayed.setRating(new Integer(str));
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
