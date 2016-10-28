package com.example.cardrag;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

@SuppressLint({ "ClickableViewAccessibility", "HandlerLeak" })
public class Drag1 extends View{

	Bitmap car, roadA, road;
	Paint paint;
	int h, w;
	int carWt, carHt;
//	Display display;
	int destX, destY;
	
	int mvx, mvy;
	int drawAtX, drawAtY;
	boolean moveTouch;
	
	final double TIME = 4;
	int distance;
	int i =0;
	
	boolean running;
	
	String engineStat = "";
	
	public Drag1(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		
		DisplayMetrics displayMetrics = new DisplayMetrics();
		((Activity)getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		h = displayMetrics.heightPixels;
		w = displayMetrics.widthPixels;
		
		car = BitmapFactory.decodeResource(getResources(), R.drawable.smallcar);
		carHt = car.getHeight();
		carWt = car.getWidth();
		
		roadA = BitmapFactory.decodeResource(getResources(), R.drawable.roadtop);
		road = Bitmap.createScaledBitmap(roadA, w, carHt * 2, true);
				
		paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.BLACK);
		paint.setTextSize(40);
		
		drawAtX = destX = w - carWt;
		destY = h/2 - carHt/2;
		
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		
		
		
		//canvas.drawText(carHt+" " + carWt, w/2, h/2, paint);
		canvas.drawBitmap(road, 0, h/2 - carHt/2 - 10, paint);
		
		if(moveTouch)
			canvas.drawBitmap(car, mvx, destY, paint);
		else
			canvas.drawBitmap(car, drawAtX, destY, paint);
				
		canvas.drawText("time/dist = " + TIME*1000/distance, 10, 50, paint);
		
		if(running)
			canvas.drawText("Engine running!", 10, 100, paint);
		else
			canvas.drawText("Engine Stopped", 10, 100, paint);
	}	
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		switch(event.getAction() & MotionEvent.ACTION_MASK){
		case MotionEvent.ACTION_DOWN:
			moveTouch = true;
			running = false;
			
			break;
		case MotionEvent.ACTION_MOVE:
			mvx = (int)event.getX();
			mvy = (int)event.getY();
			
			
			break;
		case MotionEvent.ACTION_UP:
			moveTouch = false;
			
			distance = Math.abs(mvx - destX);
			running = true;
			Engine engine = new Engine();
			engine.start();			
			break;
		
		}
		invalidate();
		return true;
	}
	

	
	class Engine extends Thread{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			while(running){
			drawAtX = mvx;
			while(drawAtX <= destX){
				try{
					Thread.sleep((long)TIME*1000/distance);
					if(moveTouch)
						break;
				}
				catch(Exception e){
					//e.printStackTrace();
				}
				h1.sendMessage(h1.obtainMessage());
				drawAtX++;
			
			}
			break;
		}
		}
		
	Handler h1 = new Handler(){
		public void handleMessage(android.os.Message msg) {
			invalidate();
		};
	};
	}
}
