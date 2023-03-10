package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {

    public static final String STANDARD_ACTION = "com.example.myapplication.MY_BROADCAST";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null && intent.getAction().equals(STANDARD_ACTION)){
            Toast.makeText(context, "received in MyBroadcastReceiver", Toast.LENGTH_SHORT).show();
            abortBroadcast();
        }
    }
}