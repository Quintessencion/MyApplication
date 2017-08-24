package com.example.myapplication.sql;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.myapplication.R;

public class SQL extends AppCompatActivity implements View.OnClickListener {
    //Fields
    EditText etId, etName, etMail;
    Button btnUpdate, btnDelete, btnAdd, btnRead, btnClear;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql);

        initViews();
        dbHelper = new DBHelper(this);
    }

    private void initViews() {
        etId = (EditText) findViewById(R.id.etId);
        etName = (EditText) findViewById(R.id.etName);
        etMail = (EditText) findViewById(R.id.etMail);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(this);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(this);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
        btnRead = (Button) findViewById(R.id.btnRead);
        btnRead.setOnClickListener(this);
        btnClear = (Button) findViewById(R.id.btnClear);
        btnClear.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String id = etId.getText().toString();
        String name = etName.getText().toString();
        String mail = etMail.getText().toString();

        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        switch (v.getId()) {
            case R.id.btnUpdate:
                if (id.equalsIgnoreCase("")) {
                    break;
                }
                contentValues.put(DBHelper.KEY_NAME, name);
                contentValues.put(DBHelper.KEY_MAIL, mail);
                int updCount = database.update(DBHelper.TABLE_CONTACTS, contentValues,
                        DBHelper.KEY_ID + "= ?", new String[]{id});
                Log.d("mLog", "updates rows count - " + updCount);
                break;
            case R.id.btnDelete:
                if (id.equalsIgnoreCase("")) {
                    break;
                }
                int delCount = database.delete(DBHelper.TABLE_CONTACTS,
                        DBHelper.KEY_ID + "=" + id, null);
                Log.d("mLog", "deleted rows count - " + delCount);
                break;
            case R.id.btnAdd:
                contentValues.put(DBHelper.KEY_NAME, name);
                contentValues.put(DBHelper.KEY_MAIL, mail);

                database.insert(DBHelper.TABLE_CONTACTS, null, contentValues);
                break;
            case R.id.btnRead:
                Cursor cursor = database.query(DBHelper.TABLE_CONTACTS,
                        null, null, null, null, null, null);
                if (cursor.moveToFirst()) {
                    int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
                    int nameIndex = cursor.getColumnIndex(DBHelper.KEY_NAME);
                    int emailIndex = cursor.getColumnIndex(DBHelper.KEY_MAIL);
                    do {
                        Log.d("mLog", "ID = " + cursor.getInt(idIndex) +
                                ", name = " + cursor.getString(nameIndex) +
                                ", email = " + cursor.getString(emailIndex));
                    } while (cursor.moveToNext());
                } else {
                    Log.d("mLog", "0 rows");
                }
                cursor.close();
                break;
            case R.id.btnClear:
                database.delete(DBHelper.TABLE_CONTACTS, null, null);
                break;
        }
        dbHelper.close();
    }
}
