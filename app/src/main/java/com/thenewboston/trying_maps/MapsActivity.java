package com.thenewboston.trying_maps;



import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;


import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    EditText mapSearchBox;
    double longi,lati;
    String place = null;
    int flag=0;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        db= new DatabaseHandler(this);
        //mMap = (GoogleMap) findViewById(R.id.map);
        mMap= ((SupportMapFragment) getSupportFragmentManager().findFragmentById(
                R.id.map)).getMap();

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    public void Clicked(View v)
    {
        switch (v.getId())
        {
            case R.id.search_c:
                GPSTracker gps = new GPSTracker(this);
                if(gps.canGetLocation()){
                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();

                    db.addCoordinate(new SqlCoordinates(Double.toString(latitude),Double.toString(longitude)));
                    Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                    LatLng now = new LatLng(latitude,longitude);
                    mMap.addMarker(new MarkerOptions().position(now).title("Now!!"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(now));
                    mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);


                }
                break;
            case R.id.plot:
                ArrayList<LatLng>  laln = new ArrayList<>();
                //Toast.makeText(this,"Clicked!",Toast.LENGTH_LONG).show();

                List<SqlCoordinates> coordinates = db.getAllCoordinates();
                for (int i=0;i<coordinates.size();i++) {
                    String log = "Longi: " + coordinates.get(i).getLongi() + " ,Lati: " + coordinates.get(i).getLati();
                    // Writing Contacts to log
                    Log.d("Name: ", log);
                    //Toast.makeText(this,log,Toast.LENGTH_LONG).show();
                    //mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                    //mMap.addPolyline(new PolylineOptions().geodesic(true).add(new LatLng(Double.parseDouble(cn.getLati()),Double.parseDouble(cn.getLongi()))));*/
                    double srclat=Double.parseDouble(coordinates.get(i).getLati());
                    double srclong=Double.parseDouble(coordinates.get(i).getLongi());
                    //double destlat=Double.parseDouble(coordinates.get(i+1).getLati());
                    //double destlong=Double.parseDouble(coordinates.get(i+1).getLongi());
                    LatLng src = new LatLng(srclat,srclong);
                    //LatLng dest= new LatLng(destlat,destlong);
                    laln.add(src);

                }
                PolylineOptions polylineOptions = new PolylineOptions();

// Create polyline options with existing LatLng ArrayList
                polylineOptions.addAll(laln);
                polylineOptions
                        .width(5)
                        .color(Color.RED).geodesic(true);
                mMap.addPolyline(polylineOptions);
                break;




        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(lati,longi);
        Log.e("ADARSH_SYDNEY",Double.toString(sydney.longitude));
        mMap.addMarker(new MarkerOptions().position(sydney).title("Bangalore"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mMap.addPolyline(new PolylineOptions().geodesic(true)
                .add(new LatLng(-33.866, 151.195))  // Sydney
                .add(new LatLng(12.9667, 77.5667))  // Fiji
                .add(new LatLng(21.291, -157.821))  // Hawaii
                .add(new LatLng(37.423, -122.091)));
    }


    @Override
    public void onBackPressed() {
        finish();
    }
}

