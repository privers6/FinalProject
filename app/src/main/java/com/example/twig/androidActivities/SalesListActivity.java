package com.example.twig.androidActivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.twig.dataObjects.CurrentUser;
import com.example.twig.dataObjects.Interest;
import com.example.twig.dataObjects.Sale;
import com.example.twig.finalproject.R;

import java.util.ArrayList;

/**
 * Created by Piyakorn on 3/5/2015.
 */
public class SalesListActivity extends Activity {
    private TextView txt;
    private Button add;
    private ListView sales;
    /**
     * Called upon activity creation.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saleslist);

        ArrayList<Sale> salelist = CurrentUser.getCurrentUser().getSaleList();
        txt = (TextView) findViewById(R.id.num_sales);
        add = (Button) findViewById(R.id.add_button);
        sales = (ListView) findViewById(R.id.salesList);

        ArrayAdapter<Sale> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,
                salelist);
        sales.setAdapter(adapter);

        txt.setText("You have " + salelist.size() + " current sales reported by you.");

    }

    public void addSalePressed(View view) {
        Intent intent = new Intent(this, SalesReportActivity.class);
        startActivity(intent);
    }

    public void backPressed(View view) {
        Intent intent = new Intent(this, ApplicationActivity.class);
        startActivity(intent);
    }
}
