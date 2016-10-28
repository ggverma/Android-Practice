package com.example.webviewdemo;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.Menu;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

@SuppressLint("SetJavaScriptEnabled")
public class MainActivity extends Activity {

	WebView webView;
	WebSettings webSettings;
	ProgressBar pb;
	
	MyWebViewClient myWebViewClient;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		myWebViewClient = new MyWebViewClient();
		webView = (WebView)findViewById(R.id.webView1);
		
		pb = (ProgressBar)findViewById(R.id.progressBar1);
		
		webSettings = webView.getSettings();
		
		webSettings.setBuiltInZoomControls(true);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.loadUrl("https://www.facebook.com");
		
		webView.setWebChromeClient(new WebChromeClient(){
			
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				// TODO Auto-generated method stub
				super.onProgressChanged(view, newProgress);
				pb.setProgress(newProgress);
			}
		});
		webView.setWebViewClient(myWebViewClient);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		webView.goBack();
	}
}

class MyWebViewClient extends WebViewClient{
	
	WebView wv;
	
	@Override
	public boolean shouldOverrideUrlLoading(WebView view, String url) {
		// TODO Auto-generated method stub
		view.loadUrl(url);
		wv = view;
		return super.shouldOverrideUrlLoading(view, url);
	}
	
}
