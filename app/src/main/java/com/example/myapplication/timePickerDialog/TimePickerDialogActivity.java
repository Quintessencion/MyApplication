package com.example.myapplication.timePickerDialog;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.myapplication.R;

public class TimePickerDialogActivity extends AppCompatActivity {
    //Fields
    int DIALOG_TIME = 1;
    int myHour = 14;
    int myMinute = 35;
    TextView tvTime;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_picker_dialog);
        tvTime = (TextView) findViewById(R.id.tvTime);
    }

    public void onclick(View view) {
        showDialog(DIALOG_TIME);
    }

    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_TIME) {
            TimePickerDialog tpd = new TimePickerDialog(this, myCallBack, myHour, myMinute, true);
            return tpd;
        }
        return super.onCreateDialog(id);
    }

    TimePickerDialog.OnTimeSetListener myCallBack = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            myHour = hourOfDay;
            myMinute = minute;
            tvTime.setText("Time is " + myHour + " hours " + myMinute + " minutes");
        }
    };
}
