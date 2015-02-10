package com.example.twig.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Andrew on 2/5/2015.
 */
public class RegisterActivity extends Activity {
    static ArrayList<User> userList = new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public static ArrayList getUserList() {
        return userList;
    }

    public void cancelPressed(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void registerPressed(View view) {
        String user = ((EditText)findViewById(R.id.username_field)).getText().toString();
        String pass = ((EditText)findViewById(R.id.password_field)).getText().toString();
        String confirmPass = ((EditText)findViewById(R.id.confirm_password_field)).getText().toString();
        TextView errorMessage = ((TextView)findViewById(R.id.errorMessage));

        if(user.isEmpty() || pass.isEmpty() || confirmPass.isEmpty()) {
            errorMessage.setText("One or more fields are empty");
            errorMessage.setVisibility(View.VISIBLE);
            return;
        }

        if(!pass.equals(confirmPass)) {
            errorMessage.setText("Confirm password does not match password");
            errorMessage.setVisibility(View.VISIBLE);
            return;
        }

        for(User u: userList) {
            if(user.equalsIgnoreCase(u.getName())) {
                errorMessage.setText("Username already taken. Please try a different one.");
                errorMessage.setVisibility(View.VISIBLE);
                return;
            }
        }

        userList.add(new User(user, pass));
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
