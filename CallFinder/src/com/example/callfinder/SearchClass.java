package com.example.callfinder;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class SearchClass extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	public void searchGoogle(String nNumber){
		Intent myWebLink = new Intent(
				android.content.Intent.ACTION_VIEW);
		String sUrl = String.format(
				"https://www.google.ca/search?q=%s", nNumber);
		myWebLink.setData(Uri.parse(sUrl));
		startActivity(myWebLink);
	}
	
	public void searchYellowPages(String nNumber, String sCountry){
		Intent myWebLink = new Intent(
				android.content.Intent.ACTION_VIEW);
		String sUrl = String
				.format("http://mobile.yp.ca/person/%s/%s",
						nNumber, sCountry);
		myWebLink.setData(Uri.parse(sUrl));
		startActivity(myWebLink);
	}

}
