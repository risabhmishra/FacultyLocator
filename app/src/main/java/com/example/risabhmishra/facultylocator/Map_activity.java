package com.example.risabhmishra.facultylocator;

import android.*;
import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Point;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmadrosid.lib.drawroutemap.DrawMarker;
import com.ahmadrosid.lib.drawroutemap.DrawRouteMaps;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.List;

import static android.app.PendingIntent.getActivity;

public class Map_activity extends FragmentActivity implements OnMapReadyCallback {

    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 100;
    private static final float DEFAULT_ZOOM = 2;
    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    boolean mLocationPermissionGranted;
    Location mLastKnownLocation;
    CameraPosition mCameraPosition;
    double currentLatitude, currentLongitude;
    LatLng destination;
    LatLng origin;//= new LatLng(12.823061, 80.043796);
    LatLng University_Building = new LatLng(12.823260, 80.042579);
    LatLng Tech_Park = new LatLng(12.824870, 80.045116);
    LatLng BioTech = new LatLng(12.824693, 80.044010);
    LatLng High_Tech = new LatLng(12.820973, 80.038916);
    LatLng Electrical_Science = new LatLng(12.819884, 80.039316);
    private LocationManager locationManager;
    private LocationListener listener;

String building;
    String dest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_activity);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        Bundle bundle = getIntent().getExtras();
        dest = bundle.getString("Location");
        building = bundle.getString("Location").trim().toUpperCase();

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        mMap.setMyLocationEnabled(true);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(BioTech, 15));

        switch (building) {
            case "TECHPARK":
                destination = Tech_Park;
                mMap.addMarker(new MarkerOptions().position(destination).title(dest).icon(
                        BitmapDescriptorFactory.defaultMarker()));

                break;
            case "UNIVERSITYBUILDING":
                destination = University_Building;
                mMap.addMarker(new MarkerOptions().position(destination).title(dest).icon(
                        BitmapDescriptorFactory.defaultMarker()));

                break;
            case "BIOTECH BLOCK":
                destination = BioTech;
                mMap.addMarker(new MarkerOptions().position(destination).title(dest).icon(
                        BitmapDescriptorFactory.defaultMarker()));


                break;
            case "HIGHTECH BLOCK":

                destination = High_Tech;
                mMap.addMarker(new MarkerOptions().position(destination).title(dest).icon(
                        BitmapDescriptorFactory.defaultMarker()));

                break;
            case "ELECTRICAL SCIENCE BLOCK":
                destination = Electrical_Science;
                mMap.addMarker(new MarkerOptions().position(destination).title(dest).icon(
                        BitmapDescriptorFactory.defaultMarker()));

                break;
        }



    }

}