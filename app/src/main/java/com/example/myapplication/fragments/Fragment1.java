package com.example.myapplication.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.myapplication.R;

public class Fragment1 extends Fragment {
    //Fields
    final String LOG_TAG = "myLogs";

    //Functions
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(LOG_TAG, "Fragment1 onAttach");
    }

    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        Log.d(LOG_TAG, "Fragment1 onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View v = inflater.inflate(R.layout.activity_fragment1, null);

        Button button = (Button) v.findViewById(R.id.buttonFrag1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(LOG_TAG, "Button click on fragment1");
//                ((Button) getActivity().findViewById(R.id.btnFind)).setText("Access from Fragment 1");
            }
        });
        Log.d(LOG_TAG, "Fragment1 onCreateView");
        return v;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(LOG_TAG, "Fragment1 onActivityCreated");
    }

    public void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "Fragment1 onStart");
    }

    public void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "Fragment1 onResume");
    }

    public void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "Fragment1 onPause");
    }

    public void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "Fragment1 onStop");
    }

    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "Fragment1 onDestroy");
    }
}
