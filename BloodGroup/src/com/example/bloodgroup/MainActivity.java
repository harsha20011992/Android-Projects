package com.example.bloodgroup;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity{

	private Button b;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Toast.makeText(getApplicationContext(), "Inside main", Toast.LENGTH_SHORT).show();
		setContentView(R.layout.activity_main);
		Toast.makeText(getApplicationContext(), "Inside main 1", Toast.LENGTH_SHORT).show();
		Log.d("harsha", "harsha");
        Context context = getBaseContext();
        Log.e("harsha", "1");
		GPSTracker gps = new GPSTracker(context);
		Log.d("harsha", "2");
		Log.d("harsha", "3");
		
		/*HttpClient httpclient = new DefaultHttpClient();
	    HttpPost httppost = new HttpPost("http://posttestserver.com/post.php");
	    try {
			HttpResponse response = httpclient.execute(httppost);
			int statusCode = response.getStatusLine().getStatusCode();
			Log.d("statuscode", statusCode + "");
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} */

		Toast.makeText(getApplicationContext(), "outside main", Toast.LENGTH_SHORT).show();
		
		
		/* b = (Button) findViewById(R.id.ETFirstName);
		
        b.setOnClickListener(new OnClickListener() {
			

			@Override
			public void onClick(View b) {
				// TODO Auto-generated method stub
				
				
			}
			
			
		}); */
	    
	    
		
		//getReverseGeoCoding obj = new getReverseGeoCoding();
		//obj.getAddress(0,0,getBaseContext());
	    
	    
		
	} 

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


}
