package com.example.myapplication.tranzaction;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;

public class SQLiteStatement extends AppCompatActivity implements View.OnClickListener {
    //Fields
    private static final String DB_NAME = "MyDB";
    private static final String TABLE_NAME = "MyTable";
    private SQLiteDatabase dataBase;
    TextView tvTime;
    Button btnInsert;
    String[] array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_statement);

        array = getResources().getStringArray(R.array.LIST);

        initDB();
        tvTime = (TextView) findViewById(R.id.tvTime);
        btnInsert = (Button) findViewById(R.id.btnInsert);
        btnInsert.setOnClickListener(this);
    }

    private void initDB() {
        dataBase = this.openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
        dataBase.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(FirstNumber INT, SecondNumber INT, Result INT);");
        dataBase.delete(TABLE_NAME, null, null);
    }

    @Override
    public void onClick(View v) {
        dataBase.delete(TABLE_NAME, null, null);
        long startTime = System.currentTimeMillis();
        insertRecords();
        long diff = System.currentTimeMillis() - startTime;
        tvTime.setText("Time: " + Long.toString(diff) + " ms");
    }

    private void insertRecords() {
        String sql = "INSERT INTO " + TABLE_NAME + " VALUES(?,?,?);";
        android.database.sqlite.SQLiteStatement statement = dataBase.compileStatement(sql);
        dataBase.beginTransaction();
        try {
            for (int i = 0; i < 1000; i++) {
                statement.clearBindings();
                statement.bindLong(1, i);
                statement.bindLong(2, i);
                statement.bindLong(3, i * i);
                statement.execute();
//                ContentValues cv = new ContentValues();
//                cv.put("FirstNumber", i);
//                cv.put("SecondNumber", i);
//                cv.put("Result", i * i);
//                dataBase.insert(TABLE_NAME, null, cv);
            }
            dataBase.setTransactionSuccessful();
        } finally {
            dataBase.endTransaction();
        }
    }

    @Override
    protected void onDestroy() {
        dataBase.close();
        super.onDestroy();
    }
}
