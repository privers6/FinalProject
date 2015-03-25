package com.example.twig.androidActivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import com.google.android.gms.maps.*;
import com.example.twig.finalproject.R;

/**
 * Class that allows user to place a marker on a map, specifying the location
 * of a sale.
 *
 * Created by Andrew on 3/22/2015.
 */
public class MapPlaceActivity extends Activity implements GoogleMap.OnMarkerDragListener {
    /**
     * Constant LatLng's of places that may be needed
     */
    final LatLng ATLANTA_POS = new LatLng(33.7550, -84.3900);
    /**
     * Local variables *
     */
    GoogleMap googleMap;
    MarkerOptions marker;
    double latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapplace);
        createMapView();

        String strLat = getIntent().getStringExtra("LATITUDE");
        String strLong = getIntent().getStringExtra("LONGITUDE");

        LatLng pos;

        if(strLat != null && strLong != null) {
            latitude = Double.parseDouble(strLat);
            longitude = Double.parseDouble(strLong);
            pos = new LatLng(latitude, longitude);
        } else {
            pos = ATLANTA_POS;
            latitude = ATLANTA_POS.latitude;
            longitude = ATLANTA_POS.longitude;
        }

        marker = new MarkerOptions();
        marker.position(pos);
        marker.draggable(true);
        googleMap.addMarker(marker);
        googleMap.setOnMarkerDragListener(this);

        centerOnLatLng(pos, 12.0f);
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
     * Called when confirm is pressed. Goes back to Sale Report activity that launched it
     * and passes it the lat and long strings.
     * @param view
     */
    public void confirmPressed(View view) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("LATITUDE", "" + latitude);
        returnIntent.putExtra("LONGITUDE", "" + longitude);
        setResult(RESULT_OK, returnIntent);

        finish();
    }

    /**
     * Called when cancel is pressed. Goes back to Sale Report activity that launched it,
     * nothing gets changed.
     * @param view
     */
    public void cancelPressed(View view) {
        Intent returnIntent = new Intent();
        setResult(RESULT_CANCELED, returnIntent);
        finish();
    }


    /**
     * Centers the map on the specified latitude and longitude,
     * with the specified zoom level
     */
    private void centerOnLatLng(LatLng pos, float zoomLevel) {
        CameraUpdate movement = CameraUpdateFactory.newLatLngZoom(pos, zoomLevel);

        googleMap.moveCamera(movement);
    }

    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker m) {
        latitude = m.getPosition().latitude;
        longitude = m.getPosition().longitude;
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(m.getPosition()));
    }
}
