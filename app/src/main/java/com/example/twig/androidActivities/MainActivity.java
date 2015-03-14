package com.example.twig.androidActivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.twig.controllers.AppController;
import com.example.twig.dataObjects.CurrentUser;

/**
 * Created by Andrew on 3/6/2015.
 */
public class MainActivity extends Activity {

    /**
     * Called upon app instantiation. Loads persistent data,
     * automatically logs in (if user never logged off), and goes
     * to homescreen.
     *
     * If user logged off, it launches to a welcome screen.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppController.getAppController().loadApplicationData(this);
        Intent intent = new Intent(this, WelcomeActivity.class);

        startActivity(intent);
    }
}