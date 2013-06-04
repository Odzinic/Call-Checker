package com.example.callfinder;

import java.util.ArrayList;


import android.content.CursorLoader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class FindTexts extends Popup {

	// Buttons
	Button b;
	LinearLayout layout;
	LayoutParams lp;
	TextView tvTitle;

	// Used for searching call log
	Uri allTexts = Uri.parse("content://sms/inbox");
	Cursor c;
	CursorLoader clC;
	String num, name, sOrder;
	String sSort = "date DESC";
	String[] columns = new String[] { "address", "person", "date", "body",
			"type" };
	int type;

	// Array to prevent duplicate entries
	ArrayList<String> arrlst = new ArrayList<String>();
	ArrayList<String> arrBlklst = new ArrayList<String>();

	String sWebsite, sCountry;
	int nChoice;

	// SimpleCursorAdapter mAdapter;
	// LoaderManager.LoaderCallbacks<Cursor> mCallbacks;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_find_calls);
		initialize();
		checkTexts();

	}

	/* Creates menu */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.prefs_menu, menu);
		return true;
	}

	/* Initializes variables */
	public void initialize() {
		layout = (LinearLayout) findViewById(R.id.ll1);
		tvTitle = (TextView) findViewById(R.id.textView1);
		lp = new LayoutParams(LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		// sOrder = String.format(sDate + " %s", sSort);

		// mAdapter = new SimpleCursorAdapter(this,
		// R.layout.activity_find_calls,
		// null, null, null, 0);
		// getLoaderManager().initLoader(0, null, this);
		//
		// }
		//
		// @Override
		// public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
		// return new CursorLoader(this, allCalls,
		// null, null, null, sOrder);
		// }
		//
		// @Override
		// public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		// switch (loader.getId()) {
		// case 1:
		// mAdapter.swapCursor(cursor);
		// break;
		// }
		//
		// }
		//
		// @Override
		// public void onLoaderReset(Loader<Cursor> arg0) {
		// mAdapter.swapCursor(null);
		//
	}

	// ----------------------------------------------
	// ----------------------------------------------

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case (R.id.menuRefresh):
			allTexts = Uri.parse("content://call_log/calls");
			layout.removeAllViews();
			//layout.addView(tvTitle, lp);
			arrlst.clear();
			checkTexts();
			Toast.makeText(this, "Refreshed", Toast.LENGTH_SHORT).show();
			break;

		case (R.id.menuSort):
			sSort = sortPopup();
			layout.removeAllViews();
			//layout.addView(tvTitle, lp);
			arrlst.clear();
			checkTexts();
			break;

		}
		return false;
	}

	public void makeButton(final String Number) {
		b = new Button(this);
		b.setText(Number);

		b.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				websitePopup(Number);
			}
		});
		// b.setOnLongClickListener(new View.OnLongClickListener() {
		//
		// @Override
		// public boolean onLongClick(View v) {
		// nChoice = longpressPopup();
		//
		// v.setVisibility(View.GONE); //Find how to wait for input
		//
		//
		// return false;
		// }
		// });

		layout.addView(b, lp);
	}

	/* Searches through call log and returns outgoing calls */
	public void checkTexts() {
		// c = mAdapter.getCursor();
		c = getContentResolver().query(allTexts,new String[] { "_id", "thread_id", "address", "person", "date","body", "type" }, null, null, null);

		while (c.moveToNext()) {
			num = c.getString(c.getColumnIndex(columns[0])); // for
																// number
			if (num.startsWith("+1")) {
				num = num.substring(2);
			}
			name = c.getString(c.getColumnIndex(columns[1])); // for
																// name
			type = Integer.parseInt(c.getString(c.getColumnIndex(columns[4]))); // for
																				// type

			if (type == 1 && arrlst.contains(num) == false && name == null) {
				arrlst.add(num);
				makeButton(num);
			}

			else {
				arrlst.add(num);
				continue;
			}
		}

	}

}
