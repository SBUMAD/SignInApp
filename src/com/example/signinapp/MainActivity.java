package com.example.signinapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.*;

import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

public class MainActivity extends Activity 
{

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final String APP_ID = "fwCJrTBQj9GL2AHrAbnoYTsGEo9fI48wbOgANckq";
		final String CLIENT_ID = "BrRub57sBqofif1xqDI2AXgkkLyxMMSyuumYzjQf";
		
		Parse.initialize(this, APP_ID, CLIENT_ID);
		ParseAnalytics.trackAppOpened(getIntent());
		
		Button button = (Button) findViewById(R.id.loginButton);
		button.setOnClickListener(new View.OnClickListener() 
		{
			
			@Override
			public void onClick(View v) 
			{				
				// get the EditText object, and get a string from the input entered
				EditText et = (EditText) findViewById(R.id.loginName);
				String name = et.getText().toString();
				
				ParseObject pigeon = new ParseObject("MemberSignIn");
				pigeon.put("name", name);
				pigeon.saveInBackground(new SaveCallback(){

					@Override
					public void done(ParseException e) 
					{
						//get the components again (scoping)
						TextView tv = (TextView) findViewById(R.id.textView1);
						//EditText et = (EditText) findViewById(R.id.loginName);
						
						if(e == null)	// if no exception returned
						{
							tv.setText("Ta-da~~");
							//clear textfield
							//et.setText("");
						}
						else	// in case of exception
						{
							tv.setText("...but it was not effective!");
						}
					}
					
				});
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
