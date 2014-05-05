package com.example.login;
import java.io.IOException;
import java.util.List;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class Routemap extends Activity {
	private LocationManager locationManager;
	  private String provider;
	  private GoogleMap map;
	//  private static final String TAG_MOVIE_NAME = "movie_name";
		private static final String TAG_THEATRE_NAME = "name";
		//private static final String TAG_PHONE_MOBILE = "mobile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);
        // Getting Theatre_Name
Intent in = getIntent();
        
        // Get JSON values from previous intent
       // String movie_name = in.getStringExtra(TAG_MOVIE_NAME);
String theatre_name = in.getExtras().getString(TAG_THEATRE_NAME);
        //String theatre_name = in.getStringExtra(TAG_THEATRE_NAME);
        //theatre_name = "Columbus Crew Stadium";
    	Toast.makeText (Routemap.this,theatre_name, Toast.LENGTH_SHORT).show();
        Log.d("Response: ", "> " + theatre_name);
        List<Address> foundGeocode = null;
        /* find the addresses  by using getFromLocationName() method with the given address*/
        try {
			foundGeocode = new Geocoder(this).getFromLocationName(theatre_name, 1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        double tlat=foundGeocode.get(0).getLatitude(); //getting latitude
        double tlong=foundGeocode.get(0).getLongitude();//getting longitude
        map=((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
	//     Define the criteria how to select the locatioin provider -> use
	    // default
        
	    Criteria criteria = new Criteria();
	    provider = locationManager.getBestProvider(criteria, false);
	    Location location = locationManager.getLastKnownLocation(provider);
	    double lat =  (location.getLatitude());
	    double lng =  (location.getLongitude());
	    LatLng loc=new LatLng(lat,lng);
	    LatLng tloc=new LatLng(tlat,tlong);
	    Polyline line=map.addPolyline(new PolylineOptions().add(loc,tloc).width(5).color(Color.RED));
	    CameraUpdate update=CameraUpdateFactory.newLatLngZoom(loc, 14);
	    map.animateCamera(update);
	    map.addMarker(new MarkerOptions().position(loc).title("You"));
	    map.addMarker(new MarkerOptions().position(tloc).title(theatre_name));
	  

    }
   /* void displayRoute() {

         LatLng start = new LatLng(lat, lng);
        LatLng end = new LatLng(tlat, tlong);

        var directionsDisplay = new DirectionsRenderer();// also, constructor can get "DirectionsRendererOptions" object
        directionsDisplay.setMap(map); // map should be already initialized.

        var request = {
            origin : start,
            destination : end,
            travelMode : google.maps.TravelMode.DRIVING
        };
        directionsService.route(request, function(response, status) {
            if (status == google.maps.DirectionsStatus.OK) {
                directionsDisplay.setDirections(response);
            }
        });
    }*/
}


