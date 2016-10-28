package com.example.mycalculator;

import java.util.Stack;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

	Button b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, bMul, bDiv, bAdd, bDiff, bEq, bAC;
	TextView resultView;
	String data="";
	Stack c;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		b0 = (Button)findViewById(R.id.button11);
		b1 = (Button)findViewById(R.id.button1);
		b2 = (Button)findViewById(R.id.button2);
		b3 = (Button)findViewById(R.id.button3);
		b4 = (Button)findViewById(R.id.button4);
		b5 = (Button)findViewById(R.id.button5);
		b6 = (Button)findViewById(R.id.button6);
		b7 = (Button)findViewById(R.id.button7);
		b8 = (Button)findViewById(R.id.button8);
		b9 = (Button)findViewById(R.id.button9);
		bMul = (Button)findViewById(R.id.buttonMul);
		bDiv = (Button)findViewById(R.id.buttonDiv);
		bAdd = (Button)findViewById(R.id.buttonAdd);
		bDiff = (Button)findViewById(R.id.buttonDiff);
		bEq = (Button)findViewById(R.id.buttonEq);
		bAC = (Button)findViewById(R.id.button12);
		
		b0.setOnClickListener(this);
		b1.setOnClickListener(this);
		b2.setOnClickListener(this);
		b3.setOnClickListener(this);
		b4.setOnClickListener(this);
		b5.setOnClickListener(this);
		b6.setOnClickListener(this);
		b7.setOnClickListener(this);
		b8.setOnClickListener(this);
		b9.setOnClickListener(this);
		bMul.setOnClickListener(this);
		bDiv.setOnClickListener(this);
		bAdd.setOnClickListener(this);
		bDiff.setOnClickListener(this);
		bEq.setOnClickListener(this);
		bAC.setOnClickListener(this);
		
		resultView = (TextView)findViewById(R.id.resultText);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
		switch(arg0.getId()){
		case R.id.button11://0 button
			data += 0;
			break;
		case R.id.button1:
			data += 1;
			break;
		case R.id.button2:
			data += 2;
			break;
		case R.id.button3:
			data += 3;
			break;
		case R.id.button4:
			data += 4;
			break;
		case R.id.button5:
			data += 5;
			break;
		case R.id.button6:
			data += 6;
			break;
		case R.id.button7:
			data += 7;
			break;
		case R.id.button8:
			data += 8;
			break;
		case R.id.button9:
			data += 9;
			break;
		case R.id.buttonMul:
			data += "*";
			break;
		case R.id.buttonDiv:
			data += "/";
			break;
		case R.id.buttonAdd:
			data += "+";
			break;
		case R.id.buttonDiff:
			data += "-";
			break;
		case R.id.buttonEq:
			result();
			break;
		case R.id.button12:
			data = "";
			break;
		}
		resultView.setText(data);
	}
	
	public void result(){
		
	}
}
