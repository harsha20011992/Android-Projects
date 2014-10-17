package com.example.textchangepipelineexample;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.widget.EditText;

public class MainActivity extends Activity {

	private Thread workerThread;
	public PipedWriter w;
	public PipedReader r;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		w = new PipedWriter();
		r = new PipedReader();
		try {
			w.connect(r);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		EditText et = (EditText) findViewById(R.id.editText1);
		
		et.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				if(count > before){
					try {
						char previous = (s.toString()).charAt((s.toString()).length()-1);
						w.write(previous);
						//Log.d("before",Integer.toString(before));
						//Log.d("count",Integer.toString(count));
						//Log.d("write",test4);
						Log.d("writing",Character.toString(previous));
						
						w.flush();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
		
		workerThread = new Thread(new TextHandlerTask(r));
		workerThread.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		workerThread.interrupt();
		try {
			w.close();
			r.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	

}
