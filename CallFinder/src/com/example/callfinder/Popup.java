package com.example.callfinder;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class Popup extends SearchClass {

	String sCountry, sWebsite, sChoice;
	int nChoice;
	ArrayAdapter<String> adapter;
	String[] arrsCountry = country.CountryList;
	String[] arrsWebsite = { "Google", "YellowPages" };
	String[] arrsSort = { "Ascending", "Descending" };
	String[] arrsLong = { "Delete", "Never Show Again" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	private void initializePopup(String[] arrsList) {
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_dropdown_item, arrsList);

	}

	public String countryPopup(final String Number) {
		initializePopup(arrsCountry);
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
		initializePopup(arrsWebsite);
		new AlertDialog.Builder(this).setTitle("Select Website")
				.setAdapter(adapter, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {

						sWebsite = arrsWebsite[which];

						dialog.dismiss();
						if (sWebsite == "Google") {
							searchGoogle(Number);
						} else {
							countryPopup(Number);
						}
					}
				}).create().show();
		return sWebsite;
	}

	public String sortPopup() {
		initializePopup(arrsSort);
		new AlertDialog.Builder(this).setTitle("Select Website")
		.setAdapter(adapter, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				if (arrsSort[which] == "Ascending") {
					sChoice = "ASC";
				} else {
					sChoice = "DESC";
				}

			}
		}).create().show();

		return sChoice;
	}

	public int longpressPopup() {
		initializePopup(arrsLong);
		new AlertDialog.Builder(this).setTitle("Select Option")
				.setAdapter(adapter, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						nChoice = which;
						dialog.dismiss();

					}

				}).create().show();
		return nChoice;
	}

}
