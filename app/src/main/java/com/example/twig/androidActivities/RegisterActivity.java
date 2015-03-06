package com.example.twig.androidActivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.twig.controllers.AppController;
import com.example.twig.controllers.UserController;
import com.example.twig.finalproject.R;

/**
 * The activity in which the user registers for an account.
 *
 * @author Andrew
 */
public class RegisterActivity extends Activity {
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
     * Method called when the cancel button is pressed.
     *
     * @param view - the cancel button
     */
    public void cancelPressed(View view) {
        Intent intent = new Intent(this, WelcomeActivity.class);
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

        //TODO: remove this before the final due date. this is just a hack to delete all data.
        if(user.equalsIgnoreCase("Delete All Data") && email.isEmpty() && pass.equals("Team 57") && confirmPass.isEmpty()) {
            AppController.getAppController().deleteAllData(this);
            return;
        }

        boolean success = UserController.getUserController().registerUser(user, pass, confirmPass, email, this);

        if (success) {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.putExtra("FROM_REGISTER", user);
            startActivity(intent);
        }
    }

    /**
     * Display message upon invalid registration.
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
