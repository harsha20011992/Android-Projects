package com.example.loginexampleusingprefence;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	EditText ETName;
	EditText ETPassword;
	Button Bsubmit;
	SharedPreferences prefs;
	Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		
		
		
		prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		intent = new Intent(getBaseContext(), WelcomeActivity.class);
		if(prefs.contains("PrefPassword") && prefs.contains("PrefName") ){
			startActivity(intent);
		}
		else
		{
		ETName = (EditText) findViewById(R.id.EditName);
		ETPassword = (EditText) findViewById(R.id.EditPassword);
		Bsubmit = (Button) findViewById(R.id.ButtonSubmit);

		
		
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
			      
			      
			      
			      String Name = ETName.getText().toString();
			      String Password = ETPassword.getText().toString();
			      intent.putExtra("UserName", Name);
			      intent.putExtra("Password", Password);
			      
			      startActivity(intent);
			}
		});
	
		}
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
