package com.example.login;
import java.io.*;
import java.util.*;

import android.R.integer;
import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class SearchMovies extends Activity{
	
	private Button bt1,bt2,bt3,bt4,bt5,bt6,bt7,bt8,bt9,bt10;
//	protected DBWishlist DB = new DBWishlist(SearchMovies.this);
	DBHelper DBH = new DBHelper(SearchMovies.this);  
	public String username;   
	ArrayAdapter<String> adapter;
	AutoCompleteTextView atv,atv1;
	private String movie;
	 private String[] mv_chk;
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchmovies);
        Intent intent = getIntent();
 	    username = intent.getExtras().getString("username");
 	    InputStream is = this.getResources().openRawResource(R.raw.interout1);
	    BufferedReader br = new BufferedReader(new InputStreamReader(is));
	    
 	    DBH.movieTableCreation(br);

       // String[] movieList = DBH.getAllMovies();
 	   ArrayList<String> movieList = new ArrayList<String>();
        movieList=getmovies();
        atv1 = (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView1);
        bt1=(Button) findViewById(R.id.bt1);
        bt2=(Button) findViewById(R.id.bt2);
        bt3=(Button) findViewById(R.id.bt3);
        bt4=(Button) findViewById(R.id.bt4);
        bt5=(Button) findViewById(R.id.bt5);
        bt6=(Button) findViewById(R.id.bt6);
        bt7=(Button) findViewById(R.id.bt7);
        bt8=(Button) findViewById(R.id.bt8);
        bt9=(Button) findViewById(R.id.bt9);
        bt10=(Button) findViewById(R.id.bt10);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, movieList);
        atv1.setAdapter(adapter);
		//Toast.makeText (SearchMovies.this,movieList[0], Toast.LENGTH_SHORT).show();
		//Toast.makeText (SearchMovies.this,movieList[1], Toast.LENGTH_SHORT).show();
        atv1.setOnItemClickListener( new OnItemClickListener (){
	
		@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3 ){
				// TODO Auto-generated method stub
				movie = arg0.getItemAtPosition(arg2).toString();
			{
				TextView tv1 = (TextView) findViewById(R.id.tvsb1);
				TextView tv2 = (TextView) findViewById(R.id.tvsb2);
				TextView tv3 = (TextView) findViewById(R.id.tvsb3);
				TextView tv4 = (TextView) findViewById(R.id.tvsb4);
				TextView tv5 = (TextView) findViewById(R.id.tvsb5);
				TextView tv6 = (TextView) findViewById(R.id.tvsb6);
				TextView tv7 = (TextView) findViewById(R.id.tvsb7);
				TextView tv8 = (TextView) findViewById(R.id.tvsb8);
				TextView tv9 = (TextView) findViewById(R.id.tvsb9);
				TextView tv10 = (TextView) findViewById(R.id.tvsb10);
				try 
				{
					Searchreltdmovies(tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9,tv10,movie);
				} 	catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					}
				return;
			}
		
			}
	});
}
	
	public void Searchreltdmovies(TextView tv1,TextView tv2,TextView tv3,TextView tv4,TextView tv5,TextView tv6,TextView tv7,TextView tv8,TextView tv9,TextView tv10, String movie) throws IOException
	{	
			String match=ReadKNNFiles(movie);
			if(match=="NIL")
			{
				Toast.makeText (SearchMovies.this,"Movie not found!!", Toast.LENGTH_SHORT).show();
			}
			else
			{
			String words[]=match.split("\\|");
		for(int i=1;i<=10;i++)
		{	
		switch(i)
		{
			case 1:tv1.setText(words[1]);
					bt1.setText("Add Movie");
			bt1.setBackgroundColor(Color.LTGRAY);
			final String mve1=words[1];
			bt1.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					int f=0;
					
					//bt1.setEnabled(false);
				//	Toast.makeText (SearchMovies.this,"hi"+ " added to wishlist successfully!!!", Toast.LENGTH_SHORT).show();
					mv_chk=DBH.addItems(username);
				      for(int i=0;i<mv_chk.length;i++)
				      {
				    	  if(mv_chk[i].equals(mve1))
				    	  {
				    		  Toast.makeText (SearchMovies.this,"Movie already in Wishlist!!", Toast.LENGTH_SHORT).show();
				    		  f=1;
				    		 
				    	  }
				      }
					if(f==0){DBH.addmovie(mve1,username);bt1.setText("Added");
					bt1.setBackgroundColor(Color.GREEN);}
					
				}
			});
					break;
			case 2:tv2.setText(words[2]);
			
			bt2.setText("Add Movie");
			bt2.setBackgroundColor(Color.LTGRAY);
			final String mve2=words[2];
			bt2.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					int f2=0;
					
				//	bt2.setEnabled(false);
					//Toast.makeText (SearchMovies.this,sb1.getText().toString()+ " added to wishlist successfully!!!", Toast.LENGTH_SHORT).show();	
					mv_chk=DBH.addItems(username);
				      for(int i=0;i<mv_chk.length;i++)
				      {
				    	  if(mv_chk[i].equals(mve2))
				    	  {
				    		  Toast.makeText (SearchMovies.this,"Movie already in Wishlist!!", Toast.LENGTH_SHORT).show();
				    		  f2=1;
				    	  }
				      }
					if(f2==0){DBH.addmovie(mve2,username);bt2.setText("Added");
					bt2.setBackgroundColor(Color.GREEN);}
				}
			});
			break;
			case 3:tv3.setText(words[3]);
			bt3.setText("Add Movie");
			bt3.setBackgroundColor(Color.LTGRAY);
			final String mve3=words[3];
			bt3.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					int f3=0;
					mv_chk=DBH.addItems(username);
				      for(int i=0;i<mv_chk.length;i++)
				      {
				    	  if(mv_chk[i].equals(mve3))
				    	  {
				    		  Toast.makeText (SearchMovies.this,"Movie already in Wishlist!!", Toast.LENGTH_SHORT).show();
				    		  f3=1;
				    	  }
				      }
					//bt3.setEnabled(false);
					//Toast.makeText (SearchMovies.this,sb1.getText().toString()+ " added to wishlist successfully!!!", Toast.LENGTH_SHORT).show();
					if(f3==0){DBH.addmovie(mve3,username);bt3.setText("Added");
					bt3.setBackgroundColor(Color.GREEN);
					}
				}
			});
			break;
			case 4:tv4.setText(words[4]);
		bt4.setText("Add Movie");
			bt4.setBackgroundColor(Color.LTGRAY);
			final String mve4=words[4];
			bt4.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					int f4=0;
					//bt4.setEnabled(false);
					//Toast.makeText (SearchMovies.this,sb1.getText().toString()+ " added to wishlist successfully!!!", Toast.LENGTH_SHORT).show();
					mv_chk=DBH.addItems(username);
				      for(int i=0;i<mv_chk.length;i++)
				      {
				    	  if(mv_chk[i].equals(mve4))
				    	  {
				    		  Toast.makeText (SearchMovies.this,"Movie already in Wishlist!!", Toast.LENGTH_SHORT).show();
				    		  f4=1;
				    	  }
				      }
				  	if(f4==0){DBH.addmovie(mve4,username);
					bt4.setText("Added");
					bt4.setBackgroundColor(Color.GREEN);}
				}
			});
			break;
			case 5:tv5.setText(words[5]);
			bt5.setText("Add Movie");
			bt5.setBackgroundColor(Color.LTGRAY);
			final String mve5=words[5];
			bt5.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//bt5.setEnabled(false);
					//Toast.makeText (SearchMovies.this,sb1.getText().toString()+ " added to wishlist successfully!!!", Toast.LENGTH_SHORT).show();
					int f5=0;
					mv_chk=DBH.addItems(username);
				      for(int i=0;i<mv_chk.length;i++)
				      {
				    	  if(mv_chk[i].equals(mve5))
				    	  {
				    		  Toast.makeText (SearchMovies.this,"Movie already in Wishlist!!", Toast.LENGTH_SHORT).show();
				    		  f5=1;
				    	  }
				      }
					if(f5==0)
					{	
					DBH.addmovie(mve5,username);

					bt5.setText("Added");
					bt5.setBackgroundColor(Color.GREEN);
				
					}
					
					}
			});
			break;
			case 6:tv6.setText(words[6]);
			bt6.setText("Add Movie");
			bt6.setBackgroundColor(Color.LTGRAY);
			final String mve6=words[6];
			bt6.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//bt6.setEnabled(false);
					//Toast.makeText (SearchMovies.this,sb1.getText().toString()+ " added to wishlist successfully!!!", Toast.LENGTH_SHORT).show();	
					int f6=0;
					mv_chk=DBH.addItems(username);
				      for(int i=0;i<mv_chk.length;i++)
				      {
				    	  if(mv_chk[i].equals(mve6))
				    	  {
				    		  Toast.makeText (SearchMovies.this,"Movie already in Wishlist!!", Toast.LENGTH_SHORT).show();
				    		  f6=1;
				    	  }
				      }
					if(f6==0)
					{
					DBH.addmovie(mve6,username);
					bt6.setText("Added");
					//bt6.setEnabled(false);
					bt6.setBackgroundColor(Color.GREEN);
					
					}
					
					}
					
			});
			break;
			case 7:tv7.setText(words[7]);
			bt7.setText("Add Movie");
			bt7.setBackgroundColor(Color.LTGRAY);
			final String mve7=words[7];
			bt7.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					int f7=0;
					//bt7.setEnabled(false);
					//Toast.makeText (SearchMovies.this,sb1.getText().toString()+ " added to wishlist successfully!!!", Toast.LENGTH_SHORT).show();
					mv_chk=DBH.addItems(username);
				      for(int i=0;i<mv_chk.length;i++)
				      {
				    	  if(mv_chk[i].equals(mve7))
				    	  {
				    		  Toast.makeText (SearchMovies.this,"Movie already in Wishlist!!", Toast.LENGTH_SHORT).show();
				    		  f7=1;
				    	  }
				      }
					if(f7==0)
					{
					DBH.addmovie(mve7,username);
					bt7.setText("Added");
					bt7.setBackgroundColor(Color.GREEN);
					
					}
				}
			});
			break;
			case 8:tv8.setText(words[8]);
			final String mve8=words[8];
			bt8.setText("Add Movie");
			bt8.setBackgroundColor(Color.LTGRAY);
			bt8.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					int f8=0;
					mv_chk=DBH.addItems(username);
				      for(int i=0;i<mv_chk.length;i++)
				      {
				    	  if(mv_chk[i].equals(mve8))
				    	  {
				    		  Toast.makeText (SearchMovies.this,"Movie already in Wishlist!!", Toast.LENGTH_SHORT).show();
				    		  f8=1;
				    	  }
				      }
					
					//bt8.setEnabled(false);
					//Toast.makeText (SearchMovies.this,sb1.getText().toString()+ " added to wishlist successfully!!!", Toast.LENGTH_SHORT).show();	
					if(f8==0)
					{
				      DBH.addmovie(mve8,username);
				  	bt8.setText("Added");
					bt8.setBackgroundColor(Color.GREEN);
				
				}}
			});
			break;
			case 9:tv9.setText(words[9]);
			bt9.setText("Add Movie");
			bt9.setBackgroundColor(Color.LTGRAY);
			final String mve9=words[9];
			bt9.setOnClickListener(new View.OnClickListener() {	
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					int f9=0;
					mv_chk=DBH.addItems(username);
				      for(int i=0;i<mv_chk.length;i++)
				      {
				    	  if(mv_chk[i].equals(mve9))
				    	  {
				    		  Toast.makeText (SearchMovies.this,"Movie already in Wishlist!!", Toast.LENGTH_SHORT).show();
				    		  f9=1;
				    	  }
				      }
					
					//bt6.setEnabled(false);
					////Toast.makeText (SearchMovies.this,sb1.getText().toString()+ " added to wishlist successfully!!!", Toast.LENGTH_SHORT).show();	
					if(f9==0)
					{
				      DBH.addmovie(mve9,username);
				      bt9.setText("Added");
						bt9.setBackgroundColor(Color.GREEN);
						
					}}
			});
			break;
			case 10:tv10.setText(words[10]);
			bt10.setText("Add Movie");
			bt10.setBackgroundColor(Color.LTGRAY);
			final String mve10=words[10];
			bt10.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					int f10=0;
					mv_chk=DBH.addItems(username);
				      for(int i=0;i<mv_chk.length;i++)
				      {
				    	  if(mv_chk[i].equals(mve10))
				    	  {
				    		  Toast.makeText (SearchMovies.this,"Movie already in Wishlist!!", Toast.LENGTH_SHORT).show();
				    		  f10=1;
				    	  }
				      }
					
					//bt10.setEnabled(false);
					////Toast.makeText (SearchMovies.this,sb1.getText().toString()+ " added to wishlist successfully!!!", Toast.LENGTH_SHORT).show();	
					if(f10==0)
					{
				      DBH.addmovie(mve10,username);
				      bt10.setText("Added");
						bt10.setBackgroundColor(Color.GREEN);
						
					}}
			});
			break;
			default: break;
		}
		}	
			}
}
	public String ReadKNNFiles(String movie) throws IOException {
		InputStream is = this.getResources().openRawResource(R.raw.interout1);
	    BufferedReader br = new BufferedReader(new InputStreamReader(is));
	    String mv="NIL";
	    String readLine="NIL";
	    try {
	    	while ((readLine = br.readLine()) != null) {
	    	String words[] = readLine.split("\\|");
	    	if(words[0].toLowerCase().equals(movie.toLowerCase()))
	    	{
	    		
	    		Toast.makeText (SearchMovies.this,words[0], Toast.LENGTH_SHORT).show();
	    		mv=readLine;
	    		return mv;
	    	}
	    	
	    }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		return mv;
	}
	
	
public ArrayList<String> getmovies()
{
	
	InputStream is = this.getResources().openRawResource(R.raw.interout1);
    BufferedReader br = new BufferedReader(new InputStreamReader(is));
    String mv="NIL";
    String readLine="NIL";
    ArrayList<String> allmovies = new ArrayList<String>();
    try {
    	int i=0;
    	while ((readLine = br.readLine()) != null) {
    	String words[] = readLine.split("\\|");
    	allmovies.add(words[0]);
    	i++;
    	}
    }
    catch (IOException e) {
        e.printStackTrace();
    }
    return allmovies;
    	
}
}



/*package com.example.login;

import java.io.*;
import java.util.*;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class SearchMovies extends Activity{
	
	private Button bt1,bt2,bt3,bt4,bt5,bt6,bt7,bt8,bt9,bt10;
//	protected DBWishlist DB = new DBWishlist(SearchMovies.this);
	DBHelper DBH = new DBHelper(SearchMovies.this);  
	public String username;   
	ArrayAdapter<String> adapter;
	AutoCompleteTextView atv,atv1;
	private String movie;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchmovies);
        Intent intent = getIntent();
 	    username = intent.getExtras().getString("username");
 	    InputStream is = this.getResources().openRawResource(R.raw.interout1);
	    BufferedReader br = new BufferedReader(new InputStreamReader(is));
	    
 	    DBH.movieTableCreation(br);

       // String[] movieList = DBH.getAllMovies();
 	   ArrayList<String> movieList = new ArrayList<String>();
        movieList=getmovies();
        atv1 = (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView1);
        bt1=(Button) findViewById(R.id.bt1);
        bt2=(Button) findViewById(R.id.bt2);
        bt3=(Button) findViewById(R.id.bt3);
        bt4=(Button) findViewById(R.id.bt4);
        bt5=(Button) findViewById(R.id.bt5);
        bt6=(Button) findViewById(R.id.bt6);
        bt7=(Button) findViewById(R.id.bt7);
        bt8=(Button) findViewById(R.id.bt8);
        bt9=(Button) findViewById(R.id.bt9);
        bt10=(Button) findViewById(R.id.bt10);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, movieList);
        atv1.setAdapter(adapter);
		//Toast.makeText (SearchMovies.this,movieList[0], Toast.LENGTH_SHORT).show();
		//Toast.makeText (SearchMovies.this,movieList[1], Toast.LENGTH_SHORT).show();
        atv1.setOnItemClickListener( new OnItemClickListener (){
	
		@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3 ){
				// TODO Auto-generated method stub
				movie = arg0.getItemAtPosition(arg2).toString();
			{
				TextView tv1 = (TextView) findViewById(R.id.tvsb1);
				TextView tv2 = (TextView) findViewById(R.id.tvsb2);
				TextView tv3 = (TextView) findViewById(R.id.tvsb3);
				TextView tv4 = (TextView) findViewById(R.id.tvsb4);
				TextView tv5 = (TextView) findViewById(R.id.tvsb5);
				TextView tv6 = (TextView) findViewById(R.id.tvsb6);
				TextView tv7 = (TextView) findViewById(R.id.tvsb7);
				TextView tv8 = (TextView) findViewById(R.id.tvsb8);
				TextView tv9 = (TextView) findViewById(R.id.tvsb9);
				TextView tv10 = (TextView) findViewById(R.id.tvsb10);
				try 
				{
					Searchreltdmovies(tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9,tv10,movie);
				} 	catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					}
				return;
			}
		
			}
	});
}
	
	public void Searchreltdmovies(TextView tv1,TextView tv2,TextView tv3,TextView tv4,TextView tv5,TextView tv6,TextView tv7,TextView tv8,TextView tv9,TextView tv10, String movie) throws IOException
	{	
			String match=ReadKNNFiles(movie);
			if(match=="NIL")
			{
				Toast.makeText (SearchMovies.this,"Movie not found!!", Toast.LENGTH_SHORT).show();
			}
			else
			{
			String words[]=match.split("\\|");
		for(int i=1;i<=10;i++)
		{	
		switch(i)
		{
			case 1:tv1.setText(words[1]);
					bt1.setText("Add Movie");
			bt1.setBackgroundColor(Color.LTGRAY);
			final String mve1=words[1];
			bt1.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					bt1.setText("Added");
					bt1.setBackgroundColor(Color.GREEN);
				//	Toast.makeText (SearchMovies.this,"hi"+ " added to wishlist successfully!!!", Toast.LENGTH_SHORT).show();
					DBH.addmovie(mve1,username);
					
				}
			});
					break;
			case 2:tv2.setText(words[2]);
			bt2.setText("Add Movie");
			bt2.setBackgroundColor(Color.LTGRAY);
			final String mve2=words[2];
			bt2.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					bt2.setText("Added");
					bt2.setBackgroundColor(Color.GREEN);
					//Toast.makeText (SearchMovies.this,sb1.getText().toString()+ " added to wishlist successfully!!!", Toast.LENGTH_SHORT).show();	
					DBH.addmovie(mve2,username);
				}
			});
			break;
			case 3:tv3.setText(words[3]);
			bt3.setText("Add Movie");
			bt3.setBackgroundColor(Color.LTGRAY);
			final String mve3=words[3];
			bt3.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					bt3.setText("Added");
					bt3.setBackgroundColor(Color.GREEN);
					//Toast.makeText (SearchMovies.this,sb1.getText().toString()+ " added to wishlist successfully!!!", Toast.LENGTH_SHORT).show();
					DBH.addmovie(mve3,username);
				}
			});
			break;
			case 4:tv4.setText(words[4]);
			bt4.setText("Add Movie");
			bt4.setBackgroundColor(Color.LTGRAY);
			final String mve4=words[4];
			bt4.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					bt4.setText("Added");
					bt4.setBackgroundColor(Color.GREEN);
					//Toast.makeText (SearchMovies.this,sb1.getText().toString()+ " added to wishlist successfully!!!", Toast.LENGTH_SHORT).show();
					DBH.addmovie(mve4,username);
				}
			});
			break;
			case 5:tv5.setText(words[5]);
			bt5.setText("Add Movie");
			bt5.setBackgroundColor(Color.LTGRAY);
			final String mve5=words[5];
			bt5.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					bt5.setText("Added");
					bt5.setBackgroundColor(Color.GREEN);
					//Toast.makeText (SearchMovies.this,sb1.getText().toString()+ " added to wishlist successfully!!!", Toast.LENGTH_SHORT).show();
					DBH.addmovie(mve5,username);
				}
			});
			break;
			case 6:tv6.setText(words[6]);
			bt6.setText("Add Movie");
			bt6.setBackgroundColor(Color.LTGRAY);
			final String mve6=words[6];
			bt6.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					bt6.setText("Added");
					bt6.setBackgroundColor(Color.GREEN);
					//Toast.makeText (SearchMovies.this,sb1.getText().toString()+ " added to wishlist successfully!!!", Toast.LENGTH_SHORT).show();	
					DBH.addmovie(mve6,username);
				}
			});
			break;
			case 7:tv7.setText(words[7]);
			bt7.setText("Add Movie");
			bt7.setBackgroundColor(Color.LTGRAY);
			final String mve7=words[7];
			bt7.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					bt7.setText("Added");
					bt7.setBackgroundColor(Color.GREEN);
					//Toast.makeText (SearchMovies.this,sb1.getText().toString()+ " added to wishlist successfully!!!", Toast.LENGTH_SHORT).show();
					DBH.addmovie(mve7,username);
				}
			});
			break;
			case 8:tv8.setText(words[8]);
			final String mve8=words[8];
			bt8.setText("Add Movie");
			bt8.setBackgroundColor(Color.LTGRAY);
			bt8.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					bt8.setText("Added");
					bt8.setBackgroundColor(Color.GREEN);
					//Toast.makeText (SearchMovies.this,sb1.getText().toString()+ " added to wishlist successfully!!!", Toast.LENGTH_SHORT).show();	
					DBH.addmovie(mve8,username);
				}
			});
			break;
			case 9:tv9.setText(words[9]);
			bt9.setText("Add Movie");
			bt9.setBackgroundColor(Color.LTGRAY);
			final String mve9=words[9];
			bt9.setOnClickListener(new View.OnClickListener() {	
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					bt9.setText("Added");
					bt9.setBackgroundColor(Color.GREEN);
					////Toast.makeText (SearchMovies.this,sb1.getText().toString()+ " added to wishlist successfully!!!", Toast.LENGTH_SHORT).show();	
					DBH.addmovie(mve9,username);
				}
			});
			break;
			case 10:tv10.setText(words[10]);
			bt10.setText("Add Movie");
			bt10.setBackgroundColor(Color.LTGRAY);
			final String mve10=words[10];
			bt10.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					bt10.setText("Added");
					bt10.setBackgroundColor(Color.GREEN);
					////Toast.makeText (SearchMovies.this,sb1.getText().toString()+ " added to wishlist successfully!!!", Toast.LENGTH_SHORT).show();	
					DBH.addmovie(mve10,username);
				}
			});
			break;
			default: break;
		}
		}	
			}
}
	public String ReadKNNFiles(String movie) throws IOException {
		InputStream is = this.getResources().openRawResource(R.raw.interout1);
	    BufferedReader br = new BufferedReader(new InputStreamReader(is));
	    String mv="NIL";
	    String readLine="NIL";
	    try {
	    	while ((readLine = br.readLine()) != null) {
	    	String words[] = readLine.split("\\|");
	    	if(words[0].toLowerCase().equals(movie.toLowerCase()))
	    	{
	    		
	    		Toast.makeText (SearchMovies.this,words[0], Toast.LENGTH_SHORT).show();
	    		mv=readLine;
	    		return mv;
	    	}
	    	
	    }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		return mv;
	}
	
	
public ArrayList<String> getmovies()
{
	
	InputStream is = this.getResources().openRawResource(R.raw.interout1);
    BufferedReader br = new BufferedReader(new InputStreamReader(is));
    String mv="NIL";
    String readLine="NIL";
    ArrayList<String> allmovies = new ArrayList<String>();
    try {
    	int i=0;
    	while ((readLine = br.readLine()) != null) {
    	String words[] = readLine.split("\\|");
    	allmovies.add(words[0]);
    	i++;
    	}
    }
    catch (IOException e) {
        e.printStackTrace();
    }
    return allmovies;
    	
}
}*/