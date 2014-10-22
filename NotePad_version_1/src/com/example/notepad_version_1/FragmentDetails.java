package com.example.notepad_version_1;



import java.util.ArrayList;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.support.v4.widget.SimpleCursorAdapter;


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
