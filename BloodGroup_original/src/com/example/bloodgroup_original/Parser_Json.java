package com.example.bloodgroup_original;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;


public class Parser_Json extends AsyncTask<String, String, JSONObject>{
	JSONObject jObject = null;
	Context context;
	HttpPostCallBack hpcvObj;
	
	
	public String Address1 = "", Address2 = "", City = "", State = "", Country = "", County = "", PIN = "";
	 
	public Parser_Json(HttpPostCallBack obj)
	{
		hpcvObj = obj;
	}
	
	/* public static JSONObject getJSONfromURL(String url,Context context) {
	    	Log.d("URL",url);
	        // initialize
	        InputStream is = null;
	        String result = "";
	        JSONObject jObject = null;

	        // http post
	        try {
	        	
	        	
	        	 
	        	
	        	Toast.makeText(context,"before 1", Toast.LENGTH_SHORT).show();
	        	
	        	AsyncHttpClient client = new AsyncHttpClient();
	            HttpClient httpclient = new DefaultHttpClient();
	            Toast.makeText(context,"before 2", Toast.LENGTH_SHORT).show();
	            HttpPost httppost = new HttpPost(url);
	            Toast.makeText(context,"before 3", Toast.LENGTH_SHORT).show();
	            HttpResponse response = httpclient.execute(httppost); 
	            Toast.makeText(context,"before 4", Toast.LENGTH_SHORT).show();
	            HttpEntity entity = response.getEntity();
	           
	            Toast.makeText(context,"before 4", Toast.LENGTH_SHORT).show();
	            is = entity.getContent();
	            //Toast.makeText(context,"before 4", Toast.LENGTH_SHORT).show();
	            
	          
	            
	        } catch (Exception e) {
	            Log.e("log_tag", "Error in http connection " + e.toString());
	        }

	        // convert response to string
	        try {
	            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
	            StringBuilder sb = new StringBuilder();
	            String line = null;
	            while ((line = reader.readLine()) != null) {
	                sb.append(line + "\n");
	            }
	            is.close();
	            result = sb.toString();
	            Log.d("Result",result);
	        } catch (Exception e) {
	            Log.e("log_tag", "Error converting result " + e.toString());
	        }

	        // try parse the string to a JSON object
	        try {
	            jObject = new JSONObject(result);
	        } catch (JSONException e) {
	            Log.e("log_tag", "Error parsing data " + e.toString());
	        }

	        return jObject;
	    }*/

		@Override
		protected JSONObject doInBackground(String... params) {
			// TODO Auto-generated method stub
			
			//Toast.makeText(context,"shreya3", Toast.LENGTH_SHORT).show();
			
			Log.d("URL","harsha");
	        // initialize
	        InputStream is = null;
	        String result = "";
	        jObject = null;

	        // http post
	        try {
	        	
	        	
	        	//Toast.makeText(context,"shreya4", Toast.LENGTH_SHORT).show();
	        	
	        	//Toast.makeText(context,"before 1", Toast.LENGTH_SHORT).show();
	        	
	        	//AsyncHttpClient client = new AsyncHttpClient();
	        	
	            HttpClient httpclient = new DefaultHttpClient();
	            //Toast.makeText(context,"before 2", Toast.LENGTH_SHORT).show();
	            HttpPost httppost = new HttpPost(params[0]);
	            //Toast.makeText(context,params[0], Toast.LENGTH_SHORT).show();
	            //Toast.makeText(context,"before 3", Toast.LENGTH_SHORT).show();
	            HttpResponse response = httpclient.execute(httppost);
	            
	            
	            
	            
	            //Toast.makeText(context,"before 4", Toast.LENGTH_SHORT).show();
	            HttpEntity entity = response.getEntity();
	           
	            //Toast.makeText(context,"before 4", Toast.LENGTH_SHORT).show();
	            is = entity.getContent();
	            //Toast.makeText(context,"before 4", Toast.LENGTH_SHORT).show();
	            
	          
	            
	        } catch (Exception e) {
	           e.printStackTrace();
	           String test = e.toString();
	           Log.d("harsha", e.toString());
	        }

	        // convert response to string
	        try {
	            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
	            StringBuilder sb = new StringBuilder();
	            String line = null;
	            while ((line = reader.readLine()) != null) {
	                sb.append(line + "\n");
	            }
	            is.close();
	            result = sb.toString();
	            Log.d("Result",result);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        // try parse the string to a JSON object
	        try {
	            jObject = new JSONObject(result);
	        } catch (JSONException e) {
	        	e.printStackTrace();
	        }

	        return jObject;
		}
		
		
	    protected void onPostExecute(JSONObject result) {
	    	
	    	
	    	String Test = "url";
	    	hpcvObj.callback(result);
	    	
	    	/*try {

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
	    	
	    	
	        
         
	    	
	    	
	    	
	    	
}
	    
	    
}
