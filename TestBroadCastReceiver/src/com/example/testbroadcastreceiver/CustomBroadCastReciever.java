package com.example.testbroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class CustomBroadCastReciever extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Log.d("TestHarsha","Intent Recieved");
		Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show();
	}

}
