package com.example.login;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
public class RegistrationDB extends Activity implements OnClickListener
{
 // Variable Declaration should be in onCreate()
 private Button mSubmit;
 private Button mCancel;
 private EditText mPassword;
 private EditText mEmail;
 private EditText mConpswd;
 protected DBHelper DB = new DBHelper(RegistrationDB.this); 
 @Override
 protected void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_register);
  mSubmit = (Button)findViewById(R.id.registera);
  mSubmit.setOnClickListener(this);
  mCancel = (Button)findViewById(R.id.backa);
  mCancel.setOnClickListener(this);
  mPassword = (EditText)findViewById(R.id.pswda);
  mEmail = (EditText)findViewById(R.id.emaila);
  mConpswd=(EditText)findViewById(R.id.conpswda);
 }
 public void onClick(View v) 
 {
  switch(v.getId()){
  case R.id.backa:
   Intent i = new Intent(getBaseContext(), LoginDB.class);
   startActivity(i);
   break;
  case R.id.registera:
   String pass = mPassword.getText().toString();
   String email = mEmail.getText().toString();
   String conpos=mConpswd.getText().toString();
   boolean invalid = false;
    if(pass.equals(""))
     {
      invalid = true;
      Toast.makeText(getApplicationContext(), "Please enter your Password", Toast.LENGTH_SHORT).show();
   }
     else 
      if(email.equals(""))
   {
    invalid = true;
    Toast.makeText(getApplicationContext(), "Please enter your Email ID", Toast.LENGTH_SHORT).show();
   }
      else if(!(pass.equals(conpos)))
      {
    	  invalid = true;
    	  Toast.makeText(getApplicationContext(), "Password MisMatch!!!", Toast.LENGTH_SHORT).show();
      }
     else
      if(invalid == false)
      {
       addEntry(pass, email);
       Intent i_register = new Intent(RegistrationDB.this, LoginDB.class);
       startActivity(i_register);
       //finish();
      }
      break;
      }
    }
 public void onDestroy()
 {
  super.onDestroy();
  DB.close();
 }
 private void addEntry( String pass, String email) 
 {
  SQLiteDatabase db = DB.getWritableDatabase();
  ContentValues values = new ContentValues();
  values.put("password", pass);
  values.put("email", email);
  try
  {
   db.insert(DBHelper.DATABASE_TABLE_NAME, null, values);
   Toast.makeText(getApplicationContext(), "your details submitted Successfully...", Toast.LENGTH_SHORT).show();
  }
  catch(Exception e)
  {
   e.printStackTrace();
  }
 }
} 