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

        LatLng currentStoreLocation = new LatLng(53.349805, -6.26031); // Dublin, Ireland
        googleMap.addMarker(new MarkerOptions().position(currentStoreLocation).title("Nearby Store in Dublin"));


        LatLng nearbyStore1 = new LatLng(53.346396, -6.264641);
        googleMap.addMarker(new MarkerOptions().position(nearbyStore1).title("Nearby Store in Dublin"));


        LatLng nearbyStore2 = new LatLng(53.349055, -6.268348);
        googleMap.addMarker(new MarkerOptions().position(nearbyStore2).title("Nearby Store in Dublin"));


        LatLng nearbyStore3 = new LatLng(53.352362, -6.265091);
        googleMap.addMarker(new MarkerOptions().position(nearbyStore3).title("Nearby Store in Dublin"));


        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentStoreLocation, 15)); // Zoom level 1-20
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
