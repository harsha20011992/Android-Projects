package com.example.loginexampleusingprefence;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class LoginPrefence extends PreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.loginpref);
	}

	
}
