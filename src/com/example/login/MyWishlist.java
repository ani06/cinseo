package com.example.login;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import android.widget.Toast;
import android.os.Bundle;  
import android.app.ListActivity;  
import android.content.Intent;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.ArrayAdapter;  
import android.widget.AdapterView.AdapterContextMenuInfo;
 public class MyWishlist extends ListActivity {  
	 
	  private String[] items;
	  private List<String> list;
	  private  ArrayAdapter<String> adapter;
	  DBHelper DBH = new DBHelper(MyWishlist.this);
	    public String username; 
	   MyWishlist MyWishlist(){
	    	return MyWishlist.this;
	    }
	  private int position;
      @Override  
      protected void onCreate(Bundle savedInstanceState) {  
           super.onCreate(savedInstanceState);  
           setContentView(R.layout.activity_wishlist);  
           fillData();  
           registerForContextMenu(getListView());
      }  
      private void fillData() {  
           //items = new String[] {"Monday", "Tuesday", "Wednesday",   
            //                "Thursday", "Friday", "Saturday", "Sunday"};
            
           Intent intent = getIntent();
           username = intent.getExtras().getString("username");
           items = DBH.addItems(username);
           list = new ArrayList<String>();
           Collections.addAll(list, items);
           adapter = new ArrayAdapter<String>(this, R.layout.row, R.id.r_text, list);  
           setListAdapter(adapter);            
      }  
      
      @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
    		ContextMenuInfo menuInfo) {
    	// TODO Auto-generated method stub
    	super.onCreateContextMenu(menu, v, menuInfo);
    	MenuInflater m = getMenuInflater();
    	m.inflate(R.menu.our_context_menu, menu);
    }
      public void onResume(){
      	super.onResume();
      	fillData();
      }

     @Override
    public boolean onContextItemSelected(MenuItem item) {
    	switch(item.getItemId()){
    		case R.id.delete_item:
    			AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
    			position = (int) info.id;
    			String mv=list.get(position);
    			Toast.makeText (MyWishlist.this,mv+" removed", Toast.LENGTH_SHORT).show();
    			DBH.removemovies(username,mv);
    			list.remove(position);
    			this.adapter.notifyDataSetChanged();
    			return true;
    	}
    	return super.onContextItemSelected(item);
    }
 }  