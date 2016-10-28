package com.example.boggler;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {

	Radar radar;
	SensorManager senMan;
	Sensor prox;
	Sensor light;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
		                                WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		senMan = (SensorManager)getSystemService(SENSOR_SERVICE);
		prox = senMan.getDefaultSensor(Sensor.TYPE_PROXIMITY);
		light = senMan.getDefaultSensor(Sensor.TYPE_LIGHT);
		
		radar = new Radar(this, senMan, prox, light);
		setContentView(radar);
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		
		senMan = null;
		prox = null;
		
		radar = null;
	}
}
