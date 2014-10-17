package com.example.customlistviewexample;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getWindow().getDecorView().setBackgroundColor(Color.BLACK);
		String[] array1 = {"Harsha", "Test" , "Vardhan"};
		ListView l1;
		CustomAdapter c1 = new CustomAdapter(this, array1);
		l1 = (ListView) findViewById(R.id.listView1);
		l1.setAdapter(c1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
		
		
	}

}
