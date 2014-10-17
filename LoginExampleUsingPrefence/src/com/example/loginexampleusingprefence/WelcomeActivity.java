package com.example.loginexampleusingprefence;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class WelcomeActivity extends Activity{

	Intent RecievedIntent;
	TextView TvWelcomeText;
	SharedPreferences prefs1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome_screen);
		RecievedIntent = getIntent();
		prefs1 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		prefs1.registerOnSharedPreferenceChangeListener(new OnSharedPreferenceChangeListener() {
			
			@Override
			public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
					String key) {
				// TODO Auto-generated method stub
				TvWelcomeText.setText("Welcome :" + prefs1.getString("PrefName","") + "\t Your Password is :" + prefs1.getString("PrefPassword",""));
			}
		});
		
		TvWelcomeText = (TextView) findViewById(R.id.Label_welcome_text);
		//TvWelcomeText.setText("Welcome :" + RecievedIntent.getExtras().getString("UserName") + "\t Your Password is :" + RecievedIntent.getExtras().getString("Password"));
		TvWelcomeText.setText("Welcome :" + prefs1.getString("PrefName","") + "\t Your Password is :" + prefs1.getString("PrefPassword",""));
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		
		
		MenuInflater menuinflater = getMenuInflater();
		menuinflater.inflate(R.menu.welcomemenu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		switch(item.getItemId()){
		case R.id.prefActivity:
			Intent prefIntent = new Intent(getBaseContext(),LoginPrefence.class);
			startActivity(prefIntent);
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	

}
