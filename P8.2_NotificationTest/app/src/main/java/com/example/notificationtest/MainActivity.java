package com.example.notificationtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button sendNotice = (Button) findViewById(R.id.send_notice);
        sendNotice.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String channel1 = "channel_1";
        switch (view.getId()){
            case R.id.send_notice:
                Intent intent = new Intent(this, NotificationActivity.class);
                PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);

                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                //高版本需要渠道
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    //只在Android O之上需要渠道, 这里的第一个参数要和下面的channelID一样
                    NotificationChannel notificationChannel = new NotificationChannel(channel1, "name", NotificationManager.IMPORTANCE_HIGH);
                    //如果这里用IMPORTANCE_NONE就需要在系统的设置里面开启渠道, 通知才能正常弹出
                    manager.createNotificationChannel(notificationChannel);
                }
                //这里的第二个参数要和上面的第一个参数一样
                Notification notification = new NotificationCompat.Builder(this, channel1)
                        .setContentTitle("这是一个内容标题")
                        .setContentText("This is content text")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                        .setContentIntent(pi)
                        .setAutoCancel(true)
                        .setSound(Uri.fromFile(new File("/system/media/audio/ringtones/luna.ogg")))
                        .setVibrate(new long[] {0, 1000, 1000, 1000})
                        .setLights(Color.GREEN, 1000, 1000)
                        .setStyle(new NotificationCompat.BigTextStyle().bigText(("Learn how to build" +
                                "notification, send and sync data, and use voice actions. Get the official" +
                                "Android IDE and developer tools to build apps for Android.")))
                        .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(
                                BitmapFactory.decodeResource(getResources(), R.drawable.big_image)))
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .build();
                manager.notify(1, notification);
                break;
            default:
                break;
        }
    }
}