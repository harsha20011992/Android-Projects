package com.example.customcursoradapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DataBaseHelperAdapter {
	
	DataBaseHelper dbhelper;

	public DataBaseHelperAdapter(Context context) {
		dbhelper = new DataBaseHelper(context);
	}

	public long insertData(int U_id,String title, String text) {
		SQLiteDatabase sqldb = dbhelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(DataBaseHelper.U_ID, U_id);
		values.put(DataBaseHelper.Note_Text, text);
		values.put(DataBaseHelper.Note_Title, title);
		
		long id  = sqldb.insertWithOnConflict(dbhelper.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
		
		return id;
	}

	public Cursor getAllDetails() {
		SQLiteDatabase sqldb = dbhelper.getWritableDatabase();
		String[] columns = { dbhelper.U_ID, dbhelper.Note_Text,
				dbhelper.Note_Title };

		Cursor cursor = sqldb.query(dbhelper.TABLE_NAME, columns, null, null,
				null, null, null);
		/*StringBuffer buffer = new StringBuffer();
		while (cursor.moveToNext()) {
			int index = cursor.getColumnIndex(dbhelper.U_ID);
			int cid = cursor.getInt(index);

			index = cursor.getColumnIndex(dbhelper.Note_Text);
			String Text_Note = cursor.getString(index);

			index = cursor.getColumnIndex(dbhelper.Note_Title);
			String Title_Note = cursor.getString(index);

			buffer.append(cid + " " + Text_Note + Title_Note + "\n");

		}

		return buffer.toString();*/
		
		if(cursor != null){
			cursor.moveToFirst();
		}
		return cursor;
	}

	
	
	public String getData(String Title)

	{
		SQLiteDatabase sqldb = dbhelper.getWritableDatabase();
		String[] columns = { dbhelper.U_ID, dbhelper.Note_Text };

		Cursor cursor = sqldb.query(dbhelper.TABLE_NAME, columns,
				dbhelper.Note_Title + " = '" + Title + "'", null, null, null,
				null);
		StringBuffer buffer = new StringBuffer();
		while (cursor.moveToNext()) {
			int index = cursor.getColumnIndex(dbhelper.U_ID);
			int cid = cursor.getInt(index);

			index = cursor.getColumnIndex(dbhelper.Note_Text);
			String Text_Note = cursor.getString(index);

			buffer.append(cid + " " + Text_Note + "\n");

		}
		return buffer.toString();

	}

	public String getUid(String Text, String Title)

	{
		SQLiteDatabase sqldb = dbhelper.getWritableDatabase();
		String[] columns = {dbhelper.U_ID};
		String[] selectionArgs = {Text,Title};
		Cursor cursor = sqldb.query(dbhelper.TABLE_NAME, columns,
				dbhelper.Note_Title + " =? AND " + dbhelper.Note_Text + " =? ",
				selectionArgs, null, null, null);
		
		StringBuffer buffer = new StringBuffer();
		while (cursor.moveToNext()) {
			int index = cursor.getColumnIndex(dbhelper.U_ID);
			int cid = cursor.getInt(index);

			
			buffer.append(Integer.toString(cid));

		}
		Log.d("buffer", buffer.toString());
		return buffer.toString();
        
	}

	
	public String getNotePadText(int Uid)

	{
		SQLiteDatabase sqldb = dbhelper.getWritableDatabase();
		String[] columns = { dbhelper.Note_Text };

		Cursor cursor = sqldb.query(dbhelper.TABLE_NAME, columns,
				dbhelper.U_ID + " = '" + Uid + "'", null, null, null,
				null);
		StringBuffer buffer = new StringBuffer();
		while (cursor.moveToNext()) {
			
			int index = cursor.getColumnIndex(dbhelper.Note_Text);
			String Text_Note = cursor.getString(index);

			buffer.append(Text_Note);

		}
		return buffer.toString();

	}

	
	public static class DataBaseHelper extends SQLiteOpenHelper {
		private static final String DATABASE_NAME = "NotePAD_TEST5";
		private static final String TABLE_NAME = "NotepadText";
		private static final int DATABASE_VERSION = 2;
		 static final String U_ID = "_id";
		static final String Note_Text = "Note_Text";
		static final String Note_Title = "Note_Title";
		private Context context;
		private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
				+ " (" + U_ID + " INTEGER PRIMARY KEY,"
				+ Note_Title + " VARCHAR(255)," + Note_Text + " VARCHAR(255));";

		private static final String DROP_TABLE = "DROP TABLE IF EXISTS "
				+ TABLE_NAME;

		public DataBaseHelper(Context context1) {
			super(context1, DATABASE_NAME, null, DATABASE_VERSION);
			context = context1;
			Toast.makeText(context, "constructor called", Toast.LENGTH_SHORT)
					.show();
			Log.d("create", "constructor called");
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			Toast.makeText(context, "oncreate called", Toast.LENGTH_SHORT)
					.show();
			Log.d("create", "on create called");
			try {
				db.execSQL(CREATE_TABLE);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT)
						.show();
				Log.d("exception", e.toString());
			}

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
			// TODO Auto-generated method stub
			Log.d("upgrade", "upgrade called1");
			db.execSQL(DROP_TABLE);
			onCreate(db);
			Toast.makeText(context, "onupgrade called", Toast.LENGTH_SHORT)
					.show();
		}

	}

}
