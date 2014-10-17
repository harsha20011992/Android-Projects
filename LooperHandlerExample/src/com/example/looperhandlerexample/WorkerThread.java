package com.example.looperhandlerexample;

import android.os.Handler;
import android.os.Looper;

public class WorkerThread extends Thread{
	Handler mainThreadHandler,workerThreadHandler;
	public WorkerThread(Handler mainThreadHandler){
		this.mainThreadHandler = mainThreadHandler;
	}
	
		public void run() {
			Looper.prepare();
			workerThreadHandler = new Handler();
			Looper.loop();
		}
	
       public Handler getHandler(){
    	   return workerThreadHandler;
       }
}
