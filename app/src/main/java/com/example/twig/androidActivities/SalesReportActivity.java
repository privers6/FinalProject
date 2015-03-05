package com.example.twig.androidActivities;

import android.content.Intent;
import android.view.View;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import com.example.twig.dataObjects.Sale;
import com.example.twig.dataObjects.User;
import com.example.twig.finalproject.R;
import com.example.twig.dataObjects.CurrentUser;
import com.example.twig.dataObjects.SaleList;


/**
 * Created by Piyakorn on 3/3/2015.
 */
public class SalesReportActivity extends Activity{
    /**
     * Called upon activity creation.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salesreport);
    }

    /**
     * Method called when the back button is pressed.
     *
     * @param view - the cancel button
     */
    public void backPressed(View view) {
        Intent intent = new Intent(this, ApplicationActivity.class);
        startActivity(intent);
    }

    /**
     * Method called when the register button is pressed.
     *
     * @param view - the register button
     */
    public void salesReportPressed(View view) {
        String name = ((EditText)findViewById(R.id.sale_name_field)).getText().toString();
        String price = ((EditText)findViewById(R.id.sale_price_field)).getText().toString();
        String location = ((EditText)findViewById(R.id.sale_location_field)).getText().toString();
        TextView text = ((TextView)findViewById(R.id.message));

        ArrayList<Sale> saleList = SaleList.getSaleList();

        if(name.isEmpty() || price.isEmpty() || location.isEmpty()) {
            text.setText("One or more fields are empty");
            text.setVisibility(View.VISIBLE);
            return;
        }

        double priceValue = Double.parseDouble(price);

        if(priceValue <= 0) {
            text.setText("Max item price must be greater than 0");
            text.setTextColor(0xFFFF0000);
            text.setVisibility(View.VISIBLE);
            return;
        }

        for (Sale s : saleList) {
            if (name.equalsIgnoreCase(s.getName()) && priceValue > s.getPrice()) {
                text.setText("There's already a better deal reported for this item.");
                text.setTextColor(0xFFFF0000);
                text.setVisibility(View.VISIBLE);
                return;
            }
        }

        User c = CurrentUser.getCurrentUser();
        c.setSalesReported(c.getSalesReported());

        text.setText("Sale added.");
        text.setTextColor(0xFF00FF00);
        text.setVisibility(View.VISIBLE);

        saleList.add(new Sale(name, priceValue, location));
        SaleList.saveSaleList();

        for (Sale s : saleList) {
            System.out.print(s.getName());
        }

        Intent intent = new Intent(this, SalesReportActivity.class);
        startActivity(intent);

    }
}
