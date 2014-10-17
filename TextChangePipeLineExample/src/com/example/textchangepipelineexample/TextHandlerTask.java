package com.example.textchangepipelineexample;

import java.io.IOException;
import java.io.PipedReader;

import android.util.Log;

public class TextHandlerTask implements Runnable{

	PipedReader reader;
	
	public TextHandlerTask(PipedReader r)
	{
		reader = r;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Log.d("TextChanged","Test3");
		while(!Thread.currentThread().isInterrupted())
		{
			int i;
			try {
				while((i = reader.read()) != -1)
				{
					char c = (char) i;
					Log.d("reading" + Integer.toString(i), Character.toString(c));
					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	

}
