package com.example.bloodgroup;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;
	
	public class GPSTracker extends Service implements LocationListener {
		 
	    private final Context mContext;
	 
	    // flag for GPS status
	    boolean isGPSEnabled = false;
	 
	    // flag for network status
	    boolean isNetworkEnabled = false;
	 
	    boolean canGetLocation = false;
	 
	    Location location; // location
	    double latitude; // latitude
	    double longitude; // longitude
	 
	    // The minimum distance to change Updates in meters
	    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters
	 
	    // The minimum time between updates in milliseconds
	    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute
	 
	    // Declaring a Location Manager
	    protected LocationManager locationManager;
	 
	    public GPSTracker(Context context) {
	    	Toast.makeText(context, "outside getlocation", Toast.LENGTH_SHORT).show();
	        this.mContext = context;
	        Log.d("harsha", "inside gps");
	        //This
	        //Toast.makeText(getApplicationContext(), "outside getlocation", Toast.LENGTH_SHORT).show();
	        getLocation(context);
	    }

		
	    
	    public String GetAddressFromCoordinates(double longitide,double latitude,Context context2){
	    
	    	
	    	Toast.makeText(context2, "GetAddressFromCoordinates", Toast.LENGTH_SHORT).show();
	    	
	    	 Geocoder geocoder = new Geocoder(this, Locale.getDefault());   
	            String result = null;
	            try {
	                List<Address> list = geocoder.getFromLocation(
	                        latitude,longitide, 1);
	                Toast.makeText(context2,"inside try", Toast.LENGTH_SHORT).show();
	                if (list != null && list.size() > 0) {
	                	Toast.makeText(context2, list.size(), Toast.LENGTH_SHORT).show();
	                    Address address = list.get(0);
	                    // sending back first address line and locality
	                    result = address.getAddressLine(0) + ", " + address.getLocality();
	                    Toast.makeText(context2, result, Toast.LENGTH_SHORT).show();
	        	    	
	                    
	                }
	            } catch (IOException e) {
	                Log.d("harsha", "Exception inside Getaddress", e);
	                Toast.makeText(context2, e.toString(), Toast.LENGTH_SHORT).show();
	            }
	            
	            
	    	return result;
	    }
	    
	    
	    /* 
	     * 
	     * 
	    

	   
	    
	    Geocoder geocoder = new Geocoder(context, Locale.getDefault());   
            String result = null;
            try {
                List<Address> list = geocoder.getFromLocation(
                        location.getLatitude(), location.getLongitude(), 1);
                if (list != null && list.size() > 0) {
                    Address address = list.get(0);
                    // sending back first address line and locality
                    result = address.getAddressLine(0) + ", " + address.getLocality();
                }
            } catch (IOException e) {
                Log.e(TAG, "Impossible to connect to Geocoder", e);

	    
	    
	    
	    
	    
	    
	    */
		public Location getLocation(Context context1) {
			getReverseGeoCoding obj = new getReverseGeoCoding();
	        try {
	            locationManager = (LocationManager) mContext
	                    .getSystemService(LOCATION_SERVICE);
	            Log.d("Network1", "Network1");
	            Toast.makeText(context1, "Network not enabled", Toast.LENGTH_SHORT).show();
	            // getting GPS status
	            isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
	 
	            // getting network status
	            isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
	 
	            if (!isGPSEnabled && !isNetworkEnabled) {
	                // no network provider is enabled
	     
	            	Toast.makeText(context1, "Network not enabled", Toast.LENGTH_SHORT).show();
	            } else {
	                this.canGetLocation = true;
	                // First get location from Network Provider
	                
	                
	                if (isNetworkEnabled) {
	                	
	                	Toast.makeText(context1, "NetworkEnabled", Toast.LENGTH_SHORT).show();
	                    locationManager.requestLocationUpdates(
	                            LocationManager.NETWORK_PROVIDER,
	                            MIN_TIME_BW_UPDATES,
	                            MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
	                    Log.d("Network", "Network");
	                    if (locationManager != null) {
	                        location = locationManager
	                                .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
	                        if (location != null) {
	                            latitude = location.getLatitude();
	                            longitude = location.getLongitude();
	                            Toast.makeText(context1, "" + longitude + " " + latitude, Toast.LENGTH_SHORT).show();
	                            //GetAddressFromCoordinates(longitude,latitude,context1);
	                            obj.getAddress(longitude,latitude,context1);
	                        }
	                    }
	                }
	                // if GPS Enabled get lat/long using GPS Services
	                if (isGPSEnabled) {
	                	
	                	Toast.makeText(context1, "NetworkEnabled", Toast.LENGTH_SHORT).show();
	                    if (location == null) {
	                        locationManager.requestLocationUpdates(
	                                LocationManager.GPS_PROVIDER,
	                                MIN_TIME_BW_UPDATES,
	                                MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
	                        Log.d("GPS Enabled", "GPS Enabled");
	                        if (locationManager != null) {
	                            location = locationManager
	                                    .getLastKnownLocation(LocationManager.GPS_PROVIDER);
	                            if (location != null) {
	                                latitude = location.getLatitude();
	                                
	                              Log.d("harsha",""+latitude);
	                                longitude = location.getLongitude();
	                                Log.d("harsha",""+longitude);
	                                Toast.makeText(context1, "" + longitude + " " + latitude, Toast.LENGTH_SHORT).show();
	                                //GetAddressFromCoordinates(longitude,latitude,context1);
	                        	    obj.getAddress(longitude,latitude,context1);
	                            }
	                        }
	                    }
	                }
	            }
	 
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	 
	        return location;
	    }
	    @Override
	    public void onLocationChanged(Location location) {
	    }
	 
	    @Override
	    public void onProviderDisabled(String provider) {
	    }
	 
	    @Override
	    public void onProviderEnabled(String provider) {
	    }
	 
	    @Override
	    public void onStatusChanged(String provider, int status, Bundle extras) {
	    }
	 
	    @Override
	    public IBinder onBind(Intent arg0) {
	        return null;
	    }
		
		
		
		
		
		
}
