package com.example.bloodgroup_original;

import org.json.JSONObject;

public class HttpPostToServer implements HttpPostCallBack {

	String Server_URL;
	
	public void PostToServer(){
		Server_URL = "Test.com";
		// Try to pass the json or http post object that must carry all the parameters in future.
		Parser_Json serverpost = new Parser_Json(this);
		serverpost.execute(Server_URL);
	}
	
	@Override
	public void callback(JSONObject resultJson) {
		// TODO Auto-generated method stub
		
	}

}
