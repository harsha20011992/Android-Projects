package com.example.notepad_version_1;



import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


@SuppressLint("NewApi")
public class MainActivity extends ActionBarActivity implements FragmentDetails.Communicator,FragmentNotePad.AfterInsertDataInterface {

	android.support.v4.app.FragmentManager manager;
	FragmentDetails f1;
	FragmentNotePad f2;
	SharedPreferences prefs;
    static	int nextValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_main);
        Log.d("Activity1 ","oncreate" + this.toString());
        	manager = getSupportFragmentManager();
		
        prefs = PreferenceManager.getDefaultSharedPreferences(this);	
		f1 = (FragmentDetails) manager.findFragmentById(R.id.fragment1);
		f1.setComm(this);
		
		f2 = (FragmentNotePad) manager.findFragmentById(R.id.fragment2);
		if(f2!=null)
		{
			f2.setAfterSaveInterface(this);
		}
		
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public static void setNextValue(int next){
    	nextValue = next;
    	Log.d("New", "inside setValue next " + next);
    }
    
    
	@Override
	public void respond(int index,int listviewsize) {
		// TODO Auto-generated method stub
		int listviewnumber;
		Boolean isInsert;
		if(index == -1){
			Log.d("Test_18", "inside respond index=-1 start");
			Log.d("New", "inside 'respond index=-1' start");
			if(prefs.contains(Integer.toString(0))){
				nextValue = prefs.getInt("NextValue", 0);
				//Editor edit = prefs.edit();
			    //edit.putInt(Integer.toString(listviewsize), ++nextValue);
			    //edit.putInt("NextValue", nextValue);
			    //edit.commit();
			    listviewnumber = listviewsize; 
			    Log.d("New", "prefs contains 0\tlisviewnumber: " + Integer.toString(listviewnumber) + "\tnextvalue: " + nextValue );
			    Log.d("Test_18", "inside respond index=-1 end\tlisviewnumber:" + Integer.toString(listviewnumber) + "\t nextvalue: " + nextValue );
			    
			}	
			else{
				listviewnumber = listviewsize;
				nextValue = 0;
				nextValue++;
				Log.d("New", "prefs not contains 0 \t lisviewnumber: " + Integer.toString(listviewnumber) + "\t nextvalue: " + nextValue );
				//Editor edit = prefs.edit();
			    //edit.putInt(Integer.toString(listviewsize), ++nextValue);
			    Log.d("Test_18", "listviewsize:\t" + Integer.toString(listviewsize));
			    //Log.d("New", "prefs not contains 0 \t lisviewnumber: " + Integer.toString(listviewnumber) + "\t nextvalue: " + nextValue );
			    //edit.putInt("NextValue", nextValue);
			    Log.d("Test_18", "nextview:\t\t" + Integer.toString(listviewsize));
			    //edit.commit();
			    Log.d("Test_18", "inside index=-1\tValueToBeSent:\t" + Integer.toString(listviewnumber));
			    
			}
			isInsert = true;
			Log.d("New", "inside 'respond index=-1' end");
		}
		
		else{
			listviewnumber = index;
			isInsert = false;
			Log.d("Test_18", "index !=-1\tValueToBeSent:\t" + Integer.toString(listviewnumber));
		}
		f2  = (FragmentNotePad) manager.findFragmentById(R.id.fragment2);
		
			if(f2 != null && f2.isVisible())
			{
				Log.d("Test_18", "F2!=Null and F2.isVisible:\t listviewnumber " + Integer.toString(listviewnumber) + "\tnextValue:" + nextValue + "isInsert:\t" + Boolean.toString(isInsert) );
				Log.d("New", "F2!=Null and F2.isVisible: \t listviewnumber: " + Integer.toString(listviewnumber) + " \t nextValue: " + nextValue + "\t isInsert: " + Boolean.toString(isInsert) );
				f2.changetext(listviewnumber,nextValue,isInsert);
			}
			else
			{
				Intent intent =  new Intent(this,AnotherActivity.class);
				Log.d("New", "Before Activity Start \t listviewnumber: " + Integer.toString(listviewnumber) + " \t nextValue: " + nextValue + "\t isInsert: " + Boolean.toString(isInsert) );
				Log.d("Test_18", "Start Activity\t listviewnumber " + Integer.toString(listviewnumber) + "\tnextValue:" + nextValue + "isInsert:\t" + Boolean.toString(isInsert) );
				intent.putExtra("index", listviewnumber);
				intent.putExtra("nextValue", nextValue);
				intent.putExtra("isInsert", isInsert);
				startActivity(intent);
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
		super.onStart();
		Log.d("Activity1", "onstart " + this.toString());
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		//Log.d("Activity1", "onResume " + this.toString());
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.d("Activity1", "onPause " + this.toString());
	}
	
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.d("Activity1", "onDestroy " + this.toString());
	}


	@Override
	public void changelistView(String Title) {
		// TODO Auto-generated method stub
		f1.NotesStringArray.add(Title);
        f1.NotesName.notifyDataSetChanged();	
	}
	
	
}
