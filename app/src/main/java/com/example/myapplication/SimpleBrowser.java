package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SimpleBrowser extends AppCompatActivity implements View.OnClickListener {
    //Fields
    Button btnWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_browser);

        initViews();
    }

    private void initViews() {
        findViewById(R.id.btnWeb).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://fandroid.info")));
    }
}
