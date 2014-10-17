package com.example.downloadimageviaservice;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public class DownloadService extends Service {

	DownloadThread t1;
	Handler h1;
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
		
		
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.d("harsha","The service has been created");
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		h1 = new Handler(){

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				Log.d("TestService","Inside handle message");
				Intent intent = new Intent();
			      intent.setAction("com.tutorialspoint.DOWNLOAD_COMPLETE");
			      Log.d("Test",Thread.currentThread().getName() + "Before Broadcast");
			      sendBroadcast(intent);
			      Log.d("Test",Thread.currentThread().getName() + "After BroadCast");
				 //sendBroadcast(intent);
				}
			
		};
	
		Log.d("harsha","The service has been started");
		Log.d("harsha", "The url is" + intent.getStringExtra("URL"));
		
		//Messenger m1 = (Messenger) intent.getParcelableExtra("myhandler");
		
		t1 = new DownloadThread(intent.getStringExtra("URL"), h1);
		
		Log.d("TestActivity","Before Thread start");
		t1.start();
		
		return super.onStartCommand(intent, flags, startId);
	}

}
