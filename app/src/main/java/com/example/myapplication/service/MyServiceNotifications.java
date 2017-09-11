package com.example.myapplication.service;

import java.util.concurrent.TimeUnit;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.provider.MediaStore;

public class MyServiceNotifications extends Service {
    NotificationManager nm;

    @Override
    public void onCreate() {
        super.onCreate();
        nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sendNotif();
        return super.onStartCommand(intent, flags, startId);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    void sendNotif() {
        // 1-я часть
        Notification.Builder notif = new Notification.Builder(this);
        notif.setSmallIcon(android.R.drawable.ic_dialog_info);
        notif.setTicker("Text in status bar");
        notif.setWhen(System.currentTimeMillis());

        // 3-я часть
        Intent intent = new Intent(this, ServiceActivityNotifications.class);
        intent.putExtra(ServiceActivityNotifications.FILE_NAME, "somefile");
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);

        // 2-я часть
//        notif.setLatestEventInfo(this, "Notification's title", "Notification's text", pIntent);//не правильно!!!

        notif.setSound(Uri.withAppendedPath(MediaStore.Audio.Media.INTERNAL_CONTENT_URI, "6"));
        notif.setVibrate(new long[]{500, 1000});
        notif.setLights(Notification.DEFAULT_LIGHTS, 2, 2);

        notif.setNumber(3);

        notif.setContentTitle("Notification title");
        notif.setContentText("Notification text");
        notif.setContentIntent(pIntent);

        // ставим флаг, чтобы уведомление пропало после нажатия
//        notif.flags |= Notification.FLAG_AUTO_CANCEL;

        notif.setAutoCancel(true);
//        notif.build();!!!???

        // отправляем
        nm.notify(1, notif.getNotification());
    }

    public IBinder onBind(Intent arg0) {
        return null;
    }
}
