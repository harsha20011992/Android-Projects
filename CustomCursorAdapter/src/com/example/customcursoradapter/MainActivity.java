package com.example.customcursoradapter;

import com.example.customcursoradapter.DataBaseHelperAdapter.DataBaseHelper;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity {

	ListView l1;
	Button add,fetch;
	private CustomCursorAdapterClass cursoradapter;
	private  DataBaseHelperAdapter dbhelperadapter;
	SQLiteDatabase sqldb;
	static int count;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button add = (Button) findViewById(R.id.Badd);
		Button fetch = (Button) findViewById(R.id.Bfetch);
		
		dbhelperadapter = new DataBaseHelperAdapter(this);
		
		add.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				dbhelperadapter.insertData(++count, "Harsha"+count, "Hello"+ count);
				
			}
		});
		
		
		l1 = (ListView) findViewById(R.id.listView1);
		
		
		fetch.setOnClickListener(new OnClickListener() {
		
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Cursor c = dbhelperadapter.getAllDetails();
			  //String[]	from = {DataBaseHelper.U_ID,DataBaseHelper.Note_Text,DataBaseHelper.Note_Title};
			  //int[] to = {R.id.U_id,R.id.textTitle,R.id.textTitle};
				//cursoradapter = new SimpleCursorAdapter(MainActivity.this, R.layout.cursoradapterxml, c, from, to,0);
				cursoradapter = new CustomCursorAdapterClass(MainActivity.this, c, 0);
				
				l1.setAdapter(cursoradapter);
				cursoradapter.notifyDataSetChanged();
			}
		});
		
		
		
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
}
