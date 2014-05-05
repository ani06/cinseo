package com.example.login;
import java.io.BufferedReader;
import java.io.IOException;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Movie;
import android.widget.Toast;
public class DBHelper extends SQLiteOpenHelper 
{
 private SQLiteDatabase db;
 public static final String KEY_ROWID = "_id";
    public static final String KEY_EMAIL = "email";
    DBHelper DB = null;

    private static final String DATABASE_NAME = "cinseo.db";
    private static final int DATABASE_VERSION = 2;
    private static final String TABLE_WISHLIST = "wishlist";
       public static final String DATABASE_TABLE_NAME = "users";
       private static final String DATABASE_TABLE_CREATE =
               "CREATE TABLE " + DATABASE_TABLE_NAME + "(" +
               "_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
               " password TEXT NOT NULL, email TEXT NOT NULL);";
   public DBHelper(Context dbHelper) {
     super(dbHelper, DATABASE_NAME, null, DATABASE_VERSION);
     System.out.println("In constructor");
   }
   @Override
   public void onCreate(SQLiteDatabase db) {
    try{
     db.execSQL(DATABASE_TABLE_CREATE);
    }catch(Exception e){
     e.printStackTrace();
    }
    try{
        db.execSQL("CREATE TABLE movies(_id INTEGER PRIMARY KEY AUTOINCREMENT,movie TEXT NOT NULL);");
       }catch(Exception e){
        e.printStackTrace();
       }
    
    
   }
   @Override
   public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    // TODO Auto-generated method stub
   }
   public Cursor rawQuery(String string, String[] strings) {
    // TODO Auto-generated method stub
    return null;
   }
   public void open() {
    getWritableDatabase(); 
   }
   public Cursor getDetails(String text) throws SQLException 
   {
       Cursor mCursor =
               db.query(DATABASE_TABLE_NAME, 
                 new String[]{KEY_ROWID, KEY_EMAIL}, 
                 null, null, null, null, null);
       if (mCursor != null) 
       {
           mCursor.moveToFirst();
       }
       return mCursor;
   }
   
   public String[] addItems(String username) {

         SQLiteDatabase db = getReadableDatabase();

		 String[] columns = {"movie"};
		  String selection = "uname=?";
		  String[] selectionArgs = {username};
		  Cursor cursor = null;

       	  cursor = db.query("wishlist", columns, selection, selectionArgs, null, null, null);
 		 	if(cursor.getCount() >0)
	       {
	           String[] str = new String[cursor.getCount()];
	           int i = 0;

	           while (cursor.moveToNext())
	           {
	                str[i] = cursor.getString(cursor.getColumnIndex("movie"));
	               i++;
	            }
	           return str;
		 
	       }
	      else
	       {
	           return new String[] {};
	       }
        
   }
   public boolean removemovies(String username,String mv) {
	   SQLiteDatabase db = getWritableDatabase();
	 return  db.delete(TABLE_WISHLIST, 
	            "uname" + " = ? AND " + "movie" + " = ?", 
	            new String[] {username, mv+""}) >0;
	  //return db.delete("wishlist", 
	    //        KEY_EMAIL + " = ? AND" + "movie" + " = ?", 
	      //      new String[] {username,mv+""})>0;
	  // return true;
	   //return db.delete(TABLE_WISHLIST, KEY_EMAIL+"="+username, "movies"+"="+mv);
	 //  return db.delete(TABLE_WISHLIST, KEY_EMAIL + "=" + username +"AND" + "movies" + "=" + mv ,null) > 0;
	  // return true;
 }
	 public String[] getAllMovies(){
		 
		 SQLiteDatabase db = getWritableDatabase();
		 Cursor cursor = db.query("movies",new String[] {"movie"},null, null, null, null, null);
		 
		 	if(cursor.getCount() >0)
	       {
	           String[] str = new String[cursor.getCount()];
	           int i = 0;

	           while (cursor.moveToNext())
	           {
	                str[i] = cursor.getString(cursor.getColumnIndex("movie"));
	                i++;
	            }
	           return str;
	       }
	       else
	       {
	           return new String[] {};
	       }
	   }  
   public void movieTableCreation(BufferedReader br){
	   
	   		SQLiteDatabase db = getWritableDatabase();
		    String mv="NIL";
		    String readLine="NIL";
		    final String DATABASE_TABLE_WISHLIST=
		   		   "CREATE TABLE " + "wishlist" + "(" +
		    	       "_id INTEGER PRIMARY KEY AUTOINCREMENT,uname TEXT NOT NULL,movie TEXT NOT NULL);";
		      try
		      {
			      db.execSQL(DATABASE_TABLE_WISHLIST);
		      }
		      catch(Exception e)
		      {
		       e.printStackTrace();
		      }
/**
		    try {
		    	 db.execSQL("CREATE TABLE movies(_id INTEGER PRIMARY KEY AUTOINCREMENT,movie TEXT NOT NULL);");
		    try {
		    	while ((readLine = br.readLine()) != null) {
		    	String words[] = readLine.split("\\|");
		    
		   	 
		      ContentValues values = new ContentValues();
		      values.put("movie", words[0]);
		      try
		      {
		      db.insert("movies", null, values);
		      }
		      catch(Exception e)
		      {
		       e.printStackTrace();
		      }
		
		    }
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		    }
		    catch (Exception e)
		    {e.printStackTrace();}
	**/	    
	   }
   public void addmovie(String new_movie, String username)
	{
		 SQLiteDatabase db = getWritableDatabase();

	      ContentValues values = new ContentValues();
	      values.put("Uname",username.toString());
	      values.put("movie", new_movie);
	      try
	      {
	      db.insert(DBWishlist.TABLE_WISHLIST, null, values);
	      }
	      catch(Exception e)
	      {
	       e.printStackTrace();
	      }

	}
   
 
}
   