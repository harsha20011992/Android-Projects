package com.example.testbroadcastreceiver;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button b = (Button) findViewById(R.id.button1);
		//IntentFilter filter = new IntentFilter("com.tutorialspoint.CUSTOM_INTENT");
		//CustomBroadCastReciever receiver = new CustomBroadCastReciever(); 
		//registerReceiver(receiver, filter);
		b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				sendCustomBroadCast(v);
			}

					});
	}

	
	public void sendCustomBroadCast(View v){
		Intent intent = new Intent();
	      intent.setAction("com.tutorialspoint.CUSTOM_INTENT");
	      sendBroadcast(intent);	
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
