package com.example.servicetest;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;

public class MyService extends Service {
    private DownloadBinder mBinder = new DownloadBinder();
    class DownloadBinder extends Binder{
        public void startDownload(){
            Log.d("MyService", "startDownload executed");
        }

        public int getProgress(){
            Log.d("MyService", "getProgress executed");
            return 0;
        }
    }

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MyService", "onCreate executed");
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);                               // 点击通知后, 触发Intent
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //高版本需要渠道
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            //只在Android O之上需要渠道, 这里的第一个参数要和下面的channelID一样
            NotificationChannel notificationChannel = new NotificationChannel("channel1", "name", NotificationManager.IMPORTANCE_HIGH);
            //如果这里用IMPORTANCE_NONE就需要在系统的设置里面开启渠道, 通知才能正常弹出
            manager.createNotificationChannel(notificationChannel);
        }
        //这里的第二个参数要和上面的第一个参数一样
        Notification notification = new NotificationCompat.Builder(this, "channel1")
                .setContentTitle("这是一个内容标题")
                .setContentText("This is content text")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setContentIntent(pi)
                .build();
        startForeground(1, notification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MyService", "onStartCommand executed");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyService", "onDestroy executed");
    }
}