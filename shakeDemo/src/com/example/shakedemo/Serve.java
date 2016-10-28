package com.example.shakedemo;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.widget.Toast;

public class Serve extends Service{

	Intent intent;
	boolean running = true;
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		//running = arg0.getBooleanExtra("Running", true);
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
		Toast.makeText(this, "Service created", Toast.LENGTH_SHORT).show();
		
		Show show = new Show();
		
		show.execute();
	}
	
	class Show extends AsyncTask<String, Integer, Boolean>{

		@Override
		protected Boolean doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			while(running){
			try {
				Thread.sleep(3000);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				
				e.printStackTrace();
			}
			
			publishProgress();
			
			doInBackground();
		}
			return null;
		}
	@Override
	protected void onProgressUpdate(Integer... values) {
		// TODO Auto-generated method stub
		super.onProgressUpdate(values);
		
		Toast.makeText(Serve.this, "Service" + running, Toast.LENGTH_SHORT).show();
	}
	}
	/*@Override
	public ComponentName startService(Intent service) {
		// TODO Auto-generated method stub
		
		intent = service;
		return super.startService(service);
	}
	@Override
	public boolean stopService(Intent name) {
		// TODO Auto-generated method stub
		running = name.getBooleanExtra("Running", false);
		
		
		return super.stopService(name);
		
		
	}
	*/
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		running = false;
	}

}
