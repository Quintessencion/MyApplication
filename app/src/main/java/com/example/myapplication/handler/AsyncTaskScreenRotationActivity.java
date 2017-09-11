package com.example.myapplication.handler;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.concurrent.TimeUnit;

public class AsyncTaskScreenRotationActivity extends AppCompatActivity {

    final String LOG_TAG = "myLogs";

    MyTask mt;
    TextView tv;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task_screen_rotation);
        Log.d(LOG_TAG, "create MainActivity: " + this.hashCode());

        tv = (TextView) findViewById(R.id.tv);

        mt = (MyTask) getLastCustomNonConfigurationInstance();
        if (mt == null) {
            mt = new MyTask();
            mt.execute();
        }
        mt.link(this);

        Log.d(LOG_TAG, "create MyTask: " + mt.hashCode());
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        mt.unLink();
        return mt;
    }

    static class MyTask extends AsyncTask<String, Integer, Void> {
        AsyncTaskScreenRotationActivity atsra;

        void link(AsyncTaskScreenRotationActivity act) {
            atsra = act;
        }

        void unLink() {
            atsra = null;
        }


        @Override
        protected Void doInBackground(String... params) {
            try {
                for (int i = 1; i <= 10; i++) {
                    TimeUnit.SECONDS.sleep(1);
                    publishProgress(i);
                    Log.d("myLogs", "i = " + i
                            + ", MyTask: " + this.hashCode()
                            + ", AsyncTaskScreenRotationActivity: " + atsra.hashCode());
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            atsra.tv.setText("i = " + values[0]);
        }
    }
}