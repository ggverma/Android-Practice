package com.example.iq_game;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

	String[] dataPool = {"What is red + blue + green?","white","black","yellow","cyan",
						"If you have 3 brothers who have only 2 brothers and 1 sister, how many are you in total?","4","5","6","None of the above",
						"Who is 26?","Z","X","Y","A",
						"If two boys run at same speed, who will win?","One running towards goal","One with longer steps","One with longer legs","One with greater speed",
						"What color does a green leaf has?","Not green","Green","Yellow","Black",
						"What can never stop?","Motion","Time","Light","Gravity",
						"What can you see in mirror but never in reality?","Your eyes","Your lips","Your teeth","Your back hair",
						"What has four legs but no feet?","Horse","Lion","Panda","Elephant",
						"What never goes up but continously come down?","River","Blood Pressure","Temperature","Wind",
						"If 4+2=1 and 8+4=2, what is 4-6?","9","2","6","-2"};
	
	//int IQ;
	
	TextView tv;
	Button b1, b2, b3, b4;
	MediaPlayer mp_pos, mp_neg;
	
	int correct, incorrect;
	int qAsked;
	int qNumber;
	int correct_ans;
	int ansButton;
	int index[] ;
	int correctID;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		b1 = (Button)findViewById(R.id.button1);
		b2 = (Button)findViewById(R.id.button2);
		b3 = (Button)findViewById(R.id.button3);
		b4 = (Button)findViewById(R.id.button4);
		
		b1.setOnClickListener(this);
		b2.setOnClickListener(this);
		b3.setOnClickListener(this);
		b4.setOnClickListener(this);
		
		mp_pos = MediaPlayer.create(this, R.raw.yes);
		mp_neg = MediaPlayer.create(this, R.raw.screamwhat);
		
		tv = (TextView)findViewById(R.id.textView1);
		
		correct = incorrect = qAsked = 0;
				
		index = new int[10];
		
		for(int i = 0 ; i < 10 ; i++)
			index[i]=567+i;
		
		startGame();
		
	}
	
	public void startGame(){
		
			qAsked++;
			if(qAsked == 11){
				Intent in = new Intent(this, Result.class);
				in.putExtra("Correct", correct);
				in.putExtra("Incorrect", incorrect);
				//in.putExtra("IQ",IQ);
				startActivity(in);
				correct = incorrect = 0;
				qAsked = 1;
			}
			else{
				qNumber = (int)(Math.random()*10);
				correct_ans = 5*qNumber + 1;
				index[qAsked - 1] = qNumber;
				
				for(int i = 0 ; i < 10 ; i++)
					for(int j = 0 ; j < i ; j++)
						if(index[i]==index[j]){
							qAsked--;
							startGame();
						}
				
				tv.setText(dataPool[qNumber*5]);
				ansButton = (int)(Math.random()*4);
				if(ansButton==0){
					b1.setText(dataPool[correct_ans]);
					b2.setText(dataPool[correct_ans+1]);
					b3.setText(dataPool[correct_ans+2]);
					b4.setText(dataPool[correct_ans+3]);
					correctID = b1.getId();
				}
				else if(ansButton==1){
					b2.setText(dataPool[correct_ans]);
					b1.setText(dataPool[correct_ans+2]);
					b3.setText(dataPool[correct_ans+1]);
					b4.setText(dataPool[correct_ans+3]);
					correctID = b2.getId();
				}
				else if(ansButton==2){
					b3.setText(dataPool[correct_ans]);
					b4.setText(dataPool[correct_ans+3]);
					b2.setText(dataPool[correct_ans+2]);
					b1.setText(dataPool[correct_ans+1]);
					correctID = b3.getId();
				}
				else if(ansButton==3){
					b4.setText(dataPool[correct_ans]);
					b3.setText(dataPool[correct_ans+3]);
					b2.setText(dataPool[correct_ans+2]);
					b1.setText(dataPool[correct_ans+1]);
					correctID = b4.getId();
				}
				
				
			}
		}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if(arg0.getId() == correctID){
			correct++;
			mp_pos.start();
		}
		else{
			incorrect++;
			mp_neg.start();
		}
		startGame();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		
		MenuInflater mi = getMenuInflater();
		mi.inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		finish();		
		return true;
	}
}
