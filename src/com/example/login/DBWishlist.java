package com.example.login;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBWishlist extends SQLiteOpenHelper{
	 private SQLiteDatabase db;
	 public static final String KEY_ROWID = "_id";
	    public static final String KEY_UNAME = "uname";
	    DBWishlist DB = null;
	    private static final String DATABASE_NAME = "cinseo.db";
	    private static final int DATABASE_VERSION = 2;
	    public static final String TABLE_WISHLIST= "wishlist";
	    public static final String DATABASE_TABLE_WISHLIST=
	    		   "CREATE TABLE" + TABLE_WISHLIST + "(" +
	    	       "_id INTEGER PRIMARY KEY,uname TEXT NOT NULL,movie TEXT NOT NULL);";

	    public DBWishlist(Context context) {
	        super(context, DATABASE_NAME, null, DATABASE_VERSION);
	        System.out.println("In constructor");
	      }

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			try{
			      db.execSQL(DATABASE_TABLE_WISHLIST);
			     }catch(Exception e){
			      e.printStackTrace();
			     }
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			
		}

	  
}
