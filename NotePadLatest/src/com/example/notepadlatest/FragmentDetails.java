package com.example.notepadlatest;

import android.app.Fragment;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FragmentDetails extends Fragment{

	ListView l1;
	Communicator communicator;
	DataBaseHelperAdapter dbhelperadapter;
	SQLiteDatabase sqldatabase;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_main,container,false);
		l1 = (ListView) view.findViewById(R.id.listView1);
		
		ArrayAdapter NotesName = ArrayAdapter.createFromResource(getActivity(), R.array.notesname, android.R.layout.simple_list_item_1);
		l1.setAdapter(NotesName);
		l1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				communicator.respond(arg2);
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
		public void respond(int index);
	}
	

}
