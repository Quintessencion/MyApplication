package com.example.myapplication.fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import com.example.myapplication.R;

public class FragmentMainActivity extends AppCompatActivity implements Fragment2.onSomeEventListener {
    //Fields
    final String LOG_TAG = "myLogs";
    Fragment1 frag1;
    Fragment2 frag2;
    FragmentTransaction ft;
    CheckBox chbStack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_main);
        Log.d(LOG_TAG, "FragmentActivity onCreate");

        frag1 = new Fragment1();
        frag2 = new Fragment2();

        chbStack = (CheckBox) findViewById(R.id.chbStack);

//        Fragment frag2 = new Fragment2();
//        FragmentTransaction ft = getFragmentManager().beginTransaction();
//        ft.add(R.id.fragment2, frag2);
//        ft.commit();
    }

    public void onClick(View v) {
        ft = getFragmentManager().beginTransaction();

        switch (v.getId()) {
            case R.id.btnAdd:
                ft.add(R.id.fragCont, frag1);
                break;
            case R.id.btnRemove:
                ft.remove(frag1);
                break;
            case R.id.btnReplace:
                ft.replace(R.id.fragCont, frag2);
                break;
        }
        if (chbStack.isChecked()) ft.addToBackStack(null);
        ft.commit();
    }

    public void someEvent(String s) {
//        Fragment frag1 = getFragmentManager().findFragmentById(R.id.fragment1);
//        ((TextView) frag1.getView().findViewById(R.id.textView1)).setText("Text from Fragment2 " + s);
//
//        Fragment frag2 = getFragmentManager().findFragmentById(R.id.fragment2);
//        ((TextView) frag2.getView().findViewById(R.id.textView2)).setText("Access to Fragment 2 from Activity");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "FragmentActivity onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "FragmentActivity onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "FragmentActivity onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "FragmentActivity onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "FragmentActivity onDestroy");
    }
}
