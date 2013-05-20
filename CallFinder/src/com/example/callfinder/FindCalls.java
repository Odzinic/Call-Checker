package com.example.callfinder;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;

public class FindCalls extends Activity {
	Button b;
	LinearLayout layout;
	LayoutParams lp;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_find_calls);
		initialize();
		
	}

	public void initialize() {
		layout = (LinearLayout) findViewById(R.id.ll1);
		lp = new LayoutParams(LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		
		for (int i=0; i<10; i++){
		b = new Button(this);
	
		layout.addView(b, lp);
		}
		}
	}