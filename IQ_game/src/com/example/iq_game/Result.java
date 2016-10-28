package com.example.iq_game;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

@SuppressLint("NewApi")
public class Result extends Activity implements  android.view.View.OnClickListener {
	
	TextView tv;
	Button b;
	int correct;
	int incorrect;
	
	MediaPlayer mp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
			
		tv = (TextView)findViewById(R.id.textView2);
		b = (Button)findViewById(R.id.button1);
		b.setOnClickListener(this);
		
		mp = MediaPlayer.create(this, R.raw.thunder);
		mp.start();
		
		correct = getIntent().getExtras().getInt("Correct");
		incorrect = getIntent().getExtras().getInt("Incorrect");
		
		tv.setText("Correct: " + correct + "\nIncorrect: " + incorrect);
		
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		mp.stop();
		finish();
	}

	
}
