package com.example.twig.androidActivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.twig.controllers.InterestController;
import com.example.twig.dataObjects.Interest;
import com.example.twig.finalproject.R;

/**
 * Activity that displays a list of all the current user's interests.
 *
 * Created by Porter Rivers on 2/26/2015.
 */
public class InterestListActivity extends Activity{
    private TextView txt;
    private Button add;
    private ListView interests;

    /**
     * Called upon activity creation. Sets up list/adapter, and
     * displays number of current interests.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interestlist);

        txt = (TextView) findViewById(R.id.txtNumInterests);
        add = (Button) findViewById(R.id.btnAdd);
        interests = (ListView) findViewById(R.id.interestList);

        InterestController interestController = InterestController.getInterestController();

        ArrayAdapter<Interest> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,
                interestController.getInterestList());
        interests.setAdapter(adapter);

        txt.setText("You have " + interestController.interestListSize() + " current interests.");
    }

    /**
     * Called when the add interest button is pressed.
     *
     * @param view the add interest button
     */
    public void addInterestPressed(View view) {
        Intent intent = new Intent(this, RegisterInterestActivity.class);
        startActivity(intent);
    }

    /**
     * Called when the back button is pressed.
     *
     * @param view the back button
     */
    public void backPressed(View view) {
        Intent intent = new Intent(this, ApplicationActivity.class);
        startActivity(intent);
    }
}

