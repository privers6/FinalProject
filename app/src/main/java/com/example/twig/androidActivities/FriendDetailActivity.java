package com.example.twig.androidActivities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.twig.dataObjects.UserList;
import com.example.twig.finalproject.R;

/**
 * Created by Andrew on 2/26/2015.
 */
public class FriendDetailActivity extends Activity {

    /**
     * Called upon activity creation. Sets content view
     * and reads in all persisent data.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frienddetail);

        TextView name = (TextView)findViewById(R.id.txtName);
        TextView email = (TextView)findViewById(R.id.txtEmail);
        TextView rating = (TextView)findViewById(R.id.txtRating);
        TextView salesReported = (TextView)findViewById(R.id.txtSalesReported);
    }
}
