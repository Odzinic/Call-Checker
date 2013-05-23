package com.example.callfinder;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class Popup extends SearchClass  {
	
	
	String sCountry, sWebsite;
	ArrayAdapter<String> adapter;
	country lstCountry = new country();
	String[] arrsCountry = lstCountry.CountryList;
	String[] arrsWebsite = {"Google", "YellowPages"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		}
	
	

	private void initialize(String[] arrsList) {
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_dropdown_item, arrsList);
		
		}
	
	public String countryPopup(final String Number) {
		initialize(arrsCountry);
		new AlertDialog.Builder(this).setTitle("Select Country")
				.setAdapter(adapter, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						sCountry = arrsCountry[which];

						dialog.dismiss();
						searchYellowPages(Number, sCountry);
					}
				}).create().show();
		return sCountry;
	}
	
	public String websitePopup(final String Number) {
		initialize(arrsWebsite);
		new AlertDialog.Builder(this).setTitle("Select Website")
				.setAdapter(adapter, new DialogInterface.OnClickListener() {
					

					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						sWebsite = arrsWebsite[which];

						dialog.dismiss();
						if(sWebsite=="Google"){
							searchGoogle(Number);	
						}
						else{
						countryPopup(Number);
						}
					}
				}).create().show();
		return sWebsite;
	}



	

}
