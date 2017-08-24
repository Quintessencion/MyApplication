package com.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.*;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //Fields
    final int MENU_ALPHA_ID = 1;
    final int MENU_SCALE_ID = 2;
    final int MENU_TRANSLATE_ID = 3;
    final int MENU_ROTATE_ID = 4;
    final int MENU_COMBO_ID = 5;

    final int REQUEST_CODE_COLOR = 1;
    final int REQUEST_CODE_ALIGN = 2;

    TextView editText;
    Button btnColor;
    Button btnAligment;

    //Functions
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        editText = (TextView) findViewById(R.id.editText);
        btnColor = (Button) findViewById(R.id.btnColor);
        btnAligment = (Button) findViewById(R.id.btnAligment);
        btnColor.setOnClickListener(this);
        btnAligment.setOnClickListener(this);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        switch (v.getId()) {
            case R.id.editText:
                menu.add(0, MENU_ALPHA_ID, 0, "Alpha");
                menu.add(0, MENU_SCALE_ID, 0, "Scale");
                menu.add(0, MENU_TRANSLATE_ID, 0, "Translate");
                menu.add(0, MENU_ROTATE_ID, 0, "Rotate");
                menu.add(0, MENU_COMBO_ID, 0, "Combo");
                break;
        }
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Animation animation = null;

        switch (item.getItemId()) {
            case MENU_ALPHA_ID:
                animation = AnimationUtils.loadAnimation(this, R.anim.myalpha);
                Toast.makeText(this, "Alpha", Toast.LENGTH_SHORT).show();
                break;
            case MENU_SCALE_ID:
                animation = AnimationUtils.loadAnimation(this, R.anim.myscale);
                break;
            case MENU_TRANSLATE_ID:
                animation = AnimationUtils.loadAnimation(this, R.anim.mytrans);
                break;
            case MENU_ROTATE_ID:
                animation = AnimationUtils.loadAnimation(this, R.anim.myrotate);
                break;
            case MENU_COMBO_ID:
                animation = AnimationUtils.loadAnimation(this, R.anim.mycombo);
                break;
        }

//        textView.startAnimation(animation);
        return super.onContextItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Intent intent;

        switch (v.getId()) {
            case R.id.btnColor:
                intent = new Intent(this, SecondActivity.class);
                startActivityForResult(intent, REQUEST_CODE_COLOR);
                break;
            case R.id.btnAligment:
                intent = new Intent(this, ThirdActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ALIGN);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_COLOR:
                    int color = data.getIntExtra("color", Color.WHITE);
                    editText.setTextColor(color);
                    break;
                case REQUEST_CODE_ALIGN:
                    int align = data.getIntExtra("alignment", Gravity.LEFT);
                    editText.setGravity(align);
                    break;
            }
        } else {
            Toast.makeText(this, "Wrong result " + resultCode, Toast.LENGTH_SHORT).show();
        }
    }
}

