package com.example.shakedemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

	Button startSer, stopSer;
	Intent in;
	
	TextView serviceStat;
	Serve service;
	boolean running = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		startSer = (Button)findViewById(R.id.button1);
		stopSer = (Button)findViewById(R.id.button2);
		
		startSer.setOnClickListener(this);
		stopSer.setOnClickListener(this);
		
		serviceStat = (TextView)findViewById(R.id.textView1);

		service = new Serve();
		in = new Intent(this, Serve.class);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.button1:
			
			running = true;
			in.putExtra("Running", true);
			startService(in);
			
			break;

		case R.id.button2:
			
			in.putExtra("Running", false);
			stopService(in);
			running = false;
			break;
		}
		
		if(running)
			serviceStat.setText("Service Running");
		else
			serviceStat.setText("Service Stopped");
	}
}
