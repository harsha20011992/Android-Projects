package com.example.downloadimageviaservice;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	String[] downloadUrls;
	EditText displayDownloadUrl;
	Button downloadImage;
	LinearLayout LoadingSection = null; 
	Handler mainThreadHandler=null;
	DownlaodBroadCastReciever reciever;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Log.d("TestActivity","Activity start");
		//Getting the Listview text from the strings.xml and setting the adapter for listview
				downloadUrls = getResources().getStringArray(R.array.downloadURLs);
				ListView downloadList = (ListView) findViewById(R.id.listView1);
				ArrayAdapter<String> downloadAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,downloadUrls);
				downloadList.setAdapter(downloadAdapter);
				
				//initializing handler
				mainThreadHandler = new Handler();
		
		//initializing the button and textview items
		displayDownloadUrl = (EditText) findViewById(R.id.DisplayDownloadUrl);
		downloadImage = (Button) findViewById(R.id.downloadImage);
		
		LoadingSection = (LinearLayout) findViewById(R.id.loadingsection);
		
		IntentFilter filter = new IntentFilter("com.tutorialspoint.DOWNLOAD_COMPLETE"); 
		
		reciever = new DownlaodBroadCastReciever();
		registerReceiver(reciever, filter);
		
		//onclick event fot listview
		downloadList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				displayDownloadUrl.setText(((TextView)view1).getText());
			}
		});
		
		
		
		downloadImage.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.d("TestButtonClick","Button has been clicked");
				// TODO Auto-generated method stub
				//call thread.start() to download image
				if(displayDownloadUrl.getText().toString().equals("") || displayDownloadUrl.getText().toString().equals(null)){
					Toast.makeText(getApplicationContext(), "Please select a image to download", Toast.LENGTH_SHORT).show();
				}
				else{
					
				
				//t1 = new DownloadImageThread(displayDownloadUrl.getText().toString(),MainActivity.this,mainThreadHandler);
				//t1.start();
					
					Intent intent = new Intent(getBaseContext(),DownloadService.class);
					intent.putExtra("URL",displayDownloadUrl.getText().toString() );
					Log.d("BeforeServiceStart","Service is going to be started");
					LoadingSection.setVisibility(View.VISIBLE);
					
					startService(intent);
				}
			}
		});
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private class DownlaodBroadCastReciever extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			Log.d("TestOnRecieve",Thread.currentThread().getName() + "inside receiver");
			LoadingSection.setVisibility(View.INVISIBLE);
		}
		
	}
	
}
