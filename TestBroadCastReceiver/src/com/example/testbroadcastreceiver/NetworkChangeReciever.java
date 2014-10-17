package com.example.testbroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

public class NetworkChangeReciever extends BroadcastReceiver{

	
	public static int TYPE_WIFI = 1;
    public static int TYPE_MOBILE = 2;
    public static int TYPE_NOT_CONNECTED = 0;
    
	@Override
	public void onReceive(Context context, Intent arg1) {
		// TODO Auto-generated method stub
		String status = getConnectivityStatusString(context);
		Log.d("TestHarshastatus",status);
        Toast.makeText(context, status, Toast.LENGTH_SHORT).show();
	}

	private String getConnectivityStatusString(Context context) {
		// TODO Auto-generated method stub
		int conn = getConnectivityStatus(context);
        String status = null;
        if (conn == TYPE_WIFI) {
        status = "Wifi enabled";
        } else if (conn == TYPE_MOBILE) {
            status = "Mobile data enabled";
        } else if (conn == TYPE_NOT_CONNECTED) {
            status = "Not connected to Internet";
        }
        return status;
		
	}

	private int getConnectivityStatus(Context context) {
		// TODO Auto-generated method stub
		ConnectivityManager cm = (ConnectivityManager) context
	            .getSystemService(Context.CONNECTIVITY_SERVICE);

	        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
	        Log.d("TestHarsha","Test");
	        if (null != activeNetwork) {
	        	Log.d("TestHarsha","Test1");
	            if(activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
	            {
	            	Log.d("TestHarsha","Test2");
	                return TYPE_WIFI;
	            }
	            if(activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
	            {
	            	Log.d("TestHarsha","Test3");
	                return TYPE_MOBILE;
	            }
	        } 
	        Log.d("TestHarsha","Test4");
	        return TYPE_NOT_CONNECTED;
	}

}
