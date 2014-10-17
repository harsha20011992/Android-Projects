package com.example.sample_fb;

import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.HttpMethod;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphObject;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;

public class MainActivity extends Activity {

	String APP_ID;
	TextView t, flist;
	Button b, friends;
	LoginButton fbButton;
	private UiLifecycleHelper uiHelper;
	private Session.StatusCallback callback;
	static int count = 0;
	static int count1 = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		final Bundle saveinstance = savedInstanceState;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		APP_ID = getString(R.string.facebook_app_id);
		 //final Facebook fb = new Facebook(APP_ID);
		t = (TextView) findViewById(R.id.fbtext);
		flist = (TextView) findViewById(R.id.fbfriendlist);
		b = (Button) findViewById(R.id.fbbuton);
        fbButton = (LoginButton) findViewById(R.id.authButton);    
		friends = (Button) findViewById(R.id.fbfriends);
		fbButton.setReadPermissions(Arrays.asList("user_location", "user_birthday", "user_likes","user_friends"));
		
		friends.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				
				
				
				Session s = new Session(MainActivity.this);
				Session.setActiveSession(s);
				Session.OpenRequest request = new Session.OpenRequest(MainActivity.this);
				request.setPermissions(Arrays.asList("basic_info","email","user_friends"));
				request.setCallback( new Session.StatusCallback() {
				   // callback when session changes state
				             @Override
				             public void call(Session session, SessionState state, Exception exception) {
				                 if (session.isOpened()) {
				                     /*Request.newMeRequest(session, new Request.GraphUserCallback() {
				                         @Override
				                         public void onCompleted(GraphUser user, Response response) {
				                             if (user != null) {

				Toast.makeText(getApplicationContext(), "User email is:"+user.getProperty("email"), Toast.LENGTH_SHORT).show(); } 
				else {
				Toast.makeText(getApplicationContext(), "Error User Null", Toast.LENGTH_SHORT).show();
				}
				}
				}).executeAsync();*/
				                	
				                	 new Request(
				                			    session,
				                			    "/me/friends",
				                			    null,
				                			    HttpMethod.GET,
				                			    new Request.Callback() {
				                			        public void onCompleted(Response response) {
				                			            /* handle the result */
				                			        	flist.setText(response.toString());
				                			        	
				                			        	//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				                			        	
				                			        	
				                			        	GraphObject graphObject = response
																.getGraphObject();

														String s = "test";
														//flist.setText("before graph object null");
														if (graphObject != null) {
															flist.setText("after graph object null");
															JSONObject jsonObject = graphObject
																	.getInnerJSONObject();
															
															flist.setText(jsonObject.toString());
															try {
																JSONArray array = jsonObject
																		.getJSONArray("data");
																
																for (int i = 0; i < array
																		.length(); i++) {
																	JSONObject object = (JSONObject) array
																			.get(i);
																	Log.d("Test",
																			"id = "
																					+ object.get("id"));
																
																
																s = (object.get("id").toString());
																}
																
															} catch (JSONException e) {
																flist.setText(e.toString());
																e.printStackTrace();
															}

				                			        	
														}
				                			        	
				                			        	
				                			        	
				                			        	
				                			        	//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				                			        }
				                			        }
				                			).executeAsync();
				                	 
				} 
				             }
				         }); //end of call;

				s.openForRead(request); //now do the request above

				
				
				
				
				
				
				
				
				
			/*	Session.OpenRequest request = new Session.OpenRequest(MainActivity.this);
				
				
				request.setPermissions(Arrays.asList("user_location", "user_birthday", "user_likes","user_friends"));
				
				Session mFacebookSession = Session.getActiveSession();
				if (mFacebookSession == null || mFacebookSession.isClosed()) 
				{
				    mFacebookSession = new Session(MainActivity.this);
				}
				mFacebookSession.openForRead(request);
				
				
				request.setCallback(new StatusCallback() {
				
					
					@Override
					public void call(Session session, SessionState state, Exception exception) {
						// TODO Auto-generated method stub
						new Request(session, "/{friendlist-id}",
								null, HttpMethod.GET,
								new Request.Callback() {
									public void onCompleted(
											Response response) {
										flist.setText(response.toString());
									
										GraphObject graphObject = response
												.getGraphObject();

										String s = "a";
										//flist.setText("before graph object null");
										if (graphObject != null) {
											flist.setText("after graph object null");
											JSONObject jsonObject = graphObject
													.getInnerJSONObject();
											try {
												JSONArray array = jsonObject
														.getJSONArray("data");
												for (int i = 0; i < array
														.length(); i++) {
													JSONObject object = (JSONObject) array
															.get(i);
													Log.d("Test",
															"id = "
																	+ object.get("id"));
													s = s
															+ object.get(
																	"id")
																	.toString();
												}
												flist.setText(s);
											} catch (JSONException e) {
												flist.setText(e.toString());
												e.printStackTrace();
											}

											
											
										}

									}
								}).executeAsync();

					}
				});*/
				
				
				
			}});
				
		
		
				/*Session.openActiveSession(MainActivity.this, true,
						new Session.StatusCallback() {

							// callback when session changes state
							@Override
							public void call(Session session,
									SessionState state, Exception exception) {
								flist.setText("inside call");
								if (session.isOpened()) {
									flist.setText("session opened");
									Log.d("Test", "Session Open " + count++);
									// make request to the /me API
									
									new Request(session, "/{friendlist-id}",
											null, HttpMethod.GET,
											new Request.Callback() {
												public void onCompleted(
														Response response) {
													flist.setText(response.toString());
												
													GraphObject graphObject = response
															.getGraphObject();

													String s = "a";
													//flist.setText("before graph object null");
													if (graphObject != null) {
														flist.setText("after graph object null");
														JSONObject jsonObject = graphObject
																.getInnerJSONObject();
														try {
															JSONArray array = jsonObject
																	.getJSONArray("data");
															for (int i = 0; i < array
																	.length(); i++) {
																JSONObject object = (JSONObject) array
																		.get(i);
																Log.d("Test",
																		"id = "
																				+ object.get("id"));
																s = s
																		+ object.get(
																				"id")
																				.toString();
															}
															flist.setText(s);
														} catch (JSONException e) {
															flist.setText(e.toString());
															e.printStackTrace();
														}

														
														
													}

												}
											}).executeAsync();
									
									
									
									
									
									/*Request.newMyFriendsRequest(session, new GraphUserListCallback() {

									    @Override
									    public void onCompleted(List<GraphUser> users, Response response) 
									    {
									    	
									    	flist.setText(response.toString());
									        if(response.getError() == null)
									        {
									        	//flist.setText(Integer.toString(users.size()));
									        	
									            for (int i = 0; i < users.size(); i++) {
									                Log.e("users", "users " + users.get(i).getName());
									                //flist.setText(users.get(i).getName());
									            }
									        }
									        else
									        {
									            Toast.makeText(MainActivity.this, 
									                           response.getError().getErrorMessage(), 
									                           Toast.LENGTH_SHORT).show();
									        }
									    }
									}).executeAsync();
									
									
									
									
									
									
									
								}

								else {
									Log.d("Test", "session closed" + count++);
									t.setText("Log in to see details");

								}

							}
						});

			}
		});*/

		Log.d("Test", "Beforw Session");
		b.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Log.d("Test", "clcked" + count++);
				Session.openActiveSession(MainActivity.this, true,
						new Session.StatusCallback() {

							// callback when session changes state
							@Override
							public void call(Session session,
									SessionState state, Exception exception) {
								if (session.isOpened()) {
									Log.d("Test", "Session Open " + count++);
									// make request to the /me API
									Request.newMeRequest(session,
											new Request.GraphUserCallback() {

												// callback after Graph API
												// response with user
												// object
												@Override
												public void onCompleted(
														GraphUser user,
														Response response) {
													if (user != null) {
														t.setText("Hello "
																+ user.getName()
																+ "!");
													}
												}
											}).executeAsync();
								}

								else {
									Log.d("Test", "session closed" + count++);
									t.setText("Log in to see details");

								}

							}
						});

			}
		});
		/*
		 * callback = new Session.StatusCallback() {
		 * 
		 * @Override public void call(Session session, SessionState state,
		 * Exception exception) { onSessionStateChange(session, state,
		 * exception); uiHelper.onCreate(saveinstance); } };
		 */

		// uiHelper = new UiLifecycleHelper(this, callback);

	}

	private void onSessionStateChange(Session session, SessionState state,
			Exception exception) {
		// Only make changes if the activity is visible
		if (state.isOpened()) {
			// If the session state is open:
			// Show the authenticated fragment
			Log.d("Test", "status is open" + count1++);

		} else if (state.isClosed()) {
			// If the session state is closed:
			// Show the login fragment
			Log.d("Test", "status is closed" + count1++);
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		Session.getActiveSession().onActivityResult(this, requestCode,
				resultCode, data);

	}

}
