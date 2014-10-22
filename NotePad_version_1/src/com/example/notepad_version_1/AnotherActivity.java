package com.example.notepad_version_1;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.app.FragmentManager;

public class AnotherActivity extends ActionBarActivity implements FragmentNotePad.AfterInsertDataInterface{

     android.support.v4.app.FragmentManager manager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notepad);
		Log.d("Test","Inside another activity before intent retreival" + this.toString());
		Intent intent = getIntent();
		int index = intent.getIntExtra("index", 0);
		Boolean isInsert = intent.getBooleanExtra("isInsert", false);
		int nextValue = intent.getIntExtra("nextValue", 0);
		Log.d("Test","Inside another activity" + this.toString());
		
		manager  = getSupportFragmentManager();
		FragmentNotePad f2 = (FragmentNotePad) manager.findFragmentById(R.id.fragment2);
		if(f2 != null){	
		Log.d("Test_19","index:\t" + index);
		Log.d("Test_19","isInsert:\t" + isInsert);
		f2.setAfterSaveInterface(this);	
		f2.changetext(index,nextValue,isInsert);
			
		}
	}
	
	@Override
	protected void onPostResume() {
		// TODO Auto-generated method stub
		super.onPostResume();
		Log.d("Activity1","oonpostresume " + this.toString());
	}


	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.d("Activity1","onStop " + this.toString());
	}


	@SuppressLint("NewApi")
	@Override
	public void onAttachFragment(Fragment fragment) {
		// TODO Auto-generated method stub
		super.onAttachFragment(fragment);
		Log.d("Activity1","onAttachFragment " + fragment.toString());
	}


	@Override
	public void onAttachedToWindow() {
		// TODO Auto-generated method stub
		super.onAttachedToWindow();
		Log.d("Activity1", "onAttachedToWindow " + this.toString());
	}


	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		Log.d("Activity1", "onRestart " + this.toString());
	}
	
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onRestart();
		Log.d("Activity1", "onstart " + this.toString());
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onRestart();
		Log.d("Activity1", "onResume " + this.toString());
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onRestart();
		Log.d("Activity1", "onPause " + this.toString());
	}
	
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onRestart();
		Log.d("Activity1", "onDestroy " + this.toString());
	}

	@Override
	public int getlistViewCount() {
		// TODO Auto-generated method stub
		//
		//changelistView
		//FragmentDetails.NotesStringArray.add(Title);
        //FragmentDetails.NotesName.notifyDataSetChanged();
		return FragmentDetails.l1.getCount();
	}
	
	
}
