package com.example.myapplication.handler;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class HandlerActivity2 extends AppCompatActivity {

    final String LOG_TAG = "myLogs";

    final int STATUS_NONE = 0; // нет подключения
    final int STATUS_CONNECTING = 1; // подключаемся
    final int STATUS_CONNECTED = 2; // подключено
    final int STATUS_DOWNLOAD_START = 3; // загрузка началась
    final int STATUS_DOWNLOAD_FILE = 4; // файл загружен
    final int STATUS_DOWNLOAD_END = 5; // загрузка закончена
    final int STATUS_DOWNLOAD_NONE = 6; // нет файлов для загрузки

    Handler h;

    TextView tvStatus;
    ProgressBar pbDownload;
    Button btnConnect;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler2);

        tvStatus = (TextView) findViewById(R.id.tvStatus);
        pbDownload = (ProgressBar) findViewById(R.id.pbConnect);
        btnConnect = (Button) findViewById(R.id.btnConnect);

        h = new Handler() {
            public void handleMessage(android.os.Message msg) {
                switch (msg.what) {
                    case STATUS_NONE:
                        btnConnect.setEnabled(true);
                        tvStatus.setText("Not connected");
                        pbDownload.setVisibility(View.GONE);
                        break;
                    case STATUS_CONNECTING:
                        btnConnect.setEnabled(false);
                        tvStatus.setText("Connecting");
                        break;
                    case STATUS_CONNECTED:
                        tvStatus.setText("Connected");
                        break;
                    case STATUS_DOWNLOAD_START:
                        tvStatus.setText("Start download " + msg.arg1 + " files");
                        pbDownload.setMax(msg.arg1);
                        pbDownload.setProgress(0);
                        pbDownload.setVisibility(View.VISIBLE);
                        break;
                    case STATUS_DOWNLOAD_FILE:
                        tvStatus.setText("Downloading. Left " + msg.arg2 + " files");
                        pbDownload.setProgress(msg.arg1);
                        saveFile((byte[]) msg.obj);
                        break;
                    case STATUS_DOWNLOAD_END:
                        tvStatus.setText("Download complete!");
                        break;
                    case STATUS_DOWNLOAD_NONE:
                        tvStatus.setText("No files for download");
                        break;
                }
            }
        };
        h.sendEmptyMessage(STATUS_NONE);
    }

    public void onclick(View v) {

        Thread t = new Thread(new Runnable() {
            Message msg;
            byte[] file;
            Random rand = new Random();

            public void run() {
                try {
                    // устанавливаем подключение
                    h.sendEmptyMessage(STATUS_CONNECTING);
                    TimeUnit.SECONDS.sleep(1);

                    // подключение установлено
                    h.sendEmptyMessage(STATUS_CONNECTED);

                    // определяем кол-во файлов
                    TimeUnit.SECONDS.sleep(1);
                    int filesCount = rand.nextInt(5);

                    if (filesCount == 0) {
                        // сообщаем, что файлов для загрузки нет
                        h.sendEmptyMessage(STATUS_DOWNLOAD_NONE);
                        // и отключаемся
                        TimeUnit.MILLISECONDS.sleep(1500);
                        h.sendEmptyMessage(STATUS_NONE);
                        return;
                    }

                    // загрузка начинается
                    // создаем сообщение, с информацией о количестве файлов
                    msg = h.obtainMessage(STATUS_DOWNLOAD_START, filesCount, 0);
                    // отправляем
                    h.sendMessage(msg);

                    for (int i = 1; i <= filesCount; i++) {
                        // загружается файл
                        file = downloadFile();
                        // создаем сообщение с информацией о порядковом номере
                        // файла,
                        // кол-вом оставшихся и самим файлом
                        msg = h.obtainMessage(STATUS_DOWNLOAD_FILE, i,
                                filesCount - i, file);
                        // отправляем
                        h.sendMessage(msg);
                    }

                    // загрузка завершена
                    h.sendEmptyMessage(STATUS_DOWNLOAD_END);

                    // отключаемся
                    TimeUnit.MILLISECONDS.sleep(1500);
                    h.sendEmptyMessage(STATUS_NONE);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }

    byte[] downloadFile() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        return new byte[1024];
    }

    void saveFile(byte[] file) {

    }
}