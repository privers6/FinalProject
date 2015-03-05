package com.example.twig.androidActivities;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;

import com.example.twig.dataObjects.UserList;
import com.example.twig.finalproject.R;
import com.example.twig.dataObjects.Friend;
import com.example.twig.dataObjects.User;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * The main activity, which launches whenever the app is started.
 *
 * @author Andrew
 */
public class MainActivity extends Activity {

    /**
     * Called upon activity creation. Sets content view
     * and reads in all persisent data.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize the user list to write to / read from the correct file
        UserList.setSaveFilename(getFilesDir().getPath() + "userlist.dat");
        UserList.loadUserList();
        System.out.println("loaded data");
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
