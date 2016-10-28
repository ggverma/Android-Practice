package com.example.ballmove;

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
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

public class Display extends View implements SensorEventListener{

	SensorManager senMan;
	Sensor accl;
	
	float x, y;
	
	float acclX, acclY;
	
	int w, h;
	
	Bitmap ball;
	float ballW, ballH;
	
	Paint paint;
	
	public Display(Context context, SensorManager senMan, Sensor accl) {
		super(context);
		// TODO Auto-generated constructor stub
		
		this.senMan = senMan;
		this.accl = accl;
	
		senMan.registerListener(this, accl, SensorManager.SENSOR_DELAY_UI);
		
		DisplayMetrics displayMetrics = new DisplayMetrics();
		((Activity)getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		h = displayMetrics.heightPixels;
		w = displayMetrics.widthPixels;
		
		x = w/2;
		y = h/2;
		
		paint = new Paint();
		paint.setAntiAlias(true);
		paint.setTextSize(70);
		paint.setColor(Color.BLACK);
		
		ball = BitmapFactory.decodeResource(getResources(), R.drawable.blueball);
		
		ball = Bitmap.createScaledBitmap(ball, 100, 100, true);
		
		ballW = ball.getWidth();
		ballH = ball.getHeight();
		
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		
		canvas.drawBitmap(ball, x, y, paint);
		
	}
	
	
	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSensorChanged(SensorEvent arg0) {
		// TODO Auto-generated method stub
		switch(arg0.sensor.getType()){
		case Sensor.TYPE_ACCELEROMETER:
			
			
			acclX = arg0.values[0];
			acclY = arg0.values[1];
			
			x = w/2 + 400/2*acclY;
			y = h/2 + 250/2*acclX;
				
			if(x>w)
				x=w - ballW;
			if(y>h)
				y=h  - ballH;
			if(x<0)
				x = 0;
			if(y<0)
				y = 0;
			invalidate();
			
		}
	}

	
	
}
