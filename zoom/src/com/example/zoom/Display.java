package com.example.zoom;

import android.annotation.SuppressLint;
import android.content.Context;
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
	
	int ptrMoveX, ptrMoveY;
	
	Paint myPaint;
	String zoom;
	
	int fingerCount = 0;
	int oldZ;
	
	public Display(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		
		myPaint = new Paint();
		myPaint.setTextSize(100);
		myPaint.setAntiAlias(true);
		myPaint.setColor(Color.BLACK);
		
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		
		canvas.drawText(zoom +"", 10, 700, myPaint);
		
		
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		switch(event.getAction() & MotionEvent.ACTION_MASK){
		case MotionEvent.ACTION_DOWN:
			
			downX = (int)event.getX();
			downY = (int)event.getY();
			break;
			
		case MotionEvent.ACTION_MOVE:
		
				moveX = (int)event.getX();
				moveY = (int)event.getY();
				
			
			
			if(fingerCount == 1){
				ptrMoveX = (int)event.getX(1);
				ptrMoveY = (int)event.getY(1);
				calspace();
			}
			
			
			
			break;
			
		case MotionEvent.ACTION_UP:
			
			upX = (int)event.getX();
			upY = (int)event.getY();
			
			break;
			
		case MotionEvent.ACTION_POINTER_DOWN:
			fingerCount ++;
			
			break;
		
		case MotionEvent.ACTION_POINTER_UP:
			fingerCount --;
			break;
		}
		
		invalidate();
		return true;
	}

 	public void calspace(){
 		int X = moveX - ptrMoveX;
 		int Y = moveY - ptrMoveY;
 		
 		int Z = (int)Math.sqrt((X*X + Y*Y));
 		
 		if(Z>oldZ + 10)
 			zoom = "zoom in";
 		if(Z<oldZ - 10)
 			zoom = "zoom out";
 		oldZ = Z;
 		
 		
 	}
	
}
