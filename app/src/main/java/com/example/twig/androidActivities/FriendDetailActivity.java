package com.example.twig.androidActivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.twig.controllers.FriendController;
import com.example.twig.controllers.UserController;
import com.example.twig.finalproject.R;

/**
 * Activity that shows details of a Friend that the user
 * has clicked on.
 *
 * Created by Andrew on 2/26/2015.
 */
public class FriendDetailActivity extends Activity implements AdapterView.OnItemSelectedListener {
    private final String[] POSSIBLE_RATINGS = {"-", "1", "2", "3", "4", "5"};
    String userBeingDisplayed;

    /**
     * Called upon activity creation. Sets content view
     * and reads in all persisent data, passing them to
     * appropriate views.
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
        userBeingDisplayed = intent.getStringExtra("USER_CLICKED");

        FriendController friendController = FriendController.getFriendController();
        UserController userController = UserController.getUserController();

        name.setText(userBeingDisplayed);

        email.setText(userController.getEmail(userBeingDisplayed));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.rating_spinner_item, POSSIBLE_RATINGS);
        rating.setAdapter(adapter);
        rating.setOnItemSelectedListener(this);
        rating.setSelection(friendController.getRating(userBeingDisplayed));

        salesReported.setText("Sales Reported: " + userController.getSalesReported(userBeingDisplayed));
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

    /**
     * Called when an item on the list is selected. Updates
     * the friends rating.
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String str = ((TextView)view).getText().toString();
        FriendController friendController = FriendController.getFriendController();

        if(str.equals("-")) {   //no rating selected
            friendController.setRating(userBeingDisplayed, 0);
        } else {                //rating selected
            friendController.setRating(userBeingDisplayed, new Integer(str));
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
