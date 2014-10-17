package com.example.downloadimageviaservice;

import android.os.Handler;
import android.util.Log;

public class DownloadThread extends Thread{

	String URL;
	Handler serviceHandler;
	public DownloadThread(String URL1,Handler h1){
		this.URL = URL1;
		serviceHandler = h1;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		downloadImage();
		
		Log.d("Test",Thread.currentThread().getName() + "Inside Thread run");
		serviceHandler.sendEmptyMessage(0);
	}
	private void downloadImage() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(3000);
			Log.d("Test",Thread.currentThread().getName() + "The sleep is ended");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
