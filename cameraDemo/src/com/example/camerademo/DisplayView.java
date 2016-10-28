package com.example.camerademo;

import java.io.IOException;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.PreviewCallback;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class DisplayView extends SurfaceView implements android.view.SurfaceHolder.Callback{

	Context con;
	Camera camera;
	SurfaceHolder surHolder;
	
	public DisplayView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		
		surHolder = getHolder();
		surHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		surHolder.addCallback(this);
		con = context;
	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
		camera.startPreview();
		camera.setDisplayOrientation(90);
	}

	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		camera = Camera.open();

		try{
			camera.setPreviewDisplay(surHolder);
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
		
	}

	PreviewCallback pcb = new PreviewCallback() {
		
		@Override
		public void onPreviewFrame(byte[] arg0, Camera arg1) {
			// TODO Auto-generated method stub
			DisplayView.this.invalidate();
		}
	};
	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		
		camera.stopPreview();
		camera.release();
		//camera = null;
	}

}
