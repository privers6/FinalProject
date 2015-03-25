package com.example.twig.androidActivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.twig.finalproject.R;

/**
 * The main activity, which launches whenever the app is started.
 *
 * @author Andrew
 */
public class WelcomeActivity extends Activity {

    /**
     * Called upon activity creation.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Method called when the login button is pressed.
     *
     * @param view - the login button
     */
    public void loginPressed(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    /**
     * Method called when the register button is pressed.
     *
     * @param view - the register button
     */
    public void registerPressed(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}
