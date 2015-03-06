package com.example.twig.androidActivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.twig.controllers.SaleController;
import com.example.twig.dataObjects.Sale;
import com.example.twig.finalproject.R;

/**
 * Activity that displays a list of all of the sales that the current user has reported.
 *
 * Created by Piyakorn on 3/5/2015.
 */
public class SalesListActivity extends Activity {
    private TextView txt;
    private Button add;
    private ListView sales;

    /**
     * Called upon activity creation. Populates the list of sales,
     * and displays how many sales you have reported.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saleslist);

        txt = (TextView) findViewById(R.id.num_sales);
        sales = (ListView) findViewById(R.id.salesList);

        SaleController saleController = SaleController.getSaleController();

        ArrayAdapter<Sale> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,
                saleController.getSaleList());
        sales.setAdapter(adapter);

        txt.setText("You have reported " + saleController.saleListSize() + " sale"
                + ((saleController.saleListSize() == 1) ? "." : "s."));

    }

    /**
     * Called when add sales is pressed. Launches Sale Report Activity.
     *
     * @param view add sales button
     */
    public void addSalePressed(View view) {
        Intent intent = new Intent(this, SalesReportActivity.class);
        startActivity(intent);
    }

    /**
     * Called when back is pressed. Returns to application activity.
     *
     * @param view the back button
     */
    public void backPressed(View view) {
        Intent intent = new Intent(this, ApplicationActivity.class);
        startActivity(intent);
    }
}