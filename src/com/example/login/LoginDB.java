package com.example.login;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class LoginDB extends Activity implements OnClickListener 
{
 View mLogin;
 View mRegister;
 View mExit;
 EditText memail;
 EditText mpassword;
 DBHelper DB = null;
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mRegister = (Button)findViewById(R.id.register);
        mRegister.setOnClickListener(this);

        mLogin = (Button)findViewById(R.id.login);
        mLogin.setOnClickListener(this);

        mExit = (Button)findViewById(R.id.exit);
        mExit.setOnClickListener(this);
    }
 public void onClick(View v) 
 {
 switch(v.getId())
 {
 case R.id.register:
  Intent i = new Intent(getBaseContext(), RegistrationDB.class);
  startActivity(i);
  break;
 case R.id.exit:
  finish();
  break;
 case R.id.login:
 { 
  memail = (EditText)findViewById(R.id.email);
  mpassword = (EditText)findViewById(R.id.pswd);

  String email = memail.getText().toString();
  String password = mpassword.getText().toString();

  if(email.equals("") || email == null)
  {
   Toast.makeText(getApplicationContext(), "Please enter User Name", Toast.LENGTH_SHORT).show();
  }
  else if(password.equals("") || password == null)
  {
   Toast.makeText(getApplicationContext(), "Please enter your Password", Toast.LENGTH_SHORT).show();
  }
  else
  {
   boolean validLogin = validateLogin(email, password, getBaseContext());
   if(validLogin)
   {
    //System.out.println("In Valid");
    //AV Intent in = new Intent(getBaseContext(), Home.class);
	Intent in = new Intent (this, Home.class);
	in.putExtra("username",email.toString());
	in.putExtra("password",password.toString());
	   //in.putExtra("Uname", memail.getText().toString());
    startActivity(in);
    //finish();
   }
  }
  break;
 }
 }
 }
 private boolean validateLogin(String email , String password, Context baseContext) 
 {
  DB = new DBHelper(getBaseContext());
  SQLiteDatabase db = DB.getReadableDatabase();
  String[] columns = {"_id"};
  String selection = "email=? AND password=?";
  String[] selectionArgs = {email,password};
  Cursor cursor = null;
  try{
  cursor = db.query(DBHelper.DATABASE_TABLE_NAME, columns, selection, selectionArgs, null, null, null);
  startManagingCursor(cursor);
  }
  catch(Exception e)
  {
   e.printStackTrace();
  }
int numberOfRows = cursor.getCount();
  if(numberOfRows <= 0)
  {
   Toast.makeText(getApplicationContext(), "User Name and Password miss match..\nPlease Try Again", Toast.LENGTH_LONG).show();
Intent intent = new Intent(getBaseContext(), LoginDB.class);
   startActivity(intent);
  return false;
  }
  else
  {
 // Intent home_in=new Intent(getBaseContext(), Home.class);
//startActivity(home_in);
  return true;
  }
 }
 public void onDestroy()
 {
  super.onDestroy();
 // DB.close();
 }
}