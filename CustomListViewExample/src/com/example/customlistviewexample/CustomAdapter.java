package com.example.customlistviewexample;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<String>{
 
	public LayoutInflater inflater;
	public CustomAdapter(Context context,
			String[] items) {
		super(context,R.layout.customlist,items);
		inflater = (LayoutInflater) context
			        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		View view = inflater.inflate(R.layout.customlist, parent,false);
		
		
		TextView t1 = (TextView) view.findViewById(R.id.editText1);
		t1.setText("Hello" + Integer.toString(position));
		
		TextView t2 = (TextView) view.findViewById(R.id.editText2);
		t2.setText("Hello" + Integer.toString(position));
		
		
		return view;
	}

	
}
