package com.example.callfinder;

import android.app.LocalActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;


@SuppressWarnings("deprecation")
public class MainMenu extends FragmentActivity  {
	
	TabHost tabHost;
	Intent inCall, inText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
		
		tabHost = (TabHost)findViewById(R.id.thMainMenu);
        
        LocalActivityManager mLocalActivityManager = new LocalActivityManager(this, false);
        mLocalActivityManager.dispatchCreate(savedInstanceState); // state will be bundle your activity state which you get in onCreate
        tabHost.setup(mLocalActivityManager);
        
        
        inCall = new Intent("com.example.callfinder.FINDCALLS");
        TabSpec specCall = tabHost.newTabSpec("calls");
        specCall.setIndicator("Calls").setContent(inCall);
        tabHost.addTab(specCall);
        
        

        inText = new Intent("com.example.callfinder.FINDTEXTS");
        TabSpec specText = tabHost.newTabSpec("text");
        specText.setIndicator("Texts").setContent(inText);
        tabHost.addTab(specText);

        tabHost.setCurrentTab(0);

	}
	

	

}
