package com.minkov.notificationsdemo;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.minkov.notificationsdemo.services.BackgroundService;
import com.minkov.notificationsdemo.ui.NotificationsFactory;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private NotificationsFactory notificationsFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tv = (TextView) this.findViewById(R.id.tv);
        notificationsFactory = new NotificationsFactory(this);
       Intent intent = new Intent(this, NextActivity.class);
//        Notification notification = notificationsFactory.notify(
//                "NEW title",
//                new Date().toString(),
//                R.drawable.ic_superhero_notification);

        notificationsFactory.notifyProgress(
                "NEW title",
                new Date().toString(),
                R.drawable.ic_superhero_notification,
                intent);


        ScheduledExecutorService scheduler =
                Executors.newSingleThreadScheduledExecutor();

        scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv.setText(new Date().getTime() + "");
//                        notificationsFactory.notify(
//                                "Update",
//                                new Date().getTime() + "",
//                                R.drawable.ic_superhero_notification);
                    }
                });

            }
        }, 0, 150, TimeUnit.MILLISECONDS);


        Intent serviceIntent = new Intent(this, BackgroundService.class);

        serviceIntent.putExtra("index", 1);

        startService(serviceIntent);

        //simple
        final NotificationCompat.Builder builder=new NotificationCompat.Builder(this)
                .setContentTitle("Simple notification")
                .setContentText("Notification content")
                .setSmallIcon(R.drawable.ic_superhero_notification);

        final NotificationManager manager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        Observable.just(5)
                .delay(3, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        manager.notify(2, builder.build());
                    }
                });
    }
}
