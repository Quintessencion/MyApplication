package com.example.myapplication.media;

import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.myapplication.R;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class SoundPoolActivity extends AppCompatActivity implements SoundPool.OnLoadCompleteListener {

    final String LOG_TAG = "myLogs";
    final int MAX_STREAMS = 5;

    SoundPool sp;
    int soundIdShot;
    int soundIdExplosion;


    int streamIDShot;
    int streamIDExplosion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_pool);

        sp = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);
        sp.setOnLoadCompleteListener(this);

        soundIdShot = sp.load(this, R.raw.shot, 1);
        soundIdExplosion = sp.load(this, R.raw.explosion, 1);
        Log.d(LOG_TAG, "soundIdShot = " + soundIdShot);

        try {
            soundIdExplosion = sp.load(getAssets().openFd("explosion.ogg"), 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d(LOG_TAG, "soundIdExplosion = " + soundIdExplosion);

    }

    public void onClick(View view) {
        streamIDShot = sp.play(soundIdShot, 1, 0, 0, 9, 1);
        streamIDExplosion = sp.play(soundIdExplosion, 0, 1, 0, 4, 1);

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        sp.setVolume(streamIDShot, 0, 1);
        sp.setVolume(streamIDExplosion, 1, 0);
    }

    @Override
    public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
        Log.d(LOG_TAG, "onLoadComplete, sampleId = " + sampleId + ", status = " + status);
    }

}