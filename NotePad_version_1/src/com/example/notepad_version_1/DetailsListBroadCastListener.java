package com.example.notepad_version_1;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;

public class DetailsListBroadCastListener extends BroadcastReceiver{
	static FragmentDetails f1;
	
	public void setFragment(FragmentDetails f){
		f1 = f;
		Log.d("Test", "insidesettFragment: "  + f1.toString());
	}
	@Override
	public void onReceive(Context arg0, Intent intent) {
		// TODO Auto-generated method stub
		Log.d("Test","Before Test broadcast");
		Log.d("Test", "inside on receive" + f1.toString());
		f1.cursor.requery();
		f1.c.notifyDataSetChanged();
		int listviewnumber = intent.getIntExtra("listviewnumber", 0);
		
		
		//Log.d("Test", "inside BroadcastListener: "  + listviewnumber);
		
		
		/*for (int j = 0; j < f1.l1.getChildCount(); j++)
            f1.l1.getChildAt(j).setBackgroundColor(Color.TRANSPARENT);
		
		
		
		View view = f1.l1.getChildAt(listviewnumber);
		view.setBackgroundColor(Color.YELLOW);*/
		
		f1.l1.smoothScrollToPosition(listviewnumber);
		
		
		Log.d("Test","Test broadcast");
	}

	
	
}
