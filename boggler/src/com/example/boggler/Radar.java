package com.example.boggler;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

public class Radar extends View implements SensorEventListener{

	int h, w;
	Paint paint;
	
	int Rad = 400;
	
	int x, y;
	
	SensorManager senMan;
	Sensor prox;
	Sensor light;
	
	double lightVal;
	boolean lightSensor = false;
	
	double angleC = 360;
	double angleAC = 0;
	
	double angleCInc = -0.01;
	double angleACInc = 0.01;
	
	boolean outerClockwise = true;
	
	Bitmap switchOn, switchOff;
	
	public Radar(Context context, SensorManager sM, Sensor proxSensor, Sensor lightSensor) {
		super(context);
		// TODO Auto-generated constructor stub
		
		DisplayMetrics displayMetrics = new DisplayMetrics();
		((Activity)getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		h = displayMetrics.heightPixels;
		w = displayMetrics.widthPixels;
		
		paint = new Paint();
		paint.setColor(Color.BLACK);
		paint.setAntiAlias(true);
		paint.setTextSize(50);
		
		senMan = sM;
		prox = proxSensor;
		light = lightSensor;
		
		senMan.registerListener(this, prox, SensorManager.SENSOR_DELAY_UI);
		senMan.registerListener(this, light, SensorManager.SENSOR_DELAY_UI);
		
		switchOn = BitmapFactory.decodeResource(getResources(), R.drawable.switchon);
		switchOff = BitmapFactory.decodeResource(getResources(), R.drawable.switchoff);
		
		switchOn = Bitmap.createScaledBitmap(switchOn, 200, 200, true);
		switchOff = Bitmap.createScaledBitmap(switchOff, 200, 200, true);
		
		Timer timer = new Timer();
		timer.execute();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		
		paint.setAlpha(255);
		paint.setColor(Color.BLACK);
		canvas.drawText("Light Sensor: ", w-500, h - 150, paint);
		
		//canvas.drawText("", w-600, h - 100, paint);
		
		if(!lightSensor)
			canvas.drawBitmap(switchOff, w-250, h-250, paint);
		else
			canvas.drawBitmap(switchOn, w-250, h-250, paint);
		
		
		canvas.drawCircle(w/2, h/2, 400 , paint);
		paint.setColor(Color.WHITE);
		
		if(lightSensor)
			paint.setAlpha((int)lightVal);
		else
			paint.setAlpha(255);
		
		for(double i = -1 ; i < 5.2  ; i+= 0.1)
			canvas.drawLine( w/2 + (float)(300*Math.sin(angleC + i)), h/2 + (float)(300*Math.cos(angleC + i)), w/2 + (float)(Rad*Math.sin(angleC + i)), h/2 + (float)(Rad*Math.cos(angleC + i)), paint);
		
		for(double i = -1 ; i < 5.2  ; i+= 0.1)
			canvas.drawLine( w/2 + (float)(200*Math.sin(angleAC + i)), h/2 + (float)(200*Math.cos(angleAC + i)), w/2 + (float)(300*Math.sin(angleAC + i)), h/2 + (float)(300*Math.cos(angleAC + i)), paint);
		
		for(double i = -1 ; i < 5.2  ; i+= 0.1)
			canvas.drawLine( w/2 + (float)(100*Math.sin(angleC + i)), h/2 + (float)(100*Math.cos(angleC + i)), w/2 + (float)(200*Math.sin(angleC + i)), h/2 + (float)(200*Math.cos(angleC + i)), paint);

		for(double i = -1 ; i < 5.2  ; i+= 0.1)
			canvas.drawLine( w/2 , h/2, w/2 + (float)(100*Math.sin(angleAC + i)), h/2 + (float)(100*Math.cos(angleAC + i)), paint);
		
		}		

	@SuppressLint({ "NewApi", "ClickableViewAccessibility" })
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		switch (event.getAction() & MotionEvent.ACTION_MASK) {
		case MotionEvent.ACTION_UP:
			x = (int)event.getX();
			y = (int)event.getY();
			if(x < (w-50) && x > (w-250) && y < (h-50) && y > (h-250)){
				if(lightSensor)
					lightSensor = false;
				else
					lightSensor = true;
			}
				
			break;
		}
		
		return true;
	}
	
	class Timer extends AsyncTask<String, Integer, Boolean>{

		@Override
		protected Boolean doInBackground(String... params) {
			// TODO Auto-generated method stub
			
			if(outerClockwise){
			while(angleC > 0){
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				publishProgress();
			}
			}
			else{
				while(angleAC > 0){
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					publishProgress();
				}
				
			}
			return null;
		}
	
		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);

			if(outerClockwise){
				if(angleC == 1)
					angleC = 360;
				if(angleAC == 359)
					angleAC = 0;
			}
			else{
				if(angleAC == 1)
					angleAC = 360;
				if(angleC == 359)
					angleC = 0;
				
			}
			angleC += angleCInc;
			angleAC += angleACInc; 
			invalidate();
			
		}
		
	}


	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSensorChanged(SensorEvent arg0) {
		// TODO Auto-generated method stub
		switch(arg0.sensor.getType()){
		case Sensor.TYPE_PROXIMITY:
			if(arg0.values[0] == 0){
				double temp;
				temp = angleC;
				angleC = angleAC;
				angleAC = temp;
				
				double tempInc;
				tempInc = angleACInc;
				angleACInc = angleCInc;
				angleCInc = tempInc;
			}
			
			if(angleACInc == 0.01)
				outerClockwise = true;
			else
				outerClockwise = false;
			
			break;
		
		case Sensor.TYPE_LIGHT:
			if(lightSensor){
				lightVal = arg0.values[0]*10;
				if(lightVal>255)
					lightVal = 255;
			}
		}
	}
	
	
} 
