package com.example.twig.androidActivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.ListView;

import com.example.twig.controllers.AppController;
import com.example.twig.controllers.SaleController;
import com.example.twig.controllers.UserController;
import com.example.twig.finalproject.R;
import com.example.twig.dataObjects.Sale;

import java.util.ArrayList;
import java.util.List;

/**
 * The "home" of the app. Has buttons to navigate to all other activities.
 * Also displays the list of sales that match your interests.
 *
 * Created by Andrew on 1/29/2015.
 */
public class ApplicationActivity extends Activity implements AdapterView.OnItemClickListener {
    private ListView sales;
    private ArrayList<Sale> saleList;

    /**
     * Called on creation. Displays name of user who is logged in, as well
     * as any sales reported by their friends that match one of their interests.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);

        sales = (ListView) findViewById(R.id.listView);

        SaleController saleController = SaleController.getSaleController();

        saleList = saleController.getDisplaySale();
        ArrayAdapter<Sale> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,
                saleList);
        sales.setAdapter(adapter);
        sales.setOnItemClickListener(this);

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

    /**
     * When sale is clicked, launch MapViewActivity to show location of the sale.
     *
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, MapViewActivity.class);
        Sale saleClicked = saleList.get(position);

        intent.putExtra("LATITUDE", "" + saleClicked.getLocation().latitude);
        intent.putExtra("LONGITUDE", "" + saleClicked.getLocation().longitude);

        startActivity(intent);
    }
}