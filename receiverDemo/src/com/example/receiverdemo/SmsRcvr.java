package com.example.receiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SmsRcvr extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){
			Bundle bd = intent.getExtras();
			
			Object[] p = (Object[])bd.get("pdus");
			SmsMessage[] msg = new SmsMessage[p.length];
			
			for(int i = 0 ; i < p.length ; i++){
				msg[i] = SmsMessage.createFromPdu((byte[])p[i]);
			}
			if(msg.length > -1)
				Toast.makeText(context, msg[0].getMessageBody(), Toast.LENGTH_LONG).show();
		}
	}

}
