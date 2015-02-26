package com.example.twig.androidActivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import com.example.twig.dataObjects.Interest;
import com.example.twig.finalproject.R;
import com.example.twig.dataObjects.CurrentUser;

/**
 * Created by Porter Rivers on 2/26/2015.
 */
public class InterestListActivity extends Activity{
    private TextView txt;
    private Button add;
    private ListView interests;
    /**
     * Called upon activity creation.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interestlist);

        ArrayList<Interest> interestList = CurrentUser.getCurrentUser().getInterestList();
        txt = (TextView) findViewById(R.id.txtNumInterests);
        add = (Button) findViewById(R.id.btnAdd);
        interests = (ListView) findViewById(R.id.interestList);

        ArrayAdapter<Interest> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,
                interestList);
        interests.setAdapter(adapter);

        txt.setText("You have " + interestList.size() + " current interests.");
    }

    public void addInterestPressed(View view) {
        Intent intent = new Intent(this, RegisterInterestActivity.class);
        startActivity(intent);
    }

    public void backPressed(View view) {
        Intent intent = new Intent(this, ApplicationActivity.class);
        startActivity(intent);
    }
}

