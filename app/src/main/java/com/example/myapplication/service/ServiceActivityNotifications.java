package com.example.myapplication.service;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.R;

public class ServiceActivityNotifications extends AppCompatActivity {

    public final static String FILE_NAME = "filename";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_notifications);

        TextView tv = (TextView) findViewById(R.id.tv);

        Intent intent = getIntent();

        String fileName = intent.getStringExtra(FILE_NAME);
        if (!TextUtils.isEmpty(fileName))
            tv.setText(fileName);
    }

    public void onClickStart(View v) {
        startService(new Intent(this, MyServiceNotifications.class));
    }


    public void onClickStop(View v) {
        stopService(new Intent(this, MyServiceNotifications.class));
    }
}