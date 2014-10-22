package com.example.notepad_version_1;


import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentNotePad extends Fragment {
	
	TextView Notepadtitle;
	NotePadEditText Notepadtext;
	Button notepadsave;
	DataBaseHelperAdapter dbhelperadapter;
	SQLiteDatabase sqldatabase;
	int listviewnumber,nextvalue_global;
	/*The initialization of these strings has to be qualified in appropriate methods
	like oncreate() or onstart() after studying  fragment methods*/ 
	String Title_from_Db="",Note_from_Db="";
	
	/*The initialization of these boolean has to be qualified in appropriate methods
	like oncreate() or onstart() after studying  fragment methods*/
	Boolean isInsert_global=false,isTransactionviaChangeText=false;
	SharedPreferences prefs;
	AfterInsertDataInterface A1interface;
	//Boolean TextFromDbAvailable;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		listviewnumber = -1;
		dbhelperadapter = new DataBaseHelperAdapter(getActivity());
		Log.d("Activity1_fragmentdetails", "oncreateview" + this.toString() + "activityname " + getActivity().toString());
		
		View v = inflater.inflate(R.layout.fragementnotes, container,false);
		Notepadtitle = (TextView) v.findViewById(R.id.notepadtitle);
		Notepadtext = (NotePadEditText) v.findViewById(R.id.note_edit_text);
		
		
		
		//String NotePadText = getNotePadtext();
		//Notepadtext.setText(NotePadText);
		
		notepadsave = (Button) v.findViewById(R.id.notepadsave);
		notepadsave.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				insertData();
			}
		});
		
		
		//@@@@@@@@@@@@@@@@@@
		
		
		
		
		//@@@@@@@@@@@@@@@@@
		
		
		return v;
	}
	public void setAfterSaveInterface(AfterInsertDataInterface a1){
		A1interface = a1;
	}
	
	public void changetext(int index,int nextValue,Boolean isInsert) {
		isTransactionviaChangeText = true;
		// TODO Auto-generated method stub
		//prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
		//Log.d("Test_19","insidechangeText" + this.toString() + "activityName" + getActivity().toString() + "\n listviewnumer:" + listviewnumber);
		Log.d("Test_19","insidechangeText");
		//Log.d("Test_19","insidechangeText" + " \t listviewnumer:" + listviewnumber);
		//Log.d("New","insidechangeText FN" + "\t listviewnumer:" + listviewnumber);
		listviewnumber = index;
		nextvalue_global = nextValue;
		isInsert_global = isInsert;
		//Log.d("Test_19","insidechangeText" + this.toString() + "activityName" + getActivity().toString() + "\n listviewnumer after asiign:" + listviewnumber + "\nnextvalue:\t"  + nextvalue_global +"\tisinsert:\t" + isInsert);
		//Log.d("New","insidechangeText FN" + " \t listviewnumer: " + listviewnumber + " \t nextglobalvale: " + nextvalue_global);
		Log.d("Test_Value","insidechangeText \t " + "listviewnumer: " + listviewnumber + "\t nextvalue: "  + nextvalue_global +"\t isinsert: " + isInsert);
		if(isInsert){
			Log.d("Test_19","inside changeText: \t isInsert " + isInsert);
			//Log.d("Test_19","prefsValue\t" +  prefs.getInt(Integer.toString(listviewnumber), 0) +this.toString() + "activityName" + getActivity().toString());
			//Notepadtitle.setText("Note" + prefs.getInt(Integer.toString(listviewnumber), 0));
			//Log.d("New","insidechangeText isInsert FN" );
			//Log.d("New","insidechangeText isInsert FN" + " \t listviewnumer: " + listviewnumber + " \t nextglobalvale: " + nextvalue_global);
			Notepadtitle.setText("Note" + nextvalue_global);
			Notepadtext.setText("");
			Log.d("Test_Value","insidechangeText --> isInsert = true \t " + "listviewnumer: " + listviewnumber + "\t nextvalue: "  + nextvalue_global +"\t isinsert: " + isInsert);
		}
		
		else
		{
			//int newIndex = 0;
			prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
			Log.d("Test_22","inside changeText: \t isInsert " + isInsert + " \t indexValue: " + listviewnumber);
			//newIndex = index + 1; 
			//Log.d("Test_22","inside changeText:\t isInsert " + isInsert + " \t NewindexValue: " + newIndex);
			if(prefs.contains(Integer.toString(index))){
				//Log.d("Test_22","inside changeText:-->prefs contains true  \t isInsert " + isInsert + " \t indexValue: " + newIndex);
				int int_U_id = prefs.getInt(Integer.toString(listviewnumber), 0);
				Log.d("Test_22","inside changeText:-->prefs contains true  \t isInsert " + isInsert + " \t U_id: " + int_U_id);
				GetTextandTitleForGivenIndex(int_U_id);
				
			}
		}
	}
	
	/*@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		//Log.d("Activity1_fragmentdetails", "onstart " + this.toString() + " activityname " + getActivity().toString());
		Log.d("Activity1_fragmentdetails", "onstart ");
	}*/
	
	public void insertData(){
		String Title = Notepadtitle.getText().toString();
		String Text =  Notepadtext.getText().toString();		
		Log.d("Test_Value", "insert Data \t listviewnumber" + listviewnumber + "\t nextValue: " + nextvalue_global);
		Long id = null;
		if(isInsert_global){
			id = dbhelperadapter.insertData(nextvalue_global,Title, Text);
			
			prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
			
			Editor editor = prefs.edit();
			editor.putInt(Integer.toString(listviewnumber), nextvalue_global);
			editor.putInt("NextValue", nextvalue_global + 1);
		    editor.commit();
		    Log.d("Test_Value_insert_edit", "After Insert into prefs \t " + "Key(listview): " + listviewnumber + "vaue(NextValue): " + prefs.getInt(Integer.toString(listviewnumber), 0));
		    if(id!=null){
		    	isInsert_global = false;	
		    }
		    
		    Log.d("Test_insert", "insert Data \t listviewnumber: " + listviewnumber + " \t nextValue: " + nextvalue_global + " \t isInsert_global" + isInsert_global);
		}
		else{
			Log.d("Test_edit_before", " \t NoteFromDB: " + Note_from_Db + "\t Text: " + Text + "\t Title_from_Db: " + Title_from_Db + " \t Title: " + Title);
			if(Note_from_Db.equals(Text) && Title_from_Db.equals(Title)){
				
				Log.d("Test_edit", "insert not done No change done");
			}
			else{
				
				if(!isTransactionviaChangeText){
					Log.d("Test_Value_insert_edit_not_via_changeText","inside isTransactionviaChangeText = false");
					prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
					nextvalue_global = prefs.getInt("NextValue", 0);
					
					listviewnumber = A1interface.getlistViewCount();
					Log.d("Test_Value_insert_edit_not_via_changeText", "After getting valuses \t " + "(listviewnumber): " + listviewnumber + "(NextValue): " + prefs.getInt(Integer.toString(listviewnumber), 0));
					id = dbhelperadapter.insertData(nextvalue_global,Title, Text);
					
					prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
					
					Editor editor = prefs.edit();
					editor.putInt(Integer.toString(listviewnumber), nextvalue_global);
					editor.putInt("NextValue", nextvalue_global + 1);
				    editor.commit();
				    Log.d("Test_Value_insert_edit_not_via_changeText", "After Insert into prefs \t " + "Key(listview): " + listviewnumber + "vaue(NextValue): " + prefs.getInt(Integer.toString(listviewnumber), 0));
				    
				}
				else{
				id = dbhelperadapter.insertData(prefs.getInt(Integer.toString(listviewnumber), 0),Title, Text);
				Log.d("Test_edit", "insert edit \t Key: " + listviewnumber +   "Value: " + prefs.getInt(Integer.toString(listviewnumber), 0) + " \t nextValue: " + nextvalue_global);
				Title_from_Db = Title;
				Note_from_Db = Text;
				}
			}
			
		}
		
		//Log.d("New", "insert Data \t listviewnumber: " + listviewnumber + " \t nextValue: " + nextvalue_global);
		
		Log.d("Test_outside","test outside");
		//int success1 = id.compareTo(null);
		//int success2 = -1;
		//Log.d("Test_outside","test outside after compare");
		if(id == null || id < 0 ){
			Log.d("Test_error", "error_if");
			Toast.makeText(getActivity(), "Error_id", Toast.LENGTH_SHORT).show();
		   	}
		else{
			//Log.d("Test_error", "error_else");
			Log.d("Test_value_insert_edit", "insert data id: " + id);
			String U_id =  dbhelperadapter.getUid(Title,Text);
			//Toast.makeText(getActivity(), "id: " + id + "U_id: " + U_id, Toast.LENGTH_SHORT).show();
			Toast.makeText(getActivity(), "success" + id, Toast.LENGTH_SHORT).show();
			Intent wakeupBroadCastList = new Intent();
			wakeupBroadCastList.setAction("com.notepad.populate_Details_list");
			getActivity().sendBroadcast(wakeupBroadCastList);
			
		}
		 
	     //Log.d("New", "insert data \t id: " + id + " \t U_id: " + U_id);
	    
		
	    //Log.d("New", "insert data \t prefs listviewnumber: " + listviewnumber);
	    
	    
	    
	    //MainActivity.setNextValue(nextvalue_global);
	    //Log.d("New", "insert data \t Nextvalue is set in Mainactivity");
	    //Log.d("Test", "inside insert of FN\t nextvalue_global" + prefs.getInt(Integer.toString(listviewnumber),0) + "listvienumber\t"+ listviewnumber + "hello");
		
		
		//Update the list view in fragment details:
		//A1interface.changelistView(Title);
		
		
		
	}

	public void GetTextandTitleForGivenIndex(int int_U_id){
		Log.d("Test","inside GetTextAndTitle.." + "\t int_U_id: " + int_U_id);
		String TitleAndText = dbhelperadapter.getNotePadText(int_U_id);
		Log.d("Test","inside GetTextAndTitle.." + "\t TitleandText: " + TitleAndText + "Test");
		if(TitleAndText.equals("")){
			Notepadtitle.setText("Enter NoteTitle here");
			Notepadtext.setText("Enter the Note here");
		}
		else{
		Log.d("Test_string","inside GetTextAndTitle.." + "\t TitleandText " + TitleAndText);
		String[] TitleAndText1 = TitleAndText.split(";");
		Log.d("Test","inside GetTextAndTitle.." + "\t Title: " + TitleAndText1[0] + "\t Text: " + TitleAndText1[1] );
		Notepadtitle.setText(TitleAndText1[0]);
		Title_from_Db = TitleAndText1[0];
		Notepadtext.setText(TitleAndText1[1]);
		Note_from_Db = TitleAndText1[1];
		}
	}
	
	
	public interface AfterInsertDataInterface{
		public int getlistViewCount();
	}
	

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		//GetTextandTitleForGivenIndex(0);
		/*Log.d("Activity1_fragmentdetails", "onActivitycreated " + this.toString() + " activityname " + getActivity().toString());
		
		if(listviewnumber == -1)
		{
        listviewnumber = 0;
		
		
		Log.d("Title", "Before SetText");
		String[] title = getResources().getStringArray(R.array.notesname);
		Log.d("Title", title[listviewnumber]);
		Notepadtitle.setText(title[listviewnumber]);
		
		String NotePadText = getNotePadtext();
		Notepadtext.setText(NotePadText);
        
        Log.d("Title", "Before SetText");
		String[] title = getResources().getStringArray(R.array.notesname);
		Log.d("Title", title[listviewnumber]);
		Notepadtitle.setText(title[listviewnumber]);
		
			String NotePadText = getNotePadtext();
			Notepadtext.setText(NotePadText);
		
        
       
		}*/
		
	}

	
	private String getNotePadtext() {
		// TODO Auto-generated method stub
		int uid=-1;
		prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
		Log.d("Listnumber", Integer.toString(listviewnumber) + "listviewnumber");
		Toast.makeText(getActivity(), "fetch" +" listviewnumber: " + Integer.toString(listviewnumber) + "\n"+ "uid: " + prefs.getString(Integer.toString(listviewnumber),""), Toast.LENGTH_SHORT).show();
		if(prefs.contains(Integer.toString(listviewnumber))){
			Log.d("harshashow","Test");
			Log.d("harshashow",prefs.getString(Integer.toString(listviewnumber),"") + "hello");
			uid = Integer.parseInt(prefs.getString(Integer.toString(listviewnumber),""));
		}
		String result = dbhelperadapter.getNotePadText(uid);
		Log.d("Uid", result);
		
		return result;
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
		Log.d("Activity1_fragmentdetails", "onActivityattach "  + this.toString() + " activityname " + getActivity().toString());
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
	public void onHiddenChanged(boolean hidden) {
		// TODO Auto-generated method stub
		super.onHiddenChanged(hidden);
		Log.d("Activity1_fragmentdetails", "onHiddenchanged" + this.toString() + " activityname " + getActivity().toString());
	}



	@Override
	public void onInflate(Activity activity, AttributeSet attrs,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onInflate(activity, attrs, savedInstanceState);
		Log.d("Activity1_fragmentdetails", "oninflate" + this.toString() + " activityname " + getActivity().toString());
	}



	



	

	



	



	



	



	@Override
	public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewStateRestored(savedInstanceState);
	}*/
	
}
