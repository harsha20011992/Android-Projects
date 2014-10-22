package com.example.notepad_version_1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class DetailsListBroadCastListener extends BroadcastReceiver{
	static FragmentDetails f1;
	
	public void setFragment(FragmentDetails f){
		f1 = f;
		Log.d("Test", "insidesettFragment: "  + f1.toString());
	}
	@Override
	public void onReceive(Context arg0, Intent arg1) {
		// TODO Auto-generated method stub
		Log.d("Test","Before Test broadcast");
		Log.d("Test", "inside on receive" + f1.toString());
		f1.cursor.requery();
		f1.c.notifyDataSetChanged();
		
		Log.d("Test","Test broadcast");
	}

	
	
}
