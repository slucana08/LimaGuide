package com.example.sting.limaguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Displays a map with the location selected, information to set location was passed through an
 * intent
 */

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    LatLng place;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // Initialize map fragment
        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().
                findFragmentById(R.id.mapFragment);
        supportMapFragment.getMapAsync(this);

        // Get information passed through the intent
        Intent intent = getIntent();
        double latitude = intent.getDoubleExtra("latitude",0.0);
        double longitude = intent.getDoubleExtra("longitude",0.0);
        title = intent.getStringExtra("title");
        place = new LatLng(latitude,longitude);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Set location on map with information obtained from the intent
        googleMap.clear();
        googleMap.setMinZoomPreference(6f);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(place, 16.0f));
        Marker marker = googleMap.addMarker(new MarkerOptions().position(place).title(title));
        marker.showInfoWindow();
    }
}
