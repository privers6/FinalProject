package com.example.twig.androidActivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.ListView;

import com.example.twig.controllers.AppController;
import com.example.twig.controllers.SaleController;
import com.example.twig.controllers.UserController;
import com.example.twig.finalproject.R;
import com.example.twig.dataObjects.Sale;

import java.util.List;

/**
 * The "home" of the app. Has buttons to navigate to all other activities.
 * Also displays the list of sales that match your interests.
 *
 * Created by Andrew on 1/29/2015.
 */
public class ApplicationActivity extends Activity {
    private ListView sales;

    /**
     * Called on creation. Displays name of user who is logged in.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);

        sales = (ListView) findViewById(R.id.listView);

        SaleController saleController = SaleController.getSaleController();

        ArrayAdapter<Sale> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,
                saleController.getDisplaySale());
        sales.setAdapter(adapter);

        TextView loginText = (TextView)findViewById(R.id.loginText);
        loginText.setText("Logged in as " + UserController.getUserController().getCurrentUsername() + ".");
        //re-centers the text since it's length can vary based on
        loginText.setGravity(Gravity.CENTER_HORIZONTAL);
    }

    /**
     * Method called when the logout button is pressed.
     *
     * @param view - the login button
     */
    public void logoutPressed(View view) {
        AppController.getAppController().logout();
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
    }

    /**
     * Starts the main activity.
     */
    public void startMainActivity() {
        Intent intent = new Intent(this, WelcomeActivity.class);
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
        //or not? are we even using notifications?
    }

    /**
     * Method called when the sales button is pressed.
     *
     * @param view - the sales button
     */
    public void mySalesPressed(View view) {
        Intent intent = new Intent(this, SalesListActivity.class);
        startActivity(intent);
    }
}