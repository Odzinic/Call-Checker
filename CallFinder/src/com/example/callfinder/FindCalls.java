package com.example.callfinder;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;

public class FindCalls extends Activity {
	
	//Buttons
	Button b;
	LinearLayout layout;
	LayoutParams lp;
	
	//Used for searching call log
	Cursor c;
	Uri allCalls = Uri.parse("content://call_log/calls");
	String num, name;
	int type;
	
	//Array to prevent duplicate entries
	ArrayList arrlst = new ArrayList<Integer>();
	
	String[] items;
	ArrayAdapter<String> adapter;
	String sCountry;
	country lstCountry;
	


	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_find_calls);
		checkLogs();

	}

	public void initialize(final String Number) {
		layout = (LinearLayout) findViewById(R.id.ll1);
		lp = new LayoutParams(LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		lstCountry = new country();
		
		items = lstCountry.CountryList;
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_dropdown_item, items);
		
		

		b = new Button(this);
		b.setText(Number);
		b.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				countryPopup();
//				Intent myWebLink = new Intent(android.content.Intent.ACTION_VIEW);
//				String sUrl = String.format("https://www.google.ca/search?q=%s", Number);
//	             myWebLink.setData(Uri.parse(sUrl));
//	             startActivity(myWebLink);
				
			}
		});

		layout.addView(b, lp);

	}
	
	public void countryPopup() {	
		new AlertDialog.Builder(this).setTitle("Select Country")
				.setAdapter(adapter, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						sCountry = items[which];

						dialog.dismiss();
					}
				}).create().show();
		
	}
	

	private void checkLogs() {
		int i = 0;

		c = getContentResolver().query(allCalls, null, null, null, null);

		while (c.moveToNext()) {
			num = c.getString(c.getColumnIndex(CallLog.Calls.NUMBER)); //for number
			name = c.getString(c.getColumnIndex(CallLog.Calls.CACHED_NAME)); //for name
			type = c.getInt(CallLog.Calls.OUTGOING_TYPE); //for type
			i++;

			if (type == 0 && arrlst.contains(num) == false && name == null) {
				arrlst.add(num);
				initialize(num);
			}

			else {
				arrlst.add(num);
				continue;
			}
		}

		c.close();
	}
}