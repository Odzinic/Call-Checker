package com.example.callfinder;

import java.util.ArrayList;



import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;

public class FindCalls extends Popup {

	// Buttons
	Button b;
	LinearLayout layout;
	LayoutParams lp;

	// Used for searching call log
	Cursor c;
	Uri allCalls = Uri.parse("content://call_log/calls");
	String num, name;
	String sOrder = android.provider.CallLog.Calls.DATE + " DESC";
	int type;

	// Array to prevent duplicate entries
	ArrayList arrlst = new ArrayList<Integer>();

	String sWebsite, sCountry;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_find_calls);
		checkLogs();

	}

	// ----------------------------------------------
	// 					Menu
	// ----------------------------------------------
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.prefs_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case (R.id.menuRefresh):
			allCalls = Uri.parse("content://call_log/calls");
			checkLogs();
		}
		return false;
	}
	// ----------------------------------------------
	// ----------------------------------------------

	public void initialize(final String Number) {
		layout = (LinearLayout) findViewById(R.id.ll1);
		lp = new LayoutParams(LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);

		b = new Button(this);
		b.setText(Number);
		b.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				websitePopup(Number);

			}
		});

		layout.addView(b, lp);

	}

	// Searches through call log and returns outgoing calls
	public void checkLogs() {
		c = getContentResolver().query(allCalls, null, null, null, sOrder);

		while (c.moveToNext()) {
			num = c.getString(c.getColumnIndex(CallLog.Calls.NUMBER)); // for
																		// number
			if (num.startsWith("+1")) {
				num = num.substring(2);
			}
			name = c.getString(c.getColumnIndex(CallLog.Calls.CACHED_NAME)); // for
																				// name
			type = Integer.parseInt(c.getString(c
					.getColumnIndex(CallLog.Calls.TYPE))); // for type

			if (type == 3 && arrlst.contains(num) == false && name == null) {
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