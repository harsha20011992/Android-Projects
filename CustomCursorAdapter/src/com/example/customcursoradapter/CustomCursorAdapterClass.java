package com.example.customcursoradapter;

import com.example.customcursoradapter.DataBaseHelperAdapter.DataBaseHelper;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CustomCursorAdapterClass extends CursorAdapter {

	
    Context basecontext;
    LayoutInflater inflater;
	public CustomCursorAdapterClass(Context context, Cursor c,int flags) {
		super(context, c,flags);
		// TODO Auto-generated constructor stub
		
		basecontext = context;
		inflater = (LayoutInflater) basecontext.getSystemService(basecontext.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public void bindView(View view, Context context, Cursor c) {
		// TODO Auto-generated method stub
		TextView U_id = (TextView) view.findViewById(R.id.cust_u_id);
		TextView text_title = (TextView) view.findViewById(R.id.cus_text_title);
		TextView text_note = (TextView) view.findViewById(R.id.cust_text_note);
		
		String uid = Integer.toString(c.getInt(c.getColumnIndex(DataBaseHelper.U_ID))); 
		String title = c.getString(c.getColumnIndex(DataBaseHelper.Note_Title));
		String note = c.getString(c.getColumnIndex(DataBaseHelper.Note_Text));
		
		U_id.setText(uid);
		text_title.setText(title);
		text_note.setText(note);
	}

	@Override
	public View newView(Context view, Cursor c, ViewGroup viewgroup) {
		// TODO Auto-generated method stub
	 return inflater.inflate(R.layout.customcursoradapterclass, viewgroup, false);
	}

}
