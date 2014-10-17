package com.example.sharedprefstestinputform;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	SharedPreferences prefs;
	EditText ETName;
	EditText ETPassword;
	Button Bsubmit;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		prefs = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
		
		ETName = (EditText) findViewById(R.id.EditName);
		ETPassword = (EditText) findViewById(R.id.EditPassword);
		Bsubmit = (Button) findViewById(R.id.ButtonSubmit);
		
		if(prefs.contains("PrefName") &&  prefs.contains("PrefPassword")){
			ETName.setText(prefs.getString("PrefName", ""));
			ETPassword.setText(prefs.getString("PrefPassword", ""));
		}
		
		
		Bsubmit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Editor editor = prefs.edit();
			      editor.putString("PrefName", ETName.getText().toString());
			      editor.putString("PrefPassword", ETPassword.getText().toString());
			      
			      editor.commit();
			      
			      Log.d("PrefName", prefs.getString("PrefName", ""));
			      Log.d("PrefPassword", prefs.getString("PrefPassword", ""));
			 
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
