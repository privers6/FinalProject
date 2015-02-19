package com.example.twig.androidActivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import com.example.twig.dataObjects.UserList;
import com.example.twig.finalproject.R;
import com.example.twig.dataObjects.Friend;
import com.example.twig.dataObjects.User;

/**
 * Activity that allows the user to log in.
 *
 * Created by Andrew on 1/29/2015.
 */
public class LoginActivity extends Activity {
    /**
     * Called upon activity creation.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    /**
     * Method called when the cancel button is pressed.
     *
     * @param view - the cancel button
     */
    public void cancelPressed(View view) {
        Intent intent = new Intent(this, MainActivity.class);
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

        ArrayList<User> userList = UserList.getUserList();

        for(User u: userList) {
            if(user.equalsIgnoreCase(u.getName())) {
                if(pass.equals(u.getPassword())) {
                    Intent intent = new Intent(this, ApplicationActivity.class);
                    intent.putExtra("LOGIN_MESSAGE", u.getName());
                    startActivity(intent);
                    return;
                }
            }
        }

        findViewById(R.id.invalid_message).setVisibility(View.VISIBLE);
    }
}
