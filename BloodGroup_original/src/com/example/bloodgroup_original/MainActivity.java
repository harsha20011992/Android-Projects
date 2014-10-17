package com.example.bloodgroup_original;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.text.InputFilter.LengthFilter;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		GPSTracker GPSObj = new GPSTracker(getBaseContext());
		 Location location = GPSObj.getLocation(getBaseContext());
		if(location == null)
		{
			Toast.makeText(getBaseContext(), "LOCATION SERVICE/GPS IS NOT ENABLED", Toast.LENGTH_SHORT);
		}
		else
		{
			//double lati = location.getLatitude();
			//double longi = location.getLongitude();
		
			GPSObj.getAddress(location.getLongitude(), location.getLatitude(), getBaseContext());
			
			
			
			
		}
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
