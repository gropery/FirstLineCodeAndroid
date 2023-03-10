package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

//    private NetworkChangeReceiver networkChangeReceiver;
//    private MyBroadcastReceiver myBroadcastReceiver;

    private LocalReceiver localReceiver;
    private LocalBroadcastManager localBroadcastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        IntentFilter intentFilter = new IntentFilter();
//        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
//        networkChangeReceiver = new NetworkChangeReceiver();
//        registerReceiver(networkChangeReceiver, intentFilter);

//        IntentFilter intentFilter = new IntentFilter(MyBroadcastReceiver.STANDARD_ACTION);
//        myBroadcastReceiver = new MyBroadcastReceiver();
//        registerReceiver(myBroadcastReceiver, intentFilter);

        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        IntentFilter intentFilter = new IntentFilter(LocalReceiver.LOCAL_ACTION);
        localReceiver = new LocalReceiver();
        localBroadcastManager.registerReceiver(localReceiver, intentFilter);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                //静态标准发送
//                String fullName = "com.example.myapplication.MyBroadcastReceiver";    //这里要完整接收类名
//                Intent intent = new Intent(MyBroadcastReceiver.STANDARD_ACTION);
//                ComponentName componentName = new ComponentName(MainActivity.this, fullName);
//                intent.setComponent(componentName);
//                sendBroadcast(intent);
//                sendOrderedBroadcast(intent, null);

//                //动态标准发送
//                Intent intent = new Intent(MyBroadcastReceiver.STANDARD_ACTION);
//                sendBroadcast(intent);

                //本地动态标准发送
                Intent intent = new Intent(LocalReceiver.LOCAL_ACTION);
                localBroadcastManager.sendBroadcast(intent);
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unregisterReceiver(networkChangeReceiver);
//        unregisterReceiver(myBroadcastReceiver);
        localBroadcastManager.unregisterReceiver(localReceiver);
    }

    class LocalReceiver extends BroadcastReceiver{
        public static final String LOCAL_ACTION = "com.example.myapplication.LOCAL_BROADCAST";
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getAction().equals(LOCAL_ACTION)){
                Toast.makeText(context, "received in LOCAL_ACTION", Toast.LENGTH_SHORT).show();
            }
        }
    }

//    class NetworkChangeReceiver extends BroadcastReceiver {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            ConnectivityManager connectionManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//            NetworkInfo networkInfo = connectionManager.getActiveNetworkInfo();
//            if (networkInfo != null && networkInfo.isAvailable()){
//                Toast.makeText(context, "network is available", Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(context, "network is unavailable", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
}