package com.example.runcarrun;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class MainActivity extends Activity implements OnClickListener {

	ImageView two, three;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		two = (ImageView)findViewById(R.id.imageView1);
		three = (ImageView)findViewById(R.id.imageView2);
		
		two.setOnClickListener(this);
		three.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId()){
		case R.id.imageView1:
			Intent twoClass = new Intent(this, TwoPlayers.class);
			startActivity(twoClass);
			break;
		case R.id.imageView2:
			Intent threeClass = new Intent(this, ThreePlayers.class);
			startActivity(threeClass);
			break;
		}
	}
}

