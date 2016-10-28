package com.example.gpsdemo;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity implements LocationListener {

	TextView locationLL;
	
	LocationManager locMan;
	
	Criteria criteria;
	
	String locProvider="";
	
	Geocoder geocoder;
	
	TextView address;
	
	//double longitude, latitude;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		locationLL = (TextView)findViewById(R.id.textView1);
		address = (TextView)findViewById(R.id.textView2);
		
		locMan = (LocationManager)getSystemService(LOCATION_SERVICE);
		criteria = new Criteria();
		//Default criteria: low power
		locProvider = "network";
		
		geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		locMan.requestLocationUpdates(locProvider, 2000, 10, this);
		
	}

	@Override
	public void onLocationChanged(Location arg0) {
		// TODO Auto-generated method stub
		
	//	latitude = arg0.getLatitude();
		//longitude = arg0.getLongitude();
		
		locationLL.setText("location: (" + arg0.getLatitude() + ", " + arg0.getLongitude() + ")");
		
		GeocodeTask geocodetask = new GeocodeTask();
		try {
			String adds = (geocodetask.execute(arg0.getLatitude(), arg0.getLongitude())).get();
			address.setText(adds);
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
		
	}
	
	class GeocodeTask extends AsyncTask<Double, Integer, String>{

		@Override
		protected String doInBackground(Double... arg0) {
			// TODO Auto-generated method stub
			
			String result = null;
			List<Address> list = null;
			Address address;
			
			try {
				
				list = geocoder.getFromLocation(arg0[0], arg0[1], 5);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			
			if(list != null && list.size() > 0){
				address = list.get(0);
				result = address.getAddressLine(0);
				result += address.getAddressLine(1);
				result += " " + address.getAddressLine(2);
			}
			
			
			return result;
		}
		
	}
}
