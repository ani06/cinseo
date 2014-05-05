package com.example.login;
import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.TabHost;
import android.widget.Toast;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
@SuppressWarnings("deprecation")
public class Home extends TabActivity{
	//String Uname = getIntent().getExtras().getString("Uname");
	TabHost tabHost;
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_home);
	        
	        Intent intent = getIntent();
	        String username = intent.getExtras().getString("username");
	        String password = intent.getExtras().getString("password");
	        
			
			tabHost = getTabHost();   
			// SearchMovies tab
			Intent intentSM = new Intent().setClass(this, SearchMovies.class);
			intentSM.putExtra("username", username.toString());
			intentSM.putExtra("password", password.toString());
			TabSpec tabSpecSM = tabHost
			  .newTabSpec("SearchMovies")
			  .setIndicator("SearchMovies")
			  .setContent(intentSM);
	 
			// Wishlist tab
			Intent intentMyWL = new Intent().setClass(this, MyWishlist.class);
		//	intentMyWL.putExtra("Uname", Uname);
			intentMyWL.putExtra("username", username.toString());
			TabSpec tabSpecMyWL = tabHost
					  .newTabSpec("MyWishlist")
					  .setIndicator("MyWishlist")
					  .setContent(intentMyWL);
			 
			// SuggestTheatres tab
			Intent intentST = new Intent().setClass(this, SuggestTheatres.class);
			//intentST.putExtra("Uname", Uname);
			TabSpec tabSpecST = tabHost
					  .newTabSpec("SuggestTheatres")
					  .setIndicator("SuggestTheatres")
					  .setContent(intentST);
			 
	 
			// add all tabs 
			tabHost.addTab(tabSpecSM);
			tabHost.addTab(tabSpecMyWL);
			tabHost.addTab(tabSpecST);
	 
			//set Windows tab as default (zero based)
			tabHost.setCurrentTab(0);
			int i;
			for(i=0;i<3;i++)
			{
			//tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
			TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTextColor(Color.parseColor("#ffffff"));
        	//tabHost.getTabWidget().setDividerDrawable(R.drawable.wood_1);
			}
		}
	 public void onTabChanged(String tabId) {
			// TODO Auto-generated method stub
		    for(int i=0;i<tabHost.getTabWidget().getChildCount();i++)
			{
				tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#8A4117"));
			}

			tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundColor(Color.parseColor("#ffffff"));
		}
}