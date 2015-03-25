package com.example.twig.androidActivities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.twig.controllers.SaleController;
import com.example.twig.finalproject.R;


/**
 * Activity in which the user can report a sale to his friends.
 *
 * Created by Piyakorn on 3/3/2015.
 */
public class SalesReportActivity extends Activity{
    private String latitude;
    private String longitude;
    /**
     * Called upon activity creation. If activity was created
     * from map activity, there will be intent extras that
     * get stored in the temporary variables latitude and longitude.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salesreport);
    }

    /**
     * Method called when the back button is pressed. Returns to the
     * sales list activity
     *
     * @param view - the back button
     */
    public void backPressed(View view) {
        Intent intent = new Intent(this, SalesListActivity.class);
        startActivity(intent);
    }

    /**
     * Method called when set location button is pressed. Hands control off to map activity.
     */
    public void setLocationPressed(View view) {
        Intent intent = new Intent(this, MapPlaceActivity.class);
        intent.putExtra("LATITUDE", latitude);
        intent.putExtra("LONGITUDE", longitude);
        startActivityForResult(intent, 1);
    }

    /**
     * Method called when startActivityForResult finishes.
     * This gets called by the Map Activity, which returns the
     * results "LATITUDE" and "LONGITUDE"
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        //1: map activity
        if(requestCode==1) {
            if(resultCode == RESULT_OK) {
                latitude = intent.getStringExtra("LATITUDE");
                longitude = intent.getStringExtra("LONGITUDE");
                TextView isSet = (TextView)findViewById(R.id.isLocationSet);
                isSet.setText("\u2713"); //TICK MARK
                isSet.setTextColor(Color.GREEN);
            } else {
                //map activity was cancelled -- no activity necessary
            }
        }

        TextView msg = (TextView)findViewById(R.id.message);
        msg.setVisibility(View.INVISIBLE);
        //no need for error messages to linger after going to and from new activity
    }

    /**
     * Method called when the register button is pressed.
     *
     * @param view - the register button
     */
    public void submitPressed(View view) {
        EditText nameField = (EditText)findViewById(R.id.sale_name_field);
        EditText priceField = (EditText)findViewById(R.id.sale_price_field);

        String name = nameField.getText().toString();
        String price = priceField.getText().toString();

        SaleController saleController = SaleController.getSaleController();
        boolean success = saleController.reportSale(name, price, latitude, longitude, this);

        if(success) {
            nameField.setText("");
            priceField.setText("");
            latitude = null;
            longitude = null;

            TextView isSet = (TextView)findViewById(R.id.isLocationSet);
            isSet.setText("");

            nameField.requestFocus();
        }
    }

    /**
     * Gives visual indicator that location is not yet set.
     */
    public void locationNotYetSetHint() {
        TextView isSet = (TextView)findViewById(R.id.isLocationSet);
        isSet.setText("X");
        isSet.setTextColor(Color.RED);
    }

    /**
     * Display message upon invalid login or succesful registration.
     *
     * @param str the message to display
     * @param color the color the message should display
     */
    public void displayMessage(String str, int color) {
        TextView msg = (TextView)findViewById(R.id.message);
        msg.setText(str);
        msg.setTextColor(color);
        msg.setVisibility(View.VISIBLE);
    }

    /**
     * Setters for latitude and longitude. THESE VARIABLES
     * ARE ONLY TEMPORARY STORAGE FOR THE INFO THAT WILL BE
     * PASSED WHEN THE REPORT BUTTON IS PRESSED. They aren't actually
     * being stored anywhere (unless report gets pressed).
     * @param str
     */
}