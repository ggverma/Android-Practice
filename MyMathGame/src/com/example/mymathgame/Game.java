package com.example.mymathgame;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Game extends Activity implements OnClickListener{

	Button b1, b2, b3, b4, b5;
	TextView tv1, tv2;
	int time_now;
	int true_count, false_count;
	long SET_TIME = 30000;
	int ran1, ran2,result, op_choose;
	int butt_selector;
	int ofb1, ofb2, ofb3, ofb4;
	long time_left;
	double accu;
	int difficulty;
	int[] threeRanValues;
	ProgressBar pb;
	
	protected int[] threeRandom(){
		int[] values = new int[3];
		
		values[0] = (int)(Math.random()*10+result);
		values[1] = (int)(Math.random()*10+result);
		values[2] = (int)(Math.random()*10+result);
		
		return values;
	}
	protected void generator(){
	ran1 = (int)(Math.random()*difficulty + Math.random()*difficulty + 10);
	ran2 = (int)(Math.random()*difficulty + Math.random()*difficulty + 5);
	
	if(ran1 == (ran2+1) || ran1 == (ran2+2) || ran1 == (ran2+0) || ran1 == (ran2-1) || ran1 == (ran2-2))
		generator();
	
	op_choose = (int)(Math.random()*4);
	if(op_choose==0){// Addition
		tv1.setText(ran1 + " + " +ran2);
		result = (ran1 + ran2);
	}
	else if(op_choose == 1){//Subtraction
		tv1.setText(ran1 + " - " +ran2);
		result = (ran1 - ran2);
	}
	else if(op_choose == 2){//Multiplication
		tv1.setText(ran1 + " * " +ran2);
		result = (ran1 * ran2);
	}
	else{//Division
		tv1.setText(ran1 + " / " +ran2);
		result = (ran1 / ran2);
	}
	butt_selector = (int)(Math.random()*4);
	threeRanValues = threeRandom();
	while(true){
		if((threeRanValues[0]==threeRanValues[1])||(threeRanValues[0]==threeRanValues[2])||(threeRanValues[2]==threeRanValues[1])||(threeRanValues[0]==result)||(threeRanValues[1]==result)||(threeRanValues[2]==result))
			threeRanValues = threeRandom();
		else
			break;
	}
	if(butt_selector == 0){
		b1.setText(result+"");
		b2.setText(threeRanValues[0]+"");
		b3.setText(threeRanValues[1]+"");
		b4.setText(threeRanValues[2]+"");
	}
	else if(butt_selector == 1){
		b1.setText(threeRanValues[0]+"");
		b2.setText(result+"");
		b3.setText(threeRanValues[1]+"");
		b4.setText(threeRanValues[2]+"");
	}
	else if(butt_selector == 2){
		b1.setText(threeRanValues[0]+"");
		b2.setText(threeRanValues[1]+"");
		b3.setText(result+"");
		b4.setText(threeRanValues[2]+"");
	}
	else{
		b1.setText(threeRanValues[0]+"");
		b2.setText(threeRanValues[1]+"");
		b3.setText(threeRanValues[2]+"");
		b4.setText(result+"");
	}
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game);
		
		//t = new Thread(this);
		//t.start();
		difficulty = getIntent().getExtras().getInt("Difficulty");
		time_now = true_count = false_count = 0;
		
		b1 = (Button)findViewById(R.id.button1);
		b2 = (Button)findViewById(R.id.button2);
		b3 = (Button)findViewById(R.id.button3);
		b4 = (Button)findViewById(R.id.button4);
		b5 = (Button)findViewById(R.id.button5);
		
		b5.setVisibility(View.INVISIBLE);
		
		b1.setOnClickListener(this);
		b2.setOnClickListener(this);
		b3.setOnClickListener(this);
		b4.setOnClickListener(this);
		b5.setOnClickListener(this);
	
		tv1 = (TextView)findViewById(R.id.textView1);
		
		tv2 = (TextView)findViewById(R.id.textView2);
		tv2.setText("");
		
		new CountDownTimer(SET_TIME, 1000) {
			
			@Override
			public void onTick(long arg0) {
				// TODO Auto-generated method stub
				pb = (ProgressBar)findViewById(R.id.progressBar1);
				tv2.setText("Time Left: "+ arg0/1000);
				time_left = arg0/1000;
				pb.setProgress((int)(time_left/SET_TIME)*100);
			}
			
			@Override
			public void onFinish() {
				// TODO Auto-generated method stub
				accu = (double)true_count/(true_count+false_count)*100;
				tv2.setVisibility(View.INVISIBLE);
				tv1.setText("Total questions asked: "+(true_count+false_count)+"\nCorrect Answers: "+true_count+"\nIncorrect Answers: "+false_count+"\n\nAccuracy: "+ accu);
				b1.setVisibility(View.INVISIBLE);
				b2.setVisibility(View.INVISIBLE);
				b3.setVisibility(View.INVISIBLE);
				b4.setVisibility(View.INVISIBLE);
				b5.setVisibility(View.VISIBLE);
				pb.setVisibility(View.INVISIBLE);
			}
		}.start();
		generator();
		
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		ofb1 = Integer.parseInt(b1.getText().toString());
		ofb2 = Integer.parseInt(b2.getText().toString());
		ofb3 = Integer.parseInt(b3.getText().toString());
		ofb4 = Integer.parseInt(b4.getText().toString());
		
		switch(arg0.getId()){
		case R.id.button1:
			if(ofb1 == result)
				true_count++;
			else
				false_count++;
			break;
		case R.id.button2:
			if(ofb2 == result)
				true_count++;
			else
				false_count++;
			break;
		case R.id.button3:
			if(ofb3 == result)
				true_count++;
			else
				false_count++;
			break;
		case R.id.button4:
			if(ofb4 == result)
				true_count++;
			else
				false_count++;
			break;
		case R.id.button5:
			finish();
		}
		if(time_left < SET_TIME)
			generator();
	}
}
