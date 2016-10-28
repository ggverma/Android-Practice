package com.example.mymathgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

	Button b5, b1;
	TextView tv1, tv2, tv3, tv4;
	int vol, diff;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		vol = 30;
		diff = 1;
		
		b5 = (Button)findViewById(R.id.button5);
		b5.setOnClickListener(this);
		
		b1 = (Button)findViewById(R.id.button1);
		b1.setOnClickListener(this);
		
		tv1 = (TextView)findViewById(R.id.textView1);
		tv1.setText("Questions appear here!");
				
		tv2 = (TextView)findViewById(R.id.textView2);
		tv2.setText("Timer appear here!");
		
		tv3 = (TextView)findViewById(R.id.textView3);
		tv3.setText("Volume: "+vol);
		
		tv4 = (TextView)findViewById(R.id.textView4);
		tv4.setText("Difficulty: "+diff);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId()){
		case R.id.button5:
			Intent game = new Intent(this, Game.class);
			game.putExtra("Difficulty", diff);
			startActivity(game);
			break;
		case R.id.button1:
			Intent settings  = new Intent(this, Settings.class);
			settings.putExtra("Set_Vol", vol);
			settings.putExtra("Set_Diff", diff);
			startActivityForResult(settings, 10);
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		vol = data.getExtras().getInt("Volume");
		diff = data.getExtras().getInt("Difficulty");
		
		tv3.setText("Volume: " + vol);
		tv4.setText("Difficulty: "+ diff);
		
	}
}
