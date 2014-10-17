package com.example.downloadimageviathread;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.os.Environment;
import android.util.Log;
import android.view.View;

public class DownloadImageThread extends Thread{
	URL urlToDownload;
	URLConnection URLConnection1;
	InputStream inputConnection1;
	FileOutputStream fileOutputStream;
	Activity mActivity;
	MainActivity test1;
	File file;
	public DownloadImageThread(String UrlToDownload1, Activity mActivity1){
		try {
			this.urlToDownload = new URL(UrlToDownload1);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mActivity = mActivity1;
		Log.d("harsha", (this.urlToDownload).toString());
	}
	
	
		public void run() {
			
			mActivity.runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					test1 = (MainActivity) mActivity;
					test1.LoadingSection.setVisibility(View.VISIBLE);
				}
			});
			
			DownloadImageCode();
		}
		
		public void DownloadImageCode(){
			if(urlToDownload!=null){
				try {
					 URLConnection1 = urlToDownload.openConnection();
					 inputConnection1 = ((URLConnection) URLConnection1).getInputStream();
					 int read = -1;
					 byte[] buffer = new byte[1024];
					 Log.d("harsha",Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath() + "/test.jpg");
					 file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath() + "/test.jpg");
					 fileOutputStream = new FileOutputStream(file);
					 while((read = inputConnection1.read(buffer))!= -1){
						 Log.d("Harsha",Integer.toString(read));
						 fileOutputStream.write(buffer, 0, read);
					 }
					 Log.d("harsha","successfull");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally{
					
					mActivity.runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
						
							test1.LoadingSection.setVisibility(View.INVISIBLE);
						}
					});
					
					if(URLConnection1!=null){
						/*try {
							//
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}*/
						if(inputConnection1!=null){
							try {
								inputConnection1.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							if(fileOutputStream!=null){
								try {
									fileOutputStream.close();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
						
					}
				}
			}
		}
	
}
