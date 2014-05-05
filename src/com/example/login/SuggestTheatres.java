package com.example.login;




import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.zip.DataFormatException;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
 
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class SuggestTheatres extends ListActivity implements LocationListener{

	private ProgressDialog pDialog;
	private LocationManager locationManager;
	  private String provider;
	  private GoogleMap map;
	  private Location location;
	 double   lat;
	  double lng;
	
	//getlatlng1();
	//getlatlng();
	// URL to get theatres JSON
	static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	static Date date = new Date();
	
	//Toast.makeText (SuggestTheatres.this,dateFormat.format(date), Toast.LENGTH_SHORT).show();
	//private static String url ="http://data.tmsapi.com/v1/movies/showings?startDate="+dateFormat.format(date)+"&lat="+lat+"&lng="+lng+"&api_key=rnzbjxxy7pjbf949uqby22vz";
	private static String url;
	// JSON Node names
	
	private static final String TAG_MOVIE_NAME = "title";
	private static final String TAG_SHOWCASE = "showtimes";
	private static final String TAG_THEATRE = "theatre";
	private static final String TAG_THEATRE_NAME = "name";
	private static final String TAG_ROUTE = "Click to view route to this theatre";
	//  JSONArray
	JSONArray movies = null;

	// Hashmap for ListView
	ArrayList<HashMap<String, String>> MovieList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_movtheatrelist);
		getlatlng();
			url ="http://data.tmsapi.com/v1/movies/showings?startDate="+dateFormat.format(date)+"&lat="+lat+"&lng="+lng+"&api_key=rnzbjxxy7pjbf949uqby22vz";

	    //Log.d("Latitude",">"+lat);

		MovieList = new ArrayList<HashMap<String, String>>();
		
		ListView lv = getListView();

		// Listview on item click listener
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// getting values from selected ListItem
				String name = ((TextView) view.findViewById(R.id.theatren))
						.getText().toString();
		

				// Starting single contact activity
				/*Intent in = new Intent(getApplicationContext(),
						Routemap.class);
				in.putExtra(TAG_THEATRE_NAME, name);
				startActivity(in);*/
				Intent intent = new Intent(android.content.Intent.ACTION_VIEW, 
	                    Uri.parse("http://maps.google.com/maps?saddr="+lat+","+lng+"&daddr="+name));
	            startActivity(intent);

			}
		});
		
		
		// Calling async task to get json
		new GetMovieTheatres().execute();
	}

	public void getlatlng()
	{
		/*locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		//Define the criteria how to select the locatioin provider -> use
//	    // default
//     
   Criteria criteria = new Criteria();
provider = locationManager.getBestProvider(criteria, false);
//Location location =locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,SuggestTheatres.this);
 Location location = locationManager.getLastKnownLocation(provider);
		Toast.makeText (SuggestTheatres.this,"Inside getlatlng", Toast.LENGTH_SHORT).show();

		//lat =   (location.getLatitude());
	    //lng =  (location.getLongitude());
	  //  Log.d("Latitude",">"+lat);
	   // Log.d("Latitude",">");
*/	
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
	    // Define the criteria how to select the locatioin provider -> use
	    // default
		//getlatlng();
		//double  lati=lat;
		//double longi=lng;
	    Criteria criteria = new Criteria();

	    provider = locationManager.getBestProvider(criteria, true);
	   // locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,SuggestTheatres.this);
	    
	     location = locationManager.getLastKnownLocation(provider);
	     if(location==null)
	    	 location=locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
	
	  
		 lat =  (location.getLatitude());
	       lng =  (location.getLongitude());
	
		}
	
	private class GetMovieTheatres extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Showing progress dialog
			pDialog = new ProgressDialog(SuggestTheatres.this);
			pDialog.setMessage("Please wait...");
			pDialog.setCancelable(false);
			pDialog.show();

		}

		@Override
		protected Void doInBackground(Void... arg0) {
			// Creating service handler class instance
			ServiceHandler sh = new ServiceHandler();

			// Making a request to url and getting response
		String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);
		// Log.d("Latitude" , ">" +lat);	
		Log.d("Response: ", "> " + jsonStr.length());

			if (jsonStr != null) {
				try {
					//JSONObject jsonObj = new JSONObject(jsonStr);
					
					//JSONArray movies = new JSONArray(jsonStr);
				//	JSONObject last = timeline.getJSONObject(0);
					
					  
					  movies = new JSONArray(jsonStr);
					Log.d("movies: ", "> " + movies.length());
					// looping through All THEATRES&MOVIES
					for (int i = 0; i < movies.length(); i++) {
						JSONObject c = movies.getJSONObject(i);
					//	c.getString("showtimes");
						//String id = c.getString("tmsID");
						//String name = c.getString("title");
					
						String mv = c.getString(TAG_MOVIE_NAME);
						
						JSONArray theatre = c.getJSONArray(TAG_SHOWCASE);
						JSONObject theatre1 = theatre.getJSONObject(i);
						
						String name = theatre1.getString(TAG_THEATRE);
						JSONObject tn = new JSONObject(name);
						String tname = tn.getString(TAG_THEATRE_NAME);
						
						
						HashMap<String, String> thtr = new HashMap<String, String>();

						// adding each child node to HashMap key => value
						
						thtr.put(TAG_MOVIE_NAME, mv);
						thtr.put(TAG_THEATRE_NAME, tname);
						thtr.put(TAG_ROUTE, TAG_ROUTE);
						
						MovieList.add(thtr);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			} else {
				Log.e("ServiceHandler", "Couldn't get any data from the url");
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			// Dismiss the progress dialog
			if (pDialog.isShowing())
				pDialog.dismiss();
			
			ListAdapter adapter = new SimpleAdapter(
					SuggestTheatres.this, MovieList,
					
					R.layout.movthrlist, new String[] { TAG_MOVIE_NAME, TAG_THEATRE_NAME
							,TAG_ROUTE}, new int[] { R.id.movien,
							R.id.theatren,R.id.clickn});

			setListAdapter(adapter);
		}

	}

	@Override
	public void onLocationChanged(Location arg0) {
		/*locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		//Define the criteria how to select the locatioin provider -> use
//	    // default
//     
   Criteria criteria = new Criteria();
provider = locationManager.getBestProvider(criteria, false);
 Location location = locationManager.getLastKnownLocation(provider);
		Toast.makeText (SuggestTheatres.this,"INside getlatlng", Toast.LENGTH_SHORT).show();*/
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
	    // Define the criteria how to select the locatioin provider -> use
	    // default
		//getlatlng();
		//double  lati=lat;
		//double longi=lng;
	    Criteria criteria = new Criteria();

	    provider = locationManager.getBestProvider(criteria, true);
	    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,SuggestTheatres.this);
	    
	     location = locationManager.getLastKnownLocation(provider);
	     if(location==null)
	    	 location=locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
	
	  
		 lat =  (location.getLatitude());
	       lng =  (location.getLongitude());
	

	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		/*locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		//Define the criteria how to select the locatioin provider -> use
//	    // default
//     
   Criteria criteria = new Criteria();
provider = locationManager.getBestProvider(criteria, false);
 Location location = locationManager.getLastKnownLocation(provider);
		Toast.makeText (SuggestTheatres.this,"INside getlatlng", Toast.LENGTH_SHORT).show();

		lat =  (location.getLatitude());
	    lng =  (location.getLongitude());
		*/
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		/*locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		//Define the criteria how to select the locatioin provider -> use
//	    // default
//     
   Criteria criteria = new Criteria();
provider = locationManager.getBestProvider(criteria, false);
 Location location = locationManager.getLastKnownLocation(provider);
		Toast.makeText (SuggestTheatres.this,"INside getlatlng", Toast.LENGTH_SHORT).show();

		lat =  (location.getLatitude());
	    lng =  (location.getLongitude());
		*/
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

}