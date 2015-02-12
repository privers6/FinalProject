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
 * The activity in which the user registers for an account.
 *
 * @author Andrew
 */
public class RegisterActivity extends Activity {
    static ArrayList<User> userList = new ArrayList<User>();

    /**
     * Called upon activity creation.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    /**
     * Returns the static list of all of the registered users.
     *
     * @return the list of users.
     */
    public static ArrayList getUserList() {
        return userList;
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
     * Method called when the register button is pressed.
     *
     * @param view - the register button
     */
    public void registerPressed(View view) {
        String user = ((EditText)findViewById(R.id.username_field)).getText().toString();
        String email = ((EditText)findViewById(R.id.email_field)).getText().toString();
        String pass = ((EditText)findViewById(R.id.password_field)).getText().toString();
        String confirmPass = ((EditText)findViewById(R.id.confirm_password_field)).getText().toString();
        TextView errorMessage = ((TextView)findViewById(R.id.errorMessage));

        //doesn't check if email is a valid email. this feature could be added later using regex
        //but for the purpose of this class it probably isn't that important to verify

        if(user.isEmpty() || email.isEmpty() || pass.isEmpty() || confirmPass.isEmpty()) {
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

        userList.add(new User(user, email, pass));
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
