package com.example.runcarrun;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;

public class TwoPlayers extends Activity implements OnClickListener{

	EditText p1, p2;
	ImageView raceIt;
	
	Drag2 drag2 ;
	
	
	
	
	
	String p1Name, p2Name;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
		                                WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
				
		setContentView(R.layout.two_player_settings);
		
		p1 = (EditText)findViewById(R.id.editText1);
		p2 = (EditText)findViewById(R.id.editText2);
		
		raceIt = (ImageView)findViewById(R.id.imageView1);
		
		raceIt.setOnClickListener(this);
		
		
		
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
		p1Name = p1.getText().toString();
		p2Name = p2.getText().toString();
		
		drag2 = new Drag2(this, p1Name, p2Name);
		setContentView(drag2);

	}
	

	
}
