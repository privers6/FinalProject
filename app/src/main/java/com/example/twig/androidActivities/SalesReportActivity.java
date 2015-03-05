package com.example.twig.androidActivities;

import android.content.Intent;
import android.view.View;
import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import com.example.twig.dataObjects.User;
import com.example.twig.dataObjects.UserList;
import com.example.twig.finalproject.R;
import com.example.twig.dataObjects.CurrentUser;


/**
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
     * Method called when the back button is pressed.
     *
     * @param view - the cancel button
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
    public void salesReportPressed(View view) {
        String name = ((EditText)findViewById(R.id.sale_name_field)).getText().toString();
        String price = ((EditText)findViewById(R.id.sale_price_field)).getText().toString();
        String location = ((EditText)findViewById(R.id.sale_location_field)).getText().toString();
        TextView text = ((TextView)findViewById(R.id.message));

        ArrayList<User> userList = UserList.getUserList();



        if(name.isEmpty() || price.isEmpty() || location.isEmpty()) {
            text.setText("One or more fields are empty");
            text.setVisibility(View.VISIBLE);
            return;
        }

        double priceValue = Double.parseDouble(price);

        if(priceValue <= 0) {
            text.setText("Max item price must be greater than 0");
            text.setTextColor(0xFFFF0000);
            text.setVisibility(View.VISIBLE);
            return;
        }

        User c = CurrentUser.getCurrentUser();
        c.addSale(name, priceValue, location);
        c.setSalesReported(c.getSalesReported() + 1);
        Intent intent = new Intent(this, SalesReportActivity.class);
        startActivity(intent);

    }
}
