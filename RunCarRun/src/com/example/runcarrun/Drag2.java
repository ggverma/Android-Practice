package com.example.runcarrun;

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
import android.view.View;

class Drag2  extends View{

	Display display;
	int w, h;
	Paint paint;
	Bitmap car1, car2;
	int carW, carH;
	double car1X = 10, car2X = 10;
	
	double distance;
	
	String er="";
	
	double finishLine;
	
	double car1Time, car2Time, winCarTime;
	
	CountDown cd;
	
	long cdCount = 3;
	
	boolean cdRunning = false;	
	
	Race race;
	
	String winner="";
	
	boolean raceIsOn = false;
	
	//String engineCond = "";
	
	double raceTime ;
	
	String carStat = "";
	
	double acc1, acc2;
	
	boolean background = false;
	
	BackThread bt;

	
	
	float backX1 = 0, backY1 = 0, backX2 = 0, backY2 = 0;
	
	String p1Name = "", p2Name="";
	
	public Drag2(Context context, String s1, String s2) {
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
		
		car1 = BitmapFactory.decodeResource(getResources(), R.drawable.smallcar);
		car2 = BitmapFactory.decodeResource(getResources(), R.drawable.smallcar);
		
		p1Name = s1;
		p2Name = s2;
		
		carW = car1.getWidth();
		carH = car1.getHeight();
		
		carTimeGenerator();
		
		finishLine = w - carW - 10; 
				
		raceTime = 0;
		
		distance = w - carW;
		
		backX1 = w;
		backY1 = 0;
		
		backX2 = w;
		backY2 = h;
		
		cdRunning = true;
		

		cd = new CountDown();
		cd.start();
		
	}
	
	public void carTimeGenerator(){
		car1Time = (long)(Math.random()*2500 + 2500);
		car2Time = (long)(Math.random()*2500 + 2500);
		
		
		if(car1Time==car2Time)
			carTimeGenerator();
		
		if(Math.abs(car1Time-car2Time) < 500)
			carTimeGenerator();
		
		acc1 = Math.random();
		acc2 = Math.random();
		
		if(car1Time < car2Time){	
			winCarTime = car1Time;
	}
		else{
			winCarTime = car2Time;
		}
	
	
		
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		
		if(!cdRunning && raceIsOn){
			
			for(int i=-2 ; i<3 ; i++)
				canvas.drawLine(0, h/2 + i, w, h/2 + i, paint);
			
			for(int i = -2 ; i < 3 ; i++)
				canvas.drawLine(w/8 + i, 0, w/8 + i, h, paint);
			
			for(int i = -2 ; i < 3 ; i++)
				canvas.drawLine(w + i - carW - 10, 0, w + i - carW - 10, h, paint);
			
			//canvas.drawText(car1Time+" "+car2Time, 10, 100, paint);
			paint.setTextSize(100);
			paint.setAlpha(100);
			
			canvas.drawText(p1Name, w/4, h/4, paint);
			canvas.drawText(p2Name, w/4, 3*h/4, paint);
			
			
			paint.setAlpha(255);
					
			canvas.drawBitmap(car1, (int)car1X, h/4, paint);
			canvas.drawBitmap(car2, (int)car2X, 3*h/4, paint);
			
		}
		else if(cdRunning){
			canvas.drawCircle(w/2, h/2, 200, paint);
			paint.setColor(Color.WHITE);
			
			canvas.drawCircle(w/2, h/2, 190, paint);
			paint.setColor(Color.BLACK);
			
			paint.setTextSize(120);
			canvas.drawText(cdCount + "", w/2 - 20, h/2 + 60, paint);
			
			
		}
		else if(!raceIsOn){
			if(background){
				//canvas.drawText("background" + backX1 +" " + backX2, 10, 100, paint);
				//canvas.drawText(background+"", 10, 200, paint);
				paint.setColor(Color.RED);
				for(int i = -100, j = -200 ; i<101 ; i++, j+=2){
					if(j<0)
						paint.setAlpha(255 + j);
					else
						paint.setAlpha(255 - j);
					canvas.drawLine(backX1 + i, backY1, backX2 + i, backY2, paint);
				}
			}
			else
				paint.setAlpha(255);
				paint.setColor(Color.BLACK);
				canvas.drawText(winner + " wins!!!", w/2 - 100, h/2 +60, paint);
			
		}
	}
		
	class CountDown extends Thread{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			while(cdRunning){
				try{
					Thread.sleep(1000);
					cdCount--;
					
				}
				catch(Exception e){}
				h1.sendMessage(h1.obtainMessage());
				if(cdCount == 0){
					raceIsOn = true;
										
					race = new Race();
					race.start();
					cdRunning = false;
				}
			}
			
		}
		Handler h1 = new Handler(){
			public void handleMessage(android.os.Message msg) {
				invalidate();
				
			};
		};
	}
	
	class Race  extends Thread{
		
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			while(raceIsOn){
				
				while(raceTime <= winCarTime){
					try{
						Thread.sleep((long)(winCarTime/distance));
						
					}
					catch(Exception e){
						
					}
					h2.sendMessage(h2.obtainMessage());
					raceTime += 1;
				}
			}
		}
	}
		Handler h2 = new Handler(){
			public void handleMessage(android.os.Message msg) {
				if(raceTime <= winCarTime/3){
					car1X += (double)distance/car1Time;
					car2X += (double)distance/car2Time;
					}
				else if(raceTime <= winCarTime){
					car1X += (double)distance/car1Time + acc1;
					car2X += (double)distance/car2Time + acc2;
				}
				
				if(car1X >= finishLine){
					winner = p1Name;
					carStat = "car1";
					raceIsOn = false;
					raceTime = winCarTime;
					
					background = true;
					bt = new BackThread();
					bt.start();
				}
				else if(car2X >= finishLine){
					winner = p2Name;
					carStat = "car2";
					raceTime = winCarTime;
					raceIsOn = false;

					background = true;
					bt = new BackThread();
					bt.start();
				}
				
				invalidate();
			};
		};
	
		class BackThread extends Thread{
			@Override
			public void run() {
				// TODO Auto-generated method stub
				super.run();
				while(backX1 >= 0){
					try {
						Thread.sleep(7);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						er = "thread error";
					}
					h3.sendMessage(h3.obtainMessage());

					if(backX1 == 0){
						background = false;
					}
				}
				
			}
		}
		Handler h3 = new Handler(){
			public void handleMessage(android.os.Message msg) {
				
				backX1 -= 1;
				backX2 -= 1;
				
				invalidate();
			};
		};
}
