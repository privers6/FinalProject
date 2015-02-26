package com.example.twig.androidActivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.twig.dataObjects.CurrentUser;
import com.example.twig.finalproject.R;

import java.util.ArrayList;

/**
 * Created by Porter Rivers on 2/26/2015.
 */
public class RegisterInterestActivity extends Activity{
    /**
     * Called upon activity creation.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerinterest);
    }

    /**
     * Method called when the back button is pressed.
     *
     * @param view - the cancel button
     */
    public void backPressed(View view) {
        Intent intent = new Intent(this, InterestListActivity.class);
        startActivity(intent);
    }

    /**
     * Method called when the register button is pressed.
     *
     * @param view - the register button
     */
    public void registerPressed(View view) {
        String name = ((EditText)findViewById(R.id.name_field)).getText().toString();
        String price = ((EditText)findViewById(R.id.item_price_field)).getText().toString();
        TextView errorMessage = ((TextView)findViewById(R.id.errorMessage));


        if(name.isEmpty() || price.isEmpty()) {
            errorMessage.setText("One or more fields are empty");
            errorMessage.setVisibility(View.VISIBLE);
            return;
        }

        double priceValue = Double.parseDouble(price);

        if(priceValue <= 0) {
            errorMessage.setText("Max item price must be greater than 0");
            errorMessage.setVisibility(View.VISIBLE);
            return;
        }

        CurrentUser.getCurrentUser().addInterest(name, priceValue);
        Intent intent = new Intent(this, InterestListActivity.class);
        startActivity(intent);
    }
}
