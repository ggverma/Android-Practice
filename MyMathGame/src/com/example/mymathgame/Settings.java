package com.example.mymathgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class Settings extends Activity implements OnClickListener {

	SeekBar s1, s2;
	Button b1;
	TextView tv1, tv2;
	
	int s1_value, s2_value;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);
		
		tv1 = (TextView)findViewById(R.id.textView1);
		tv2 = (TextView)findViewById(R.id.textView2);
		
		s1_value = getIntent().getExtras().getInt("Set_Vol");
		s2_value = getIntent().getExtras().getInt("Set_Diff");
		
		tv1.setText("Volume: " + s1_value);
		tv2.setText("Difficulty: " + s2_value);
		
		s1 = (SeekBar)findViewById(R.id.seekBar1);
		s2 = (SeekBar)findViewById(R.id.seekBar2);
		
		b1 = (Button)findViewById(R.id.button1);
		b1.setOnClickListener(this);
		
		s1.setProgress(s1_value);
		s2.setProgress(s2_value);
		
		s1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
				s1_value = s1.getProgress();
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
				// TODO Auto-generated method stub
					tv1.setText("Volume: " + arg1);
			}
		});
		
		s2.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
				s2_value = s2.getProgress();
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
				// TODO Auto-generated method stub
				tv2.setText("Difficulty: " + arg1);
			}
		});
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Intent in = new Intent();
		in.putExtra("Volume", s1_value);
		in.putExtra("Difficulty", s2_value);
		setResult(0, in);
		finish();
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		//super.onBackPressed();
		Intent in = new Intent();
		in.putExtra("Volume", s1_value);
		in.putExtra("Difficulty", s2_value);
		setResult(0, in);
		finish();
	}
}
