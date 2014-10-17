package com.example.looperhandlerexample;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	Button b;
	Handler mainThreadHandler;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		
		b = (Button) findViewById(R.id.button1);
		
		final WorkerThread wt = new WorkerThread(mainThreadHandler);
		Log.d("ThreadName",Thread.currentThread().getName());
		wt.start();
		
		b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Handler WorkerThreadHandler = wt.getHandler();
				WorkerThreadHandler.post(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						Log.d("ThreadName",Thread.currentThread().getName());
					}
				});
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
