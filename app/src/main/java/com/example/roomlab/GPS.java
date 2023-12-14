package com.example.roomlab;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import android.util.Log;


public class GPS extends AppCompatActivity implements OnMapReadyCallback {

    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);

        mapView = findViewById(R.id.mapView);
        if (mapView != null) {
            mapView.onCreate(savedInstanceState);
            mapView.getMapAsync(this);
        } else {
            Log.e("GPS Activity", "MapView is null");
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Add marker for a nearby store (sample location)
        LatLng storeLocation = new LatLng(37.7749, -122.4194); // Example: San Francisco
        googleMap.addMarker(new MarkerOptions().position(storeLocation).title("Nearby Store"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(storeLocation, 12)); // Zoom level 1-20
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}
