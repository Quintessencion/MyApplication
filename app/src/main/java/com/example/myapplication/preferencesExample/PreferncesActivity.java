package com.example.myapplication.preferencesExample;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.myapplication.R;

public class PreferncesActivity extends AppCompatActivity {

    TextView tvInfo;
    SharedPreferences sp;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prefernces);

        tvInfo = (TextView) findViewById(R.id.tvInfo);

        // получаем SharedPreferences, которое работает с файлом настроек
        sp = PreferenceManager.getDefaultSharedPreferences(this);
        // полная очистка настроек
        // sp.edit().clear().commit();
    }

    protected void onResume() {
        String listValue = sp.getString("list", "не выбрано");
        boolean b = sp.getBoolean("chb3", false);
        tvInfo.setText("Значение списка - " + listValue + " значение chb3 " + b);
//        Boolean notif = sp.getBoolean("notif", false);
//        String address = sp.getString("address", "");
//        String text = "Notifications are " + ((notif) ? "enabled, address = " + address : "disabled");
//        tvInfo.setText(text);
        super.onResume();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem mi = menu.add(0, 1, 0, "Preferences");
        mi.setIntent(new Intent(this, PrefActivity.class));
        return super.onCreateOptionsMenu(menu);
    }
}