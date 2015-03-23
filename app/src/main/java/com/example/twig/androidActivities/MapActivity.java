package com.example.twig.androidActivities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import com.google.android.gms.maps.*;
import com.example.twig.finalproject.R;

/**
 * Created by Andrew on 3/22/2015.
 */
public class MapActivity extends Activity {
    /**
     * Constant LatLng's of places that may be needed
     */
    final LatLng ATLANTA_POS = new LatLng(33.7550, -84.3900);
    /**
     * Local variables *
     */
    GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        createMapView();

        MarkerOptions marker = new MarkerOptions();
        marker.position(ATLANTA_POS);
        marker.draggable(true);
        googleMap.addMarker(marker);

        centerOnLatLng(ATLANTA_POS, 12.0f);
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


    /**
     * Centers the map on the specified latitude and longitude,
     * with the specified zoom level
     */
    private void centerOnLatLng(LatLng pos, float zoomLevel) {
        CameraUpdate movement = CameraUpdateFactory.newLatLngZoom(pos, zoomLevel);

        googleMap.moveCamera(movement);
    }
}
