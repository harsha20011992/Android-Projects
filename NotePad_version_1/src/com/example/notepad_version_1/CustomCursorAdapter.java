package com.example.notepad_version_1;

import com.example.notepad_version_1.DataBaseHelperAdapter.DataBaseHelper;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CustomCursorAdapter extends CursorAdapter {

	
    Context basecontext;
    LayoutInflater inflater;
    DataBaseHelperAdapter dbhelperadapter;
    static int selectedItem=-1;
	public CustomCursorAdapter(Context context, Cursor c,int flags) {
		super(context, c,flags);
		// TODO Auto-generated constructor stub
		
		basecontext = context;
		inflater = (LayoutInflater) basecontext.getSystemService(basecontext.LAYOUT_INFLATER_SERVICE);
		dbhelperadapter = new DataBaseHelperAdapter(basecontext);
	}

	public static void setSelectedItem(int item){
		selectedItem = item;
	}
	
	@Override
	public View getView(int position, View view, ViewGroup viewgroup) {
		// TODO Auto-generated method stub
		
		Log.d("Test_workflow","Inside customcursor adapter getview" + "position: " + position + " \t selecteditem: " + selectedItem);
		
		if(position ==selectedItem){
			Log.d("Test_workflow_true","Inside customcursor adapter getview" + "position: " + position + " \t selecteditem: " + selectedItem);
			for (int j = 0; j < viewgroup.getChildCount(); j++)
	            viewgroup.getChildAt(j).setBackgroundColor(Color.TRANSPARENT);
		
			view.setBackgroundColor(Color.YELLOW);
			
		}
		
		return super.getView(position, view, viewgroup);
	}

	@Override
	public void bindView(View view, Context context, Cursor c) {
		// TODO Auto-generated method stub
		TextView U_id = (TextView) view.findViewById(R.id.cursor_TextU_id);
		TextView text_title = (TextView) view.findViewById(R.id.cursor_TextTitle);
		TextView text_note = (TextView) view.findViewById(R.id.cursor_TextNote);
		String[] columns = dbhelperadapter.returnColumns();
		
		String uid = Integer.toString(c.getInt(c.getColumnIndex(columns[0]))); 
		String title = c.getString(c.getColumnIndex(columns[1]));
		String note = c.getString(c.getColumnIndex(columns[2]));
		
		U_id.setText(uid);
		text_title.setText(title);
		text_note.setText(note);
		//Log.d("Test_workflow","Inside customcursor adapter bindview");
	}

	@Override
	public View newView(Context view, Cursor c, ViewGroup viewgroup) {
		// TODO Auto-generated method stub
		//Log.d("Test_workflow","Inside customcursor adapter newview");
	 return inflater.inflate(R.layout.detailscursorlayout, viewgroup, false);
	}

}
