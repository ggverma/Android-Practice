package com.example.asynctaskclass;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ProgressBar;

public class MainActivity extends Activity {

	ProgressBar pb;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        pb = (ProgressBar)findViewById(R.id.progressBar1);
        
        ProgressUpdate pBarUpd = new ProgressUpdate();
        pBarUpd.execute();
    }

    class ProgressUpdate extends AsyncTask<Integer, Integer, Boolean>{

		@Override
		protected Boolean doInBackground(Integer... arg0) {
			// TODO Auto-generated method stub
			
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			publishProgress(10);
			return null;
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
			
			pb.incrementProgressBy(values[0]);
		}
    	
    	
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
