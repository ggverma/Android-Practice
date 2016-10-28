package com.example.threadinclass;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

public class MainActivity extends Activity {

	ProgressBar pb1;
	int fillAmt;
	int i = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		pb1 = (ProgressBar)findViewById(R.id.progressBar1);
		
		Threader threader = new Threader();
		threader.start();
	}
	
	class Threader extends Thread{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			while(i<10){
				try{
					Thread.sleep(1000);
					
				}
				catch(Exception e){
					e.printStackTrace();
				}
				h1.sendMessage(h1.obtainMessage());
				
				i++;	
							
			}
			
		}
		
	
	Handler h1 = new Handler(){
		public void handleMessage(android.os.Message msg) {
			pb1.incrementProgressBy(10);
			
		};
	};
	
	
	}
}
