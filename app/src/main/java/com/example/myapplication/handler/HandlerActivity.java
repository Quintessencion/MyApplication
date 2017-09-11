package com.example.myapplication.handler;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TimeUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.concurrent.TimeUnit;

public class HandlerActivity extends AppCompatActivity {

    //Fields
    final String LOG_TAG = "myLogs";

    Handler h;
    TextView tvInfo;
    Button btnStart;


    //Functions
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_handler);
//
//        tvInfo = (TextView) findViewById(R.id.tvInfo);
//    }

//    public void onclick(View v) {
//        switch (v.getId()) {
//            case R.id.btnStart:
//                for (int i = 1; i <= 10; i++) {
//                    // долгий процесс
//                    downloadFile();
//                    // обновляем TextView
//                    tvInfo.setText("Закачано файлов: " + i);
//                    // пишем лог
//                    Log.d(LOG_TAG, "Закачано файлов: " + i);
//                }
//                break;
//            case R.id.btnTest:
//                Log.d(LOG_TAG, "test");
//                break;
//            default:
//                break;
//        }
//    }

    //    public void onclick(View v) {
//        switch (v.getId()) {
//            case R.id.btnStart:
//                Thread t = new Thread(new Runnable() {
//                    public void run() {
//                        for (int i = 1; i <= 10; i++) {
//                            // долгий процесс
//                            downloadFile();
//                            // обновляем TextView
//                            tvInfo.setText("Закачано файлов: " + i);
//                            // пишем лог
//                            Log.d(LOG_TAG, "i = " + i);
//                        }
//                    }
//                });
//                t.start();
//                break;
//            case R.id.btnTest:
//                Log.d(LOG_TAG, "test");
//                break;
//            default:
//                break;
//        }
//    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        tvInfo = (TextView) findViewById(R.id.tvInfo);
        btnStart = (Button) findViewById(R.id.btnStart);

        h = new Handler() {
            public void handleMessage(android.os.Message msg) {
                // обновляем TextView
                tvInfo.setText("Закачано файлов: " + msg.what);
                if (msg.what == 10) btnStart.setEnabled(true);
            }
        };
    }

    public void onclick(View v) {
        switch (v.getId()) {
            case R.id.btnStart:
//                btnStart.setEnabled(false);
                Thread t = new Thread(new Runnable() {
                    public void run() {
                        for (int i = 1; i <= 10; i++) {
                            // долгий процесс
                            downloadFile();
                            h.sendEmptyMessage(i);
                            // пишем лог
                            Log.d(LOG_TAG, "i = " + i);
                        }
                    }
                });
                t.start();
                break;
            case R.id.btnTest:
                Log.d(LOG_TAG, "test");
                break;
            default:
                break;
        }
    }

    void downloadFile() {
        // пауза - 1 секунда
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}