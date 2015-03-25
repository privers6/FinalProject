package com.example.twig.androidActivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.twig.finalproject.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Activity that allows the user to view the location of a previously reported sale.
 *
 * Created by Andrew on 3/25/2015.
 */
public class MapViewActivity extends Activity {
    GoogleMap googleMap;
    MarkerOptions marker;

    /**
     * Called on Activity creation. Centers the map
     * on the specified latitude and longitude.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapview);
        createMapView();

        String latitude = getIntent().getStringExtra("LATITUDE");
        String longitude = getIntent().getStringExtra("LONGITUDE");
        LatLng pos = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));

        marker = new MarkerOptions();
        marker.position(pos);
        marker.draggable(false);
        googleMap.addMarker(marker);

        centerOnLatLng(pos, 12.0f);
    }


    /**
     * Centers the map on the specified latitude and longitude,
     * with the specified zoom level
     */
    private void centerOnLatLng(LatLng pos, float zoomLevel) {
        CameraUpdate movement = CameraUpdateFactory.newLatLngZoom(pos, zoomLevel);

        googleMap.moveCamera(movement);
    }

    /**
     * Called when Return is pressed. Brings user back to the application activity
     * that launched this activity.
     */
    public void returnPressed(View view) {
        finish();
        //startActivity(new Intent(this, ApplicationActivity.class));
    }


    /**
     * Initialises the mapview
     */
    private void createMapView() {
        /**
         * Catch the null pointer exception that
         * may be thrown when initialising the map
         */
        try {
            if (null == googleMap) {
                googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                        R.id.mapView)).getMap();

                /**
                 * If the map is still null after attempted initialisation,
                 * show an error to the user
                 */
                if (null == googleMap) {
                    Toast.makeText(getApplicationContext(),
                            "Error creating map", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (NullPointerException exception) {
            Log.e("mapApp", exception.toString());
        }
    }
}
