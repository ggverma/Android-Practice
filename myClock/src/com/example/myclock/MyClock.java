package com.example.myclock;

import java.util.Calendar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.os.Handler;
import android.view.View;

public class MyClock extends View{
	
	Context con;
	Paint myPaint, minute, second, hour;
	String time[];
	int h, w, oldh, oldw;
	int min, sec, hr;
	Calendar c;
	boolean round;
	Handler handler;
	MediaPlayer mp;
	
	public MyClock(Context context){
		super(context);
		con = context;
		myPaint = new Paint();
		second = new Paint();
		minute = new Paint();
		hour = new Paint();
		mp = MediaPlayer.create(con, R.raw.tick);
		c = Calendar.getInstance();
		
		min = c.get(Calendar.MINUTE);
		sec = c.get(Calendar.SECOND);
		hr = c.get(Calendar.HOUR);
			
		myPaint.setColor(Color.BLACK);
		second.setColor(Color.RED);
		minute.setColor(Color.BLUE);
		hour.setColor(Color.GREEN);
		
		
		myPaint.setAntiAlias(true);
		second.setAntiAlias(true);
		minute.setAntiAlias(true);
		hour.setAntiAlias(true);
		
		myPaint.setTextSize(100);
		second.setTextSize(100);
		minute.setTextSize(100);
		hour.setTextSize(100);
		
		hr = c.get(Calendar.HOUR);
		min = c.get(Calendar.MINUTE);
		sec = c.get(Calendar.SECOND);
		
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		mp.start();
		if(hr == 0)
			hr = 12;
		
		myPaint.setColor(Color.BLACK);
		canvas.drawText("Time>> "+hr+":"+min+":"+sec, w/4, 3*h/4, myPaint);
		canvas.drawCircle(w/2, h/2, w/4, myPaint);
		
		myPaint.setColor(Color.WHITE);
		canvas.drawCircle(w/2, h/2, 0.9f*w/4, myPaint);
		
		myPaint.setColor(Color.RED);
		/*canvas.drawCircle(w/2, h/2 - w/4  , 10, myPaint);
		canvas.drawCircle(w/2, h/2 + w/4 + 5, 10, myPaint);
		canvas.drawCircle(w/2 - w/4 - 5, h/2, 10, myPaint);
		canvas.drawCircle(w/2 + w/4 + 5, h/2, 10, myPaint);
		canvas.drawCircle(w/2 + w/8 + 2.5f, h/2 - w/4 + 0.27f*w/8 +(1.732f*2.5f), 10, myPaint);
		canvas.drawCircle(w/2 + w/4 - 0.27f*w/8 +(1.732f*2.5f), h/2 - w/8 - 2.5f, 10, myPaint);
		canvas.drawCircle(w/2 + w/4 - 0.27f*w/8 +(1.732f*2.5f), h/2 + w/8 + 2.5f, 10, myPaint);
		canvas.drawCircle(w/2 + w/8 + 2.5f, h/2 + w/4 - 0.27f*w/8 -(1.732f*2.5f), 10, myPaint);
		canvas.drawCircle(w/2 - w/8 - 2.5f, h/2 + w/4 - 0.27f*w/8 -(1.732f*2.5f), 10, myPaint);
		canvas.drawCircle(w/2 - w/4 + 0.27f*w/8 +(1.732f*2.5f), h/2 + w/8 + 2.5f , 10, myPaint);
		canvas.drawCircle(w/2 - w/4 + 0.27f*w/8 +(1.732f*2.5f), h/2 - w/8 - 2.5f, 10, myPaint);
		canvas.drawCircle(w/2 - w/8 - 2.5f, h/2 - w/4 + 0.27f*w/8 +(1.732f*2.5f), 10, myPaint);*/
		
		hour.setTextSize(100);
		canvas.drawText("Red: Seconds", 40, 100, second);
		canvas.drawText("Blue: Minutes", 40, 200, minute);
		canvas.drawText("Green: Hours", 40, 300, hour);
		
		canvas.drawLine(w/2, h/4, w/2, h/4 - 10, hour);
		canvas.drawLine(3*w/4, h/2, 3*w/4 + 10, h/2 , hour);
		canvas.drawLine(w/2, 3*h/4, w/2, 3*h/4 + 10, hour);
		canvas.drawLine(w/4, h/2, w/4 - 10, h/2, hour);
		
		if(sec <=15 )
			canvas.drawLine(w/2, h/2, (w/2) + (w/60)*sec, (h/2) - w/4 + (w/60)*sec, second);
		else if(sec <=30 && sec > 15)
			canvas.drawLine(w/2, h/2, (w/2) + w/4 - (w/60)*(sec - 15), (h/2) + (w/60)*(sec - 15), second);
		else if(sec <=45 && sec > 30)
			canvas.drawLine(w/2, h/2, (w/2)  -(w/60)*(sec - 30), (h/2) + w/4 - (w/60)*(sec - 30), second);
		else if(sec > 45)
			canvas.drawLine(w/2, h/2, (w/2)  - w/4 + (w/60)*(sec - 45), (h/2) - (w/60)*(sec - 45), second);
		
		/*if(sec<=5)
			canvas.drawLine(w/2, h/2, w/2 + w/480*5*sec, h/2 - w/4 + (0.27f*w/480)*5*sec, second);
		else if(sec<=10)
			canvas.drawLine(w/2, h/2, (w/2) + w/8 + (0.73f*w/480)*5*(sec), (h/2) - w/4 + 0.27f*w/8*5 + (0.73f*w/480)*5*(sec), second);
		else if(sec<=15)
			canvas.drawLine(w/2, h/2, w/2 + w/4 - (0.27f*w)/8*5 + (0.27f/480)*w*5*sec, h/2 - w/8 + w/480*5*sec, second);
		else if(sec<=20)
			canvas.drawLine(w/2, h/2, w/2 + w/4 - (0.23f*w/480)*5*sec, h/2 + (w/480)*5*sec, second);
		else if(sec<=25)
			canvas.drawLine(w/2, h/2, (w/2) + w/4 - (0.27f*w/8)*5 - (0.73f*w/480)*5*sec, (h/2) + w/8 + (0.73f*w/480)*5*sec, second);
		else if(sec<=30)
			canvas.drawLine(w/2, h/2, w/2 + w/8 - (w)/480*5*sec, h/2 + w/4 - 0.27f*(w/8)*5 + (0.27f*w)/480*5*sec, second);
		else if(sec<=35)
			canvas.drawLine(w/2, h/2, w/2 - w/480*5*sec, h/2 + w/4 - (0.27f*w/480)*5*sec, second);
		else if(sec<=40)
			canvas.drawLine(w/2, h/2, (w/2) - w/8 - (0.73f*w/480)*5*(sec), (h/2) + w/4 - 0.27f*w/8*5 - (0.73f*w/480)*5*(sec), second);
		else if(sec<=45)
			canvas.drawLine(w/2, h/2, w/2 - w/4 + (0.27f*w)/8*5 - (0.27f/480)*w*5*sec, h/2 + w/8 - w/480*5*sec, second);
		else if(sec<=50)
			canvas.drawLine(w/2, h/2, w/2 - w/4 + (0.23f*w/480)*5*sec, h/2 - (w/480)*5*sec, second);
		else if(sec<=55)
			canvas.drawLine(w/2, h/2, (w/2) - w/4 + (0.27f*w/8)*5 + (0.73f*w/480)*5*sec, (h/2) - w/8 - (0.73f*w/480)*5*sec, second);
		else if(sec<=60)
			canvas.drawLine(w/2, h/2, (w/2 - w/8 + (w)/480*5*sec),( h/2 - w/4 + 0.27f*(w/8)*5 - (0.27f*w)/480*5*sec), second);*/
		
		if(min <=15 )
			canvas.drawLine(w/2, h/2, (w/2) + (w/60)*min, (h/2) - w/4 + (w/60)*min, minute);
		else if(min <=30 && min > 15)
			canvas.drawLine(w/2, h/2, (w/2) + w/4 - (w/60)*(min - 15), (h/2) + (w/60)*(min - 15), minute);
		else if(min <=45 && min > 30)
			canvas.drawLine(w/2, h/2, (w/2)  -(w/60)*(min - 30), (h/2) + w/4 - (w/60)*(min - 30), minute);
		else if(min > 45)
			canvas.drawLine(w/2, h/2, (w/2)  - w/4 + (w/60)*(min - 45), (h/2) - (w/60)*(min - 45), minute);
		
		hour.setTextSize(50);
		
		if(hr ==1 ){
			canvas.drawLine(w/2, h/2, (w/2) + w/8 + (0.73f*w/480)*(min), (h/2) - w/4 + 0.27f*w/8 + (0.73f*w/480)*(min), hour);
			canvas.drawText(hr+"",  (w/2)  + w/8 + 60, (h/2) - w/ 4 + 0.27f*w/8, second);
		}
		else if(hr ==4){
			canvas.drawLine(w/2, h/2, (w/2) + w/4 - (0.27f*w/8) - (0.73f*w/480)*min, (h/2) + w/8 + (0.73f*w/480)*min, hour);
			canvas.drawText(hr+"",  w/2 + w/4 - 0.27f*w/8, (h/2) + w/8  +60, second);
		}
		else if(hr == 7){
			canvas.drawLine(w/2, h/2, (w/2) - w/8 - (0.73f*w/480)*(min), (h/2) + w/4 - 0.27f*w/8 - (0.73f*w/480)*(min), hour);
			canvas.drawText(hr+"",  (w/2)  - w/8 - 60, (h/2) + w/ 4 - 0.27f*w/8, second);			
		}
		else if(hr == 10){
			canvas.drawLine(w/2, h/2, (w/2) - w/4 + (0.27f*w/8) + (0.73f*w/480)*min, (h/2) - w/8 - (0.73f*w/480)*min, hour);
			canvas.drawText(hr+"",  w/2 - w/4 + 0.27f*w/8, (h/2) - w/8  -60, second);
		}
		else if(hr == 12){
			canvas.drawLine(w/2, h/2, w/2 + w/480*min, h/2 - w/4 + (0.27f*w/480)*min, hour);
			canvas.drawText(hr+"",  (w/2) , (h/2) - w/4 -60, second);
		}
		else if(hr == 3){
			canvas.drawLine(w/2, h/2, w/2 + w/4 - (0.23f*w/480)*min, h/2 + (w/480)*min, hour);
			canvas.drawText(hr+"", (w/2) + w/4 + 60, (h/2)  , second);
		}
		else if(hr==6){
			canvas.drawLine(w/2, h/2, w/2 - w/480*min, h/2 + w/4 - (0.27f*w/480)*min, hour);
			canvas.drawText(hr+"",  (w/2)  , (h/2) + w/4  +60, second);
		}
		else if(hr==9){
			canvas.drawLine(w/2, h/2, w/2 - w/4 + (0.23f*w/480)*min, h/2 - (w/480)*min, hour);
			canvas.drawText(hr+"",  (w/2)  - w/4  - 60, (h/2)  , second);
		}
		else if(hr==2){
			canvas.drawLine(w/2, h/2, w/2 + w/4 - (0.27f*w)/8 + (0.27f/480)*w*min, h/2 - w/8 + w/480*min, hour);
			canvas.drawText(hr+"",  w/2 + w/4 - 0.27f*w/8, (h/2) - w/8  -60, second);
		}
		else if(hr==5){
			canvas.drawLine(w/2, h/2, w/2 + w/8 - (w)/480*min, h/2 + w/4 - 0.27f*(w/8) + (0.27f*w)/480*min, hour);
			canvas.drawText(hr+"", (w/2)  + w/8 + 60, (h/2) + w/ 4 - 0.27f*w/8, second);
		}
		else if(hr==8){
			canvas.drawLine(w/2, h/2, w/2 - w/4 + (0.27f*w)/8 - (0.27f/480)*w*min, h/2 + w/8 - w/480*min, hour);
			canvas.drawText(hr+"",  w/2 - w/4 + 0.27f*w/8, (h/2) + w/8  +60, second);
		}
		else if(hr==11){
			canvas.drawLine(w/2, h/2, (w/2 - w/8 + (w)/480*min),( h/2 - w/4 + 0.27f*(w/8) - (0.27f*w)/480*min), hour);
			canvas.drawText(hr+"",  (w/2)  - w/8 - 60, (h/2) - w/ 4 + 0.27f*w/8 , second);
		}
			
				
		
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		super.onSizeChanged(w, h, oldw, oldh);
		this.w = w;
		this.h = h;
		this.oldw = oldw;
		this.oldh = oldh;
		
		handler = new Handler();
		starter();
	}

	public void starter(){
		updateTIme();

handler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				starter();
				invalidate();
			}
		}, 1000);
	}
	
	public void updateTIme(){
		Calendar t;
		t = Calendar.getInstance();
		hr = t.get(Calendar.HOUR);
		min = t.get(Calendar.MINUTE);
		sec = t.get(Calendar.SECOND);
		
	}
}