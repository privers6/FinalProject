package com.example.twig.androidActivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.twig.controllers.InterestController;
import com.example.twig.finalproject.R;

/**
 * Activity in which a user can add an interest to his account.
 *
 * Created by Porter Rivers on 2/26/2015.
 */
public final class RegisterInterestActivity extends Activity{
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
        EditText nameField = (EditText)findViewById(R.id.name_field);
        EditText priceField = (EditText)findViewById(R.id.item_price_field);

        String name = nameField.getText().toString();
        String price = priceField.getText().toString();

        InterestController interestController = InterestController.getInterestController();
        boolean success = interestController.registerInterest(name, price, this);

        if(success) {
            nameField.setText("");
            priceField.setText("");
            nameField.requestFocus();
        }
    }

    /**
     * Displays a message to the user.
     *
     */
    public void displayMessage(String str, int color) {
        TextView msg = (TextView)findViewById(R.id.message);
        msg.setText(str);
        msg.setTextColor(color);
        msg.setVisibility(View.VISIBLE);
    }
}
