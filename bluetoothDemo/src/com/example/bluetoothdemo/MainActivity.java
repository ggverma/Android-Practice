package com.example.bluetoothdemo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

@SuppressLint("NewApi")
public class MainActivity extends Activity implements OnCheckedChangeListener, OnClickListener {

	ToggleButton tb;
	Button showB, sendFile;
	
	BluetoothAdapter myBA;
	
	TextView bStatus;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tb = (ToggleButton)findViewById(R.id.toggleButton1);
		showB = (Button)findViewById(R.id.button1);
		sendFile = (Button)findViewById(R.id.button2);
		
		tb.setOnCheckedChangeListener(this);
		
		showB.setOnClickListener(this);
		sendFile.setOnClickListener(this);
		
		bStatus = (TextView)findViewById(R.id.textView1);
		
		myBA = BluetoothAdapter.getDefaultAdapter();
		
		if(myBA == null){
			Toast.makeText(this, "No Bluetooth found", Toast.LENGTH_SHORT).show();
			finish();
		}
		
		if(myBA.isEnabled()){
			tb.setChecked(true);
		}
		

		if(!myBA.isDiscovering())
			bStatus.setText("Bluetooth Discoverable: False");
		else
			bStatus.setText("Bluetooth Discoverable: True");
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
		// TODO Auto-generated method stub
		if(arg1){
			Intent bt = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
			startActivityForResult(bt, 10);
			
		}
		else{
			myBA.disable();
		}
		
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		if(!myBA.isEnabled()){
			tb.setChecked(false);
		}
		else
			tb.setChecked(true);
		switch(requestCode){
		case 10:
			if(!myBA.isEnabled()){
				tb.setChecked(false);
				bStatus.setText("Bluetooth Discoverable: False");
			}
			break;
		case 20:
			if(myBA.isDiscovering())
				bStatus.setText("Bluetooth Discoverable: False");
			else
				bStatus.setText("Bluetooth Discoverable: True");
		}
		
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.button1:
			if(!myBA.isDiscovering()){
				Intent btDisc = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
				startActivityForResult(btDisc, 20);
			}
			break;
						
		case R.id.button2:
				Intent sendFileIn = new Intent(this,SendFile.class);
				startActivity(sendFileIn);
			break;
		}
	}

}
