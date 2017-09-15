package com.example.myapplication.actionMode;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.myapplication.R;

public class ActionModeActivity extends AppCompatActivity {

    ActionMode actionMode;
    ListView lvActionMode;
    final String LOG_TAG = "myLogs";

    String[] data = {"one", "two", "three", "four", "five"};

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_mode);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_activated_1, data);
        lvActionMode = (ListView) findViewById(R.id.lvActionMode);
        lvActionMode.setAdapter(adapter);
        lvActionMode.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        lvActionMode.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                mode.getMenuInflater().inflate(R.menu.context, menu);
                return true;
            }

            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                mode.finish();
                return false;
            }

            public void onDestroyActionMode(ActionMode mode) {
            }

            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                Log.d(LOG_TAG, "position = " + position + ", checked = " + checked);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}