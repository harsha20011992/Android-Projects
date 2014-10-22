package com.example.notepad_version_1;



import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


public class FragmentDetails extends Fragment{

	static ListView l1;
	Button addNotes;
	Communicator communicator;
	static ArrayAdapter<String> NotesName;
	static ArrayList<String> NotesStringArray;
	DataBaseHelperAdapter dbhelperadapter;
	SQLiteDatabase sqldatabase;
	SimpleCursorAdapter c;
	Cursor cursor;
	SharedPreferences prefs;
	DetailsListBroadCastListener Dbroadcast1;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		Log.d("Activity1_fragmentdetails", "oncreateview" + this.toString() + "activityname " + getActivity().toString());
		View view = inflater.inflate(R.layout.fragment_main,container,false);
		addNotes = (Button) view.findViewById(R.id.addButton);
		l1 = (ListView) view.findViewById(R.id.listView1);
		NotesStringArray = new ArrayList<String>();
		//NotesStringArray[0] = "hello";
		NotesName = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,NotesStringArray);
		
		dbhelperadapter = new DataBaseHelperAdapter(getActivity());
		l1.setAdapter(NotesName);
		Dbroadcast1 = new DetailsListBroadCastListener();
		l1.setOnItemClickListener(new OnItemClickListener() {
	    
		
			
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int index,
					long arg3) {
				// TODO Auto-generated method stub
				communicator.respond(index,0);
			}
		});
		return view;
	}
	
	
	
	public Communicator getComm() {
		return communicator;
	}



	public void setComm(Communicator comm) {
		communicator = comm;
	}



	public interface Communicator{
		public void respond(int index,int listviewsize);
	}

	
	
	


	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		Dbroadcast1.setFragment(this);
		
		Log.d("Activity1_fragmentdetails", "onActivitycreated " + this.toString() + " activityname " + getActivity().toString());
		//communicator.respond(-1, l1.getCount() );
		Boolean requestFocusforfragment1 = getActivity().findViewById(R.id.fragment1).requestFocus();
		Log.d("Test_Requestfocus", "Boolean Value: " + requestFocusforfragment1 );
		addNotes.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Log.d("Test_18", "on add button click \t l1 count:\t" + Integer.toString(l1.getCount()));
				Log.d("New", "on add button click\t list count: " + Integer.toString(l1.getCount()));
				communicator.respond(-1,l1.getCount());
				
				
			}
		
		});
		
		
		
		cursor = dbhelperadapter.getAllCursorDetails();
		if(cursor!=null){
			String[]	from = dbhelperadapter.returnColumns();
			int[] to = {R.id.cursor_TextU_id,R.id.cursor_TextTitle,R.id.cursor_TextNote};
			c = new SimpleCursorAdapter(getActivity(), R.layout.detailscursorlayout, cursor, from, to, 0);
			l1.setAdapter(c);
			c.notifyDataSetChanged();	
		}
		
		l1.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int index, long arg3) {
				// TODO Auto-generated method stub
				final int index1 = index;
				//Toast.makeText(getActivity(), "You Pressed item" + (index +1) , Toast.LENGTH_SHORT).show();
				AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
				builder1.setMessage("Do you want to delete this notepad");
				builder1.setCancelable(true);
				builder1.setPositiveButton("YES", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int arg1) {
						// TODO Auto-generated method stub
						Log.d("Test_dialog","Clicked yes: " + index1);
						DeleteCorrespondingRow(index1);
						
						dialog.cancel();
					}

					
				});
				
				
				builder1.setNegativeButton("No", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int arg1) {
						// TODO Auto-generated method stub
						Log.d("Test_dialog","Clicked No");
						dialog.cancel();
					}
				});
				
				AlertDialog dialog1 = builder1.create();
				dialog1.show();
				
				return false;
			}
		});
		
	}

	private void DeleteCorrespondingRow(int index1) {
		// TODO Auto-generated method stub
		prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
		
		if(prefs.contains(Integer.toString(index1)))
		{
			//delete the row in db starts here
			int count = dbhelperadapter.deleteRow(prefs.getInt(Integer.toString(index1), 0));
			Log.d("Test_delete_comit", "rows affected: " + count);
			//delete the row in db starts here
			cursor.requery();
			c.notifyDataSetChanged();
			//delete the corresponding prefs here
			for(int i=index1;i < l1.getCount();i++){
				Editor edit = prefs.edit();
				edit.putInt(Integer.toString(i), prefs.getInt(Integer.toString(i+1), 0));
				Log.d("Test_delete_comit", " \t listnumber: " + i + " \t Vaue(prefsValue): " + prefs.getInt(Integer.toString(i+1), 0) + " \t listviewcount" + l1.getCount() );
				edit.commit();
			}//delete the corresponding prefs here
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		Log.d("Activity1_fragmentdetails", "onActivityresult " + this.toString() + " activityname " + getActivity().toString());
		
	}



	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		Log.d("Activity1_fragmentdetails", "onActivityattach " + this.toString() + " activityname " + getActivity().toString());
	}



	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.d("Activity1_fragmentdetails", "oncreate " + this.toString() + " activityname " + getActivity().toString());
	}



	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.d("Activity1_fragmentdetails", "onDestroy " + this.toString() + " activityname " + getActivity().toString());
	}



	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
		Log.d("Activity1_fragmentdetails", "onDestroyview " + this.toString() + " activityname " + getActivity().toString());
	}



	@Override
	public void onDetach() {
		// TODO Auto-generated method stub
		super.onDetach();
		Log.d("Activity1_fragmentdetails", "onDetach " + this.toString() + " activityname " + getActivity().toString());
	}



	@Override
	public void onHiddenChanged(boolean hidden) {
		// TODO Auto-generated method stub
		super.onHiddenChanged(hidden);
		Log.d("Activity1_fragmentdetails", "onHiddenchanged " + this.toString() + " activityname " + getActivity().toString());
	}



	



	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.d("Activity1_fragmentdetails", "onPause " + this.toString() + " activityname " + getActivity().toString());
	}



	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.d("Activity1_fragmentdetails", "onResume " + this.toString() + " activityname " + getActivity().toString());
	}



	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		Log.d("Activity1_fragmentdetails", "onsaveinstancestate " + this.toString() + " activityname " + getActivity().toString());
	}



	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.d("Activity1_fragmentdetails", "onstart " + this.toString() + " activityname " + getActivity().toString());
	}



	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.d("Activity1_fragmentdetails", "onstop " + this.toString() + " activityname " + getActivity().toString());
	}



	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		Log.d("Activity1_fragmentdetails", "onViewCreated " + this.toString() + " activityname " + getActivity().toString());
	}



	
	/*@Override
	public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewStateRestored(savedInstanceState);
	}
	
	@Override
	public void onInflate(Activity activity, AttributeSet attrs,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onInflate(activity, attrs, savedInstanceState);
		Log.d("Activity1_fragmentdetails", "oninflate" + this.toString() + " activityname " + getActivity().toString());
	}*/
	
 
}
