package com.example.twig.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Andrew on 1/29/2015.
 */
public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void cancelPressed(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void loginPressed(View view) {
        String user = ((EditText)findViewById(R.id.username_field)).getText().toString();
        String pass = ((EditText)findViewById(R.id.password_field)).getText().toString();

        if (user.equalsIgnoreCase("user") && pass.equals("pass")) {
            Intent intent = new Intent(this, ApplicationActivity.class);
            startActivity(intent);
        }
        else {
            findViewById(R.id.invalid_message).setVisibility(View.VISIBLE);
        }
    }
}
