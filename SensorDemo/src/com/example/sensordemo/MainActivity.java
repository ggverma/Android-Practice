package com.example.sensordemo;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener {

	TextView tvX, tvY, tvZ, tvLight, tvProx;
	SensorManager sensorManager;
	Sensor accl;
	Sensor prox;
	Sensor light;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        tvX = (TextView)findViewById(R.id.textView1);
        tvY = (TextView)findViewById(R.id.textView2);
        tvZ = (TextView)findViewById(R.id.textView3);
        tvLight = (TextView)findViewById(R.id.textView4);
        tvProx = (TextView)findViewById(R.id.textView5);
        
       }
    
    @Override
    protected void onResume() {
    	// TODO Auto-generated method stub
    	super.onResume();
    	 sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
         
         accl = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
         prox = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
         light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
         
         
    	sensorManager.registerListener(this, accl, SensorManager.SENSOR_DELAY_UI);
    	sensorManager.registerListener(this, prox, SensorManager.SENSOR_DELAY_UI);
    	sensorManager.registerListener(this, light, SensorManager.SENSOR_DELAY_UI);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

	@Override
	public void onAccuracyChanged(Sensor arg0, int accuracy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		switch(event.sensor.getType()){
		case Sensor.TYPE_ACCELEROMETER:
			tvX.setText("" + event.values[0]);
			tvY.setText("" + event.values[1]);
			tvZ.setText("" + event.values[2]);
			break;
		case Sensor.TYPE_LIGHT:
			tvLight.setText("Light: " + event.values[0]);
			break;
		case Sensor.TYPE_PROXIMITY:
			
			tvProx.setText("Proximity: " + event.values[0]);
		}
	}
    
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		sensorManager = null;
		accl = null;
		super.onDestroy();
		
		
	}
}
