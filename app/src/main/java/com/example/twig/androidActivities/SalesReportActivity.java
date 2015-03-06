package com.example.twig.androidActivities;

import android.app.Activity;
import android.content.Intent;
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
    /**
     * Called upon activity creation.
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
     * Method called when the register button is pressed.
     *
     * @param view - the register button
     */
    public void submitPressed(View view) {
        EditText nameField = (EditText)findViewById(R.id.sale_name_field);
        EditText priceField = (EditText)findViewById(R.id.sale_price_field);
        EditText locationField = (EditText)findViewById(R.id.sale_location_field);

        String name = nameField.getText().toString();
        String price = priceField.getText().toString();
        String location = locationField.getText().toString();

        SaleController saleController = SaleController.getSaleController();
        boolean success = saleController.reportSale(name, price, location, this);

        if(success) {
            nameField.setText("");
            priceField.setText("");
            locationField.setText("");
            nameField.requestFocus();
        }
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
}