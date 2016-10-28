package com.example.camerademo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import android.app.Activity;
import android.content.ContentValues;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Images.Media;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{

	Button b1;
	FrameLayout fl;
	DisplayView dV;
	Uri uri;
	OutputStream file1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
		dV = new DisplayView(this);
		
		b1 = (Button)findViewById(R.id.button1);
		b1.setOnClickListener(this);
		
		fl = (FrameLayout)findViewById(R.id.frameLayout1);
		fl.addView(dV);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
		dV.camera.takePicture(shCB, raw, jpeg);
		
		
	}
	
	ShutterCallback shCB = new ShutterCallback() {
		
		@Override
		public void onShutter() {
			// TODO Auto-generated method stub
			
		}
	};
	
	PictureCallback raw = new PictureCallback() {
		
		@Override
		public void onPictureTaken(byte[] arg0, Camera arg1) {
			// TODO Auto-generated method stub
			
		}
	};
	
	PictureCallback jpeg = new PictureCallback() {
		
		@Override
		public void onPictureTaken(byte[] arg0, Camera arg1) {
			// TODO Auto-generated method stub
			
			uri = getContentResolver().insert(Media.EXTERNAL_CONTENT_URI, new ContentValues());
			
			try {
				file1 = getContentResolver().openOutputStream(uri);
				file1.write(arg0);
				file1.flush();
				file1.close();
				dV.camera.startPreview();
				Toast.makeText(MainActivity.this, uri.toString(), Toast.LENGTH_LONG).show();
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	};
}
