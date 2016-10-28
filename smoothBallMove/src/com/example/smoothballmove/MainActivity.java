package com.example.smoothballmove;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {

	SensorManager senMan;
	Sensor gravity;
	Display display;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
		                                WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		
    }

    @Override
    protected void onResume() {
    	// TODO Auto-generated method stub
    	super.onResume();

    	senMan = (SensorManager)getSystemService(SENSOR_SERVICE);
        
        gravity = senMan.getDefaultSensor(Sensor.TYPE_GRAVITY);
    
       
        display = new Display(this, senMan, gravity);
        setContentView(display);
        
    	
    }
    
    @Override
    protected void onDestroy() {
    	// TODO Auto-generated method stub
    	
    	senMan = null;
    	gravity = null;
    	display = null;
    	
    	super.onDestroy();
    }
}
