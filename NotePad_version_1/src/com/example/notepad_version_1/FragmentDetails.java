package com.example.notepad_version_1;



import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.graphics.Color;
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
	private Button addNotes;
	private Communicator communicator;
	static ArrayAdapter<String> NotesName;
	static ArrayList<String> NotesStringArray;
	private DataBaseHelperAdapter dbhelperadapter;
	CustomCursorAdapter c;
	DetailsListBroadCastListener Dbroadcast1;
	Cursor cursor;
	
	public interface Communicator{
		public void respond(int index,int listviewsize);
	}
	
	
	
	public FragmentDetails(){
		Dbroadcast1 = new DetailsListBroadCastListener();
		Dbroadcast1.setFragment(this);
		Log.d("Test_workFlow","constructor called for fragment " + StringSplitter.getReadablFragmentName(this) + " \t BaseActivityName: " + StringSplitter.getReadableActivityName(getActivity()));
	}
	
	public Communicator getComm() {
		return communicator;
	}
	
	public void setComm(Communicator comm) {
		communicator = comm;
	}
	
	private void DeleteCorrespondingRow(int index1) {
		// TODO Auto-generated method stub
		SharedPreferences prefs;
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
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		Log.d("Test_WorkFlow", "\t Inside onAttach of " + "Fragment: "+ StringSplitter.getReadablFragmentName(this) + " \t BaseActivityName: " + StringSplitter.getReadableActivityName(getActivity()));
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.d("Test_WorkFlow", "\t Inside onCreate of " + "Fragment: "+ StringSplitter.getReadablFragmentName(this) + " \t BaseActivityName: " + StringSplitter.getReadableActivityName(getActivity()));
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.d("Test_WorkFlow", "\t Inside oncreateview of " + "Fragment: "+ StringSplitter.getReadablFragmentName(this) + " \t BaseActivityName: " + StringSplitter.getReadableActivityName(getActivity()));
		
		View view = inflater.inflate(R.layout.fragment_main,container,false);
		addNotes = (Button) view.findViewById(R.id.addButton);
		l1 = (ListView) view.findViewById(R.id.listView1);
		//NotesStringArray = new ArrayList<String>();
		//NotesName = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,NotesStringArray);
		
		dbhelperadapter = new DataBaseHelperAdapter(getActivity());
		//l1.setAdapter(NotesName);
		l1.setOnItemClickListener(new OnItemClickListener() {
	    
		
			
			@Override
			public void onItemClick(AdapterView<?> adapterView, View arg1, int index,
					long arg3) {
				// TODO Auto-generated method stub
				for (int j = 0; j < adapterView.getChildCount(); j++)
			           adapterView.getChildAt(j).setBackgroundColor(Color.TRANSPARENT);
				
				arg1.setBackgroundColor(Color.YELLOW);
				
				CustomCursorAdapter.setSelectedItem(index);
				
				communicator.respond(index,0);
			}
		});
		return view;
	}
	
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		
		Log.d("Test_WorkFlow", "\t Inside onActivityCreated of " + "Fragment: "+ StringSplitter.getReadablFragmentName(this) + " \t BaseActivityName: " + StringSplitter.getReadableActivityName(getActivity()));
		//communicator.respond(-1, l1.getCount() );
		//Boolean requestFocusforfragment1 = getActivity().findViewById(R.id.fragment1).requestFocus();
		//Log.d("Test_Requestfocus", "Boolean Value: " + requestFocusforfragment1 );
		addNotes.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Log.d("Test_Value", "on addNotes button click \t l1 count:\t" + Integer.toString(l1.getCount()));
				communicator.respond(-1,l1.getCount());
				
				
			}
		
		});
		
		
		
		cursor = dbhelperadapter.getAllCursorDetails();
		if(cursor!=null){
			String[]	from = dbhelperadapter.returnColumns();
			int[] to = {R.id.cursor_TextU_id,R.id.cursor_TextTitle,R.id.cursor_TextNote};
			c = new CustomCursorAdapter(getActivity(), cursor, 0);
			l1.setAdapter(c);
			c.notifyDataSetChanged();	
		}
		
		l1.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int index, long arg3) {
				// TODO Auto-generated method stub
				String[] items = {"delete","edit"};
				final int index1 = index;
				//Toast.makeText(getActivity(), "You Pressed item" + (index +1) , Toast.LENGTH_SHORT).show();
				AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
				//builder1.setMessage("Do you want to delete this notepad");
				builder1.setItems(items, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface outer_dialog, int outer_item_number) {
						// TODO Auto-generated method stub
						
						Log.d("Test_Value","you have clicked " + outer_item_number  + "for index " + index1);
						
						if(outer_item_number == 0){
							
							AlertDialog.Builder builder2 = new AlertDialog.Builder(getActivity());
							builder2.setMessage("Do you want to delete this notepad");
							builder2.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface inner_dialog, int number) {
									// TODO Auto-generated method stub
									
									DeleteCorrespondingRow(index1);
									inner_dialog.cancel();
									
								}
							});
							builder2.setNegativeButton("No", new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface inner_dialog, int arg1) {
									// TODO Auto-generated method stub
								inner_dialog.cancel();
								}
							});
							builder2.setCancelable(true);
							AlertDialog dialog2 = builder2.create();
							dialog2.show();
					
							
							
						}
							}
				});
				builder1.setCancelable(true);					
				AlertDialog dialog1 = builder1.create();
				dialog1.show();
				
				return false;
			}
		});
		
	}
	
	
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.d("Test_WorkFlow", "\t Inside onStart of " + "Fragment: "+ StringSplitter.getReadablFragmentName(this) + " \t BaseActivityName: " + StringSplitter.getReadableActivityName(getActivity()));
	}


	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.d("Test_WorkFlow", "\t Inside onResume of " + "Fragment: "+ StringSplitter.getReadablFragmentName(this) + " \t BaseActivityName: " + StringSplitter.getReadableActivityName(getActivity()));
		Log.d("Test_WorkFlow", "\t"  + "Fragment: "+ StringSplitter.getReadablFragmentName(this) + " \t BaseActivityName: " + StringSplitter.getReadableActivityName(getActivity()));
	}
	

//Fragment is active after onresume is called
	

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.d("Test_WorkFlow", "\t Inside onPause of " + "Fragment: "+ StringSplitter.getReadablFragmentName(this) + " \t BaseActivityName: " + StringSplitter.getReadableActivityName(getActivity()));
	}


	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.d("Test_WorkFlow", "\t Inside onStop of " + "Fragment: "+ StringSplitter.getReadablFragmentName(this) + " \t BaseActivityName: " + StringSplitter.getReadableActivityName(getActivity()));
	}
	
	/*once the layout returns to the layour from the backstack, it moves from onDestroyview()
	to onCreateView()*/

	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
		Log.d("Test_WorkFlow", "\t Inside onDestroyView of " + "Fragment: "+ StringSplitter.getReadablFragmentName(this) + " \t BaseActivityName: " + StringSplitter.getReadableActivityName(getActivity()));
	}
	
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.d("Test_WorkFlow", "\t Inside onDestroy of " + "Fragment: "+ StringSplitter.getReadablFragmentName(this) + " \t BaseActivityName: " + StringSplitter.getReadableActivityName(getActivity()));
	}

	@Override
	public void onDetach() {
		// TODO Auto-generated method stub
		super.onDetach();
		Log.d("Test_WorkFlow", "\t Inside onDetach of " + "Fragment: "+ StringSplitter.getReadablFragmentName(this) + " \t BaseActivityName: " + StringSplitter.getReadableActivityName(getActivity()));
	}


/*Below three not mentioned in the activity cycle but has to be seen from the workflow*/
	@Override
	public void onHiddenChanged(boolean hidden) {
		// TODO Auto-generated method stub
		super.onHiddenChanged(hidden);
		Log.d("Test_WorkFlow", "\t Inside onHiddenChanged of " + "Fragment: "+ StringSplitter.getReadablFragmentName(this) + " \t BaseActivityName: " + StringSplitter.getReadableActivityName(getActivity()));
	}



	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		Log.d("Test_WorkFlow", "\t Inside onSaveInstance of " + "Fragment: "+ StringSplitter.getReadablFragmentName(this) + " \t BaseActivityName: " + StringSplitter.getReadableActivityName(getActivity()));
	}



	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		Log.d("Test_WorkFlow", "\t Inside onViewCreated of " + "Fragment: "+ StringSplitter.getReadablFragmentName(this) + " \t BaseActivityName: " + StringSplitter.getReadableActivityName(getActivity()));
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
