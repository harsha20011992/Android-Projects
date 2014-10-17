package com.example.bloodgroup_original;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
	
	public class GPSTracker extends Service implements LocationListener,HttpPostCallBack {
		 
		public String Address1 = "", Address2 = "", City = "", State = "", Country = "", County = "", PIN = "";
		
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
	 
	    //constructor of GPSTracker
	    public GPSTracker(Context context) {
	    	Toast.makeText(context, "outside getlocation", Toast.LENGTH_SHORT).show();
	        this.mContext = context;
	        Log.d("harsha", "inside gps");
	        //This
	        //Toast.makeText(getApplicationContext(), "outside getlocation", Toast.LENGTH_SHORT).show();
	        getLocation(context);
	    }

		
	    
	    /*public String GetAddressFromCoordinates(double longitide,double latitude,Context context2){
	    
	    	
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
	    */
	    
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
			// getReverseGeoCoding obj = new getReverseGeoCoding();
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
	                        /*if (location != null) {
	                            latitude = location.getLatitude();
	                            longitude = location.getLongitude();
	                            Toast.makeText(context1, "" + longitude + " " + latitude, Toast.LENGTH_SHORT).show();
	                            //GetAddressFromCoordinates(longitude,latitude,context1);
	                            //obj.getAddress(longitude,latitude,context1);
	                        }*/
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
	                            /*if (location != null) {
	                                latitude = location.getLatitude();
	                                
	                              Log.d("harsha",""+latitude);
	                                longitude = location.getLongitude();
	                                Log.d("harsha",""+longitude);
	                                Toast.makeText(context1, "" + longitude + " " + latitude, Toast.LENGTH_SHORT).show();
	                                //GetAddressFromCoordinates(longitude,latitude,context1);
	                        	    //obj.getAddress(longitude,latitude,context1);
	                            }*/
	                        }
	                    }
	                }
	            }
	 
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	 
	        return location;
	    }
		
		
		
		
		public void getAddress(double longitude,double latitude,Context context) {
	    	longitude = 80.1740991;
	    	latitude =  13.0227547;
	    	Toast.makeText(context,"Test1" + longitude + " " + latitude, Toast.LENGTH_SHORT).show();
	        Address1 = "";
	        Address2 = "";
	        City = "";
	        State = "";
	        Country = "";
	        County = "";
	        PIN = "";

	        try {

	        	
	        	
	            //JSONObject jsonObj = parser_Json.getJSONfromURL("http://maps.googleapis.com/maps/api/geocode/json?latlng=" + latitude + ","
	                    //+ longitude + "&sensor=true",context);
	        	
	        	
	                    
	                    JSONObject jsonObj = null;
	                    Parser_Json Json_execute = new Parser_Json(this);
	                    Toast.makeText(context,"json" + "Test-shreya" + " " + latitude + Address1, Toast.LENGTH_SHORT).show();
	                    Json_execute.execute("http://maps.googleapis.com/maps/api/geocode/json?latlng=" + latitude + ","
	                    + longitude + "&sensor=true");
	                    String str = Json_execute.Address1;
	                    Toast.makeText(context,"json" +  Json_execute.Address1 + "Let us see", Toast.LENGTH_SHORT).show();
	            
	                    
	                    
	            /*Toast.makeText(context,"json1" + "Test1" + " " + latitude + Address1, Toast.LENGTH_SHORT).show();
	            
	            Toast.makeText(context,"Type" + longitude + " " + latitude + Address1, Toast.LENGTH_SHORT).show();
	            String Status = jsonObj.getString("status");
	            if (Status.equalsIgnoreCase("OK")) {
	            	Toast.makeText(context,"BeforeResults" + longitude + " " + latitude + Address1, Toast.LENGTH_SHORT).show();
	                JSONArray Results = jsonObj.getJSONArray("results");
	                Toast.makeText(context,"AfterResults" + longitude + " " + latitude + Address1, Toast.LENGTH_SHORT).show();
	                JSONObject zero = Results.getJSONObject(0);
	                JSONArray address_components = zero.getJSONArray("address_components");
	                Toast.makeText(context,"addresscomponents" + longitude + " " + latitude + Address1, Toast.LENGTH_SHORT).show();
	                for (int i = 0; i < address_components.length(); i++) {
	                    JSONObject zero2 = address_components.getJSONObject(i);
	                    String long_name = zero2.getString("long_name");
	                    JSONArray mtypes = zero2.getJSONArray("types");
	                    String Type = mtypes.getString(0);
	                    Log.d("Json", Type);
	                    Toast.makeText(context,"Type" + longitude + " " + latitude + Address1, Toast.LENGTH_SHORT).show();
	                    if (TextUtils.isEmpty(long_name) == false || !long_name.equals(null) || long_name.length() > 0 || long_name != "") {
	                        if (Type.equalsIgnoreCase("sublocality_level_1")) {
	                            Address1 = long_name + " ";
	                            Toast.makeText(context,"Adress" + longitude + " " + latitude + Address1, Toast.LENGTH_SHORT).show();
	                        } else if (Type.equalsIgnoreCase("route")) {
	                            Address1 = Address1 + long_name;
	                        } else if (Type.equalsIgnoreCase("sublocality_level_2")) {
	                            Address2 = long_name;
	                        } else if (Type.equalsIgnoreCase("city")) {
	                            // Address2 = Address2 + long_name + ", ";
	                            City = long_name;
	                        } else if (Type.equalsIgnoreCase("administrative_area_level_2")) {
	                            County = long_name;
	                        } else if (Type.equalsIgnoreCase("administrative_area_level_1")) {
	                            State = long_name;
	                        } else if (Type.equalsIgnoreCase("country")) {
	                        	
	                            Country = long_name;
	                            Toast.makeText(context,"county" + longitude + " " + latitude + Country, Toast.LENGTH_SHORT).show();
	                        } else if (Type.equalsIgnoreCase("postal_code")) {
	                            PIN = long_name;
	                        }
	                    }

	                    // JSONArray mtypes = zero2.getJSONArray("types");
	                    // String Type = mtypes.getString(0);
	                    // Log.e(Type,long_name);
	                }
	            }*/

	        } catch (Exception e) {
	            e.printStackTrace();
	            Toast.makeText(context,"Exception" + e.toString(), Toast.LENGTH_SHORT).show();
	            
	        }

	        Toast.makeText(context,  Address1  , Toast.LENGTH_SHORT).show();
	        
	        Log.d("Address1",Address1);
	        Log.d("Address2",Address2);
	        Log.d("city",City);
	        Log.d("state",State);

	        Log.d("country",Country);
	        Log.d("county",County);
	        Log.d("pin",PIN);
	        /*
	         * 
	         * 
	         * 
	         * Address1 = "";
	        Address2 = "";
	        City = "";
	        State = "";
	        Country = "";
	        County = "";
	        PIN = "";
	         * 
	         * */
	        
	        
	    }

		@Override
		public void callback(JSONObject jObject) {
			// TODO Auto-generated method stub
			
			
			
			/*Testing!@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/
			
			
			
			try {

	        	//Toast.makeText(context,"json" + "Test" + " " + "Test1" + Address1, Toast.LENGTH_SHORT).show();
	        	
	            //JSONObject jsonObj = parser_Json.getJSONfromURL("http://maps.googleapis.com/maps/api/geocode/json?latlng=" + latitude + ","
	                    //+ longitude + "&sensor=true",context);
	                    
	                    
	           //Toast.makeText(context,"json1" + "Test1" + " " + "Test2" + Address1, Toast.LENGTH_SHORT).show();
	            
	            //Toast.makeText(context,"Type" + "Test4" + " " + "Test3" + Address1, Toast.LENGTH_SHORT).show();
	            String Status = jObject.getString("status");
	            if (Status.equalsIgnoreCase("OK")) {
	            	//Toast.makeText(context,"BeforeResults" + longitude + " " + latitude + Address1, Toast.LENGTH_SHORT).show();
	                JSONArray Results = jObject.getJSONArray("results");
	                
	                
	                
	                
	                
	                //Toast.makeText(context,"AfterResults" + longitude + " " + latitude + Address1, Toast.LENGTH_SHORT).show();
	                JSONObject zero = Results.getJSONObject(0);
	                JSONArray address_components = zero.getJSONArray("address_components");
	                //Toast.makeText(context,"addresscomponents" + longitude + " " + latitude + Address1, Toast.LENGTH_SHORT).show();
	                for (int i = 0; i < address_components.length(); i++) {
	                    JSONObject zero2 = address_components.getJSONObject(i);
	                    String long_name = zero2.getString("long_name");
	                    JSONArray mtypes = zero2.getJSONArray("types");
	                    String Type = mtypes.getString(0);
	                    Log.d("Json", Type);
	                    //Toast.makeText(context,"Type" + longitude + " " + latitude + Address1, Toast.LENGTH_SHORT).show();
	                    if (TextUtils.isEmpty(long_name) == false || !long_name.equals(null) || long_name.length() > 0 || long_name != "") {
	                        if (Type.equalsIgnoreCase("sublocality_level_1")) {
	                            Address1 = long_name + " ";
	                           // Toast.makeText(context,"Adress" + longitude + " " + latitude + Address1, Toast.LENGTH_SHORT).show();
	                        } else if (Type.equalsIgnoreCase("route")) {
	                            Address1 = Address1 + long_name;
	                        } else if (Type.equalsIgnoreCase("sublocality_level_2")) {
	                            Address2 = long_name;
	                        } else if (Type.equalsIgnoreCase("city")) {
	                            // Address2 = Address2 + long_name + ", ";
	                            City = long_name;
	                        } else if (Type.equalsIgnoreCase("administrative_area_level_2")) {
	                            County = long_name;
	                        } else if (Type.equalsIgnoreCase("administrative_area_level_1")) {
	                            State = long_name;
	                        } else if (Type.equalsIgnoreCase("country")) {
	                        	
	                            Country = long_name;
	                           // Toast.makeText(context,"county" + longitude + " " + latitude + Country, Toast.LENGTH_SHORT).show();
	                        } else if (Type.equalsIgnoreCase("postal_code")) {
	                            PIN = long_name;
	                        }
	                    }

	                    // JSONArray mtypes = zero2.getJSONArray("types");
	                    // String Type = mtypes.getString(0);
	                    // Log.e(Type,long_name);
	                }
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	          //  Toast.makeText(context,"Exception" + e.toString(), Toast.LENGTH_SHORT).show();
	            
	        }

	        //Toast.makeText(context,  Address1  , Toast.LENGTH_SHORT).show();
	        
	        Log.d("Address1",Address1);
	        Log.d("Address2",Address2);
	        Log.d("city",City);
	        Log.d("state",State);

	        Log.d("country",Country);
	        Log.d("county",County);
	        Log.d("pin",PIN);
	        /*
	         * 
	         * 
	         * 
	         * Address1 = "";
	        Address2 = "";
	        City = "";
	        State = "";
	        Country = "";
	        County = "";
	        PIN = "";
	         * 
	         * */
	        
         
	    	//Post to serverdepending upon the json from the result. Have to append the above data to the server
	    	
	        HttpPostToServer hptsObj = new HttpPostToServer();
			hptsObj.PostToServer();

			
			
			
			
			
			
			
			/*Testing!@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/
			
			
			
			
			
			
			
			
			
		
			
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
