package com.example.myapplication.service;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.R;

public class ServiceActivityIntentServiceForeground extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_intent_service_foreground);
    }

    public void onClickStart(View v) {
        startService(new Intent(this, MyServiceIntentServiceForeground.class).putExtra("time", 3).putExtra("label", "Call 1"));
        startService(new Intent(this, MyServiceIntentServiceForeground.class).putExtra("time", 1).putExtra("label", "Call 2"));
        startService(new Intent(this, MyServiceIntentServiceForeground.class).putExtra("time", 4).putExtra("label", "Call 3"));
    }
}
