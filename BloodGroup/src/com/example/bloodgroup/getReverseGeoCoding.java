package com.example.bloodgroup;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

public class getReverseGeoCoding {
	    public String Address1 = "", Address2 = "", City = "", State = "", Country = "", County = "", PIN = "";

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
	                    parser_Json Json_execute = new parser_Json(context);
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

	    public String getAddress1() {
	        return Address1;

	    }

	    public String getAddress2() {
	        return Address2;

	    }
	    
	   

	    public String getCity() {
	        return City;

	    }

	    public String getState() {
	        return State;

	    }

	    public String getCountry() {
	        return Country;

	    }

	    public String getCounty() {
	        return County;

	    }

	    public String getPIN() {
	        return PIN;

	    }

	}
