package com.example.stopwatch;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements OnClickListener {

	Chronometer cr;
	Button b1, b2, b3, b4;
	TextView tv;
	int i=0;
	boolean initial = true;
	int last_min, last_sec;
	int min, sec;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		cr = (Chronometer)findViewById(R.id.chronometer1);
		
		b1 = (Button)findViewById(R.id.button1);
		b2 = (Button)findViewById(R.id.button2);
		b3 = (Button)findViewById(R.id.button3);
		b4 = (Button)findViewById(R.id.button4);
		
		b1.setOnClickListener(this);
		b2.setOnClickListener(this);
		b3.setOnClickListener(this);
		b4.setOnClickListener(this);
		
		tv = (TextView)findViewById(R.id.textView1);
		tv.setText("");
		
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
		String s = cr.getText().toString();
		String[] time = s.split(":");
		min = Integer.parseInt(time[0]);
		sec = Integer.parseInt(time[1]);
		
		switch(arg0.getId()){
		case R.id.button1://Start
			Toast.makeText(this, "Started!", Toast.LENGTH_LONG ).show(); // Making toast is displaying small messages.
			int ms= (Integer.parseInt(time[0]))*60000 + (Integer.parseInt(time[1]))*1000;
			
			cr.setBase(SystemClock.elapsedRealtime()-ms);
			cr.start();
			break;
		case R.id.button2://Stop
			Toast.makeText(this, "Stopped!", Toast.LENGTH_LONG ).show();
			cr.stop();
			break;	
		case R.id.button3://Lap
			i++;
			
			Toast.makeText(this, "Lap "+i+" taken!", Toast.LENGTH_LONG ).show();
			
			if(initial){
				tv.setText(tv.getText().toString() + "\n" + i + "." +  time[0] + ":" + time[1]);
				initial=false;
				last_min = min;
				last_sec = sec;
				
			}
			else{
				tv.setText(tv.getText().toString() + "\n" + i + "." +  (min - last_min) + ":" + (sec - last_sec));
				last_min = min;
				last_sec = sec;
			}
			break;
		case R.id.button4://Reset
			Toast.makeText(this, "Clock Reset!", Toast.LENGTH_LONG ).show();
			cr.setBase(SystemClock.elapsedRealtime());
			tv.setText("");
			i=0;
			cr.stop();
			initial = true;
		}
		
	}

}
