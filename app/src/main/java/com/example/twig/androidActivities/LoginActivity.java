package com.example.twig.androidActivities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.twig.controllers.AppController;
import com.example.twig.finalproject.R;

/**
 * Activity that allows the user to log in.
 *
 * Created by Andrew on 1/29/2015.
 */
public class LoginActivity extends Activity {
    /**
     * Called upon activity creation. If the activity is created from a
     * successful registration, then gives the user an acknowledgement of
     * the registration success.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        String fillName = getIntent().getStringExtra("FROM_REGISTER");
        if(fillName != null) {
            ((EditText)findViewById(R.id.username_field)).setText(fillName);
            ((EditText)findViewById(R.id.password_field)).requestFocus();
            displayMessage("Registration Successful", Color.GREEN);
        } else {
            ((EditText)findViewById(R.id.username_field)).requestFocus();
        }
    }

    /**
     * Method called when the cancel button is pressed.
     *
     * @param view - the cancel button
     */
    public void cancelPressed(View view) {
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
    }

    /**
     * Method called when the login button is pressed.
     *
     * @param view - the login button
     */
    public void loginPressed(View view) {
        String user = ((EditText)findViewById(R.id.username_field)).getText().toString();
        String pass = ((EditText)findViewById(R.id.password_field)).getText().toString();

        AppController appController = AppController.getAppController();
        boolean success = appController.login(user, pass, this);

        if(success) {
            Intent intent = new Intent(this, ApplicationActivity.class);
            startActivity(intent);
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
