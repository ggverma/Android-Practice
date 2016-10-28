package com.example.bluetoothdemo;

import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class SendFile extends Activity implements OnClickListener{
	
	Button selectImage, sendImage;
	ImageView imV;
	Uri picUri;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sendfile);
		
		selectImage = (Button)findViewById(R.id.button1);
		sendImage = (Button)findViewById(R.id.button2);
		
		selectImage.setOnClickListener(this);
		sendImage.setOnClickListener(this);
		
		imV = (ImageView)findViewById(R.id.imageView1);
		
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId()){
		case R.id.button1:
			Intent inpp = new Intent(Intent.ACTION_PICK);
			inpp.setType("image/");
			startActivityForResult(inpp, 10);
			
			break;
		case R.id.button2:
			Intent shareIn = new Intent(android.content.Intent.ACTION_SEND);
			shareIn.setType("image/");
			shareIn.setComponent(new ComponentName("com.android.bluetooth","com.android.bluetooth.app.BluetoothOppLauncherActivity"));
			
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		picUri = data.getData();
		
		if(picUri!=null){
			try {
				Bitmap b1 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), picUri);
				imV.setImageBitmap(b1);
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
