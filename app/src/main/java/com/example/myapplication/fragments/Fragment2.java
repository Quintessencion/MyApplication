package com.example.myapplication.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.myapplication.R;

public class Fragment2 extends Fragment {
    //Fields
    final String LOG_TAG = "myLogs";
    onSomeEventListener onSomeEventListener;

    public interface onSomeEventListener {
        public void someEvent(String s);
    }

    //Functions
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(LOG_TAG, "Fragment2 onAttach");

        try {
            onSomeEventListener = (onSomeEventListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implements onSomeEventListener");
        }
    }

    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        Log.d(LOG_TAG, "Fragment2 onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View v = inflater.inflate(R.layout.activity_fragment2, null);

        Button button = (Button) v.findViewById(R.id.buttonFrag2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(LOG_TAG, "Button click on fragment2");
                onSomeEventListener.someEvent("Test text to Fragment1");
            }
        });
        Log.d(LOG_TAG, "Fragment2 onCreateView");
        return v;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(LOG_TAG, "Fragment2 onActivityCreated");
    }

    public void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "Fragment2 onStart");
    }

    public void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "Fragment2 onResume");
    }

    public void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "Fragment2 onPause");
    }

    public void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "Fragment2 onStop");
    }

    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "Fragment2 onDestroy");
    }
}
