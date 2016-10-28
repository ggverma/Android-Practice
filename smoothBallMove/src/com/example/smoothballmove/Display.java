package com.example.smoothballmove;

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
import android.util.DisplayMetrics;
import android.view.View;

@SuppressLint("ViewConstructor") public class Display extends View implements SensorEventListener{

	SensorManager senMan;
	Sensor gravity;
	
	int x, y;
	
	float acclX, acclY;
	
	int w, h;
	
	Bitmap ball;
	int ballW, ballH;
	
	Paint paint;
	
	int scopeX[], scopeY[];
	
	int newX, newY;
	
	public Display(Context context, SensorManager senMan, Sensor gravity) {
		super(context);
		// TODO Auto-generated constructor stub
		
		this.senMan = senMan;
		this.gravity = gravity;
	
		senMan.registerListener(this, gravity, SensorManager.SENSOR_DELAY_UI);
		
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
		
		scopeX = new int[21];
		scopeY = new int[21];
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		
		
		canvas.drawBitmap(ball, x, y, paint);
		
		canvas.drawLine(x + ballW/2, 0, x + ballW/2, h, paint);
		canvas.drawLine(0, y + ballH/2, w, y + ballH/2, paint);
		
		canvas.drawText("Old: " + x + " " + y, 10, 100, paint);
		canvas.drawText("New: " + newX + " " + newY, 10, 200, paint);
		
	}
	
	
	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSensorChanged(SensorEvent arg0) {
		// TODO Auto-generated method stub
		switch(arg0.sensor.getType()){
		case Sensor.TYPE_GRAVITY:
			

			for(int i=-10 ; i<11 ; i++){
				scopeX[i + 10] = x + i;
				scopeY[i + 10] = y + i;
			}
			
			acclX = arg0.values[0];
			acclY = arg0.values[1];
			
			newX = (int)(w/2 + 400/2*acclY);
			newY = (int)(h/2 + 250/2*acclX);
			
			//x = w/2 + 400/2*acclY;
			//y = h/2 + 250/2*acclX;
			
			for(int j = 0 ; j <21 ; j++){
				if(scopeX[j] != newX)
					x = newX;
				if(scopeY[j] != newY)
					y = newY;
			}
			
			if(x>w)
				x=w - ballW;
			if(y>h)
				y=h  - ballH;
			if(x<0)
				x = 0;
			if(y<0)
				y = 0;
			break;
			
		
		}
		invalidate();
	}

	
	
}
