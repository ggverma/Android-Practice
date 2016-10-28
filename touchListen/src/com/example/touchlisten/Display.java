package com.example.touchlisten;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;


@SuppressLint("ClickableViewAccessibility")
public class Display extends View{
	
	int downX = 0, downY = 0;
	int moveX = 0, moveY = 0;
	int upX = 0, upY = 0;
	
	Paint myPaint;
	Bitmap bitmap;
	int imX = 0, imY = 0;
	boolean flag = true ;
	int w, h;
	String s = "Screen Untouched!";
	int ptrUpX = 0, ptrUpY = 0, ptrDownX = 0, ptrDownY = 0, ptrMoveX = 0, ptrMoveY = 0;
	
	int oldMX, oldMY, oldPMX, oldPMY;
	
	int count = 0;
	
	String zoom = "";
	
	double thingsFir, thingsSec;
	
	int got2 = 0;
	
	public Display(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		
		myPaint = new Paint();
		myPaint.setTextSize(100);
		myPaint.setAntiAlias(true);
		myPaint.setColor(Color.BLACK);
		bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ball); 
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
	
		canvas.drawText("Down at: (" +downX + ", " + downY+")", 10, 100, myPaint);
		canvas.drawText("Moving at: (" + moveX + ", " + moveY + ")", 10, 200, myPaint);
		canvas.drawText("Up at: (" + upX + ", " + upY + ")", 10, 300, myPaint);
		
		canvas.drawText("2nd Down at: (" +ptrDownX + ", " + ptrDownY+")", 10, 400, myPaint);
		canvas.drawText("2nd Moving at: (" + ptrMoveX + ", " + ptrMoveY + ")", 10, 500, myPaint);
	canvas.drawText("2nd Up at: (" + ptrUpX + ", " + ptrUpY + ")", 10, 600, myPaint);
		
		if(flag )
			canvas.drawBitmap(bitmap, moveX - 125, moveY - 125, myPaint);
		else
			canvas.drawBitmap(bitmap, imX - 125, imY - 125, myPaint);
		
		canvas.drawText(s, 10, 700, myPaint);
		canvas.drawText("Total fingers: " + count, 10, 800, myPaint);
		
		canvas.drawText(zoom, 10, 900, myPaint);
		
		/*if(count==2){
			if(Math.abs((oldMX - oldPMX)*(oldMY - oldPMY)) < Math.abs((moveX - ptrMoveX)*(moveY - ptrMoveY)))
				canvas.drawText("Zoom in!", 10, 900, myPaint);
			else if(Math.abs((oldMX - oldPMX)*(oldMY - oldPMY)) > Math.abs((moveX - ptrMoveX)*(moveY - ptrMoveY)))
				canvas.drawText("Zoom out!", 10, 900, myPaint);
			else
				canvas.drawText("cant figure out!", 10, 900, myPaint);
		}*/
		
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		switch(event.getAction() & MotionEvent.ACTION_MASK){
		case MotionEvent.ACTION_DOWN:
			//firstFin = true;
			count++;

			downX = (int)event.getX();
			downY = (int)event.getY();
			if(downX >= imX - 125  && downX <= imX + 125 && downY <= imY+125 && downY >= imY-125)
				flag = true;
			else
				flag = false;		
			break;
			
		case MotionEvent.ACTION_MOVE:
			
		
				moveX = (int)event.getX();
				moveY = (int)event.getY();
				
				
				oldMX = moveX;
				oldMY = moveY;
			
			if(count == 2 ){
				ptrMoveX = (int)event.getX(1);
				ptrMoveY = (int)event.getY(1);
				
				oldPMX = ptrMoveX;
				oldPMY = ptrMoveY;
			}
			
			
			break;
		case MotionEvent.ACTION_UP:
			if(thingsFir > thingsSec){
			upX = (int)event.getX();
			upY = (int)event.getY();
			}
			
			if(flag){
				imX = upX;
				imY = upY;
			}
			
			if(downX < upX && (upX - downX) > (Math.abs(upY - downY)))
				s = "Right swipe";
			else if(downX> upX && (downX - upX) > (Math.abs(upY - downY)))
				s = "Left swipe";
			if(downY < upY && (upY - downY) > (Math.abs(upX - downX)) )
				s = " Down swipe";
			else if(downY> upY && (downY - upY) > (Math.abs(upX - downX)))
				s = " Up swipe";
			
			
			
			break;
		case MotionEvent.ACTION_POINTER_DOWN:
			
			count++;
			ptrDownX = (int)event.getX(1);
			ptrDownY = (int)event.getY(1);
			
			
			break;
		case MotionEvent.ACTION_POINTER_UP:
			
			ptrUpX = (int)event.getX(1);
			ptrUpY = (int)event.getY(1);
			count --;
		}
			
		invalidate();
		return true;
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		super.onSizeChanged(w, h, oldw, oldh);
		this.w = w;
		this.h = h;
	}
}
