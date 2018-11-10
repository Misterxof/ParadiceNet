package com.mistkorp.paradicenet;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

public class EnterActivity extends AppCompatActivity{

    final String LOG_TAG = "myLogs";

    DBHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;
    EditText email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_enter);

        email = (EditText)findViewById(R.id.editText);
        password = (EditText)findViewById(R.id.editText2);
        dbHelper = new DBHelper(this);
    }


    public void onClickEnter(View view){
        Cursor c = null;
        sqLiteDatabase = dbHelper.getWritableDatabase();

        String selection = "email like '" + email.getText().toString() +"' and password like '" + password.getText().toString() + "'";
        c = sqLiteDatabase.query("users", null, selection, null, null, null, null);

        if (c != null){
            if (c.moveToFirst()) {
                String str;
                do {
                    str = "";
                    for (String cn : c.getColumnNames()) {
                        str = str.concat(cn + " = "
                                + c.getString(c.getColumnIndex(cn)) + "; ");
                    }
                    Log.d(LOG_TAG, str);

                } while (c.moveToNext());
                Intent intent = new Intent(EnterActivity.this, MainActivity.class);
                startActivity(intent);
            }
            c.close();
        } else
            Log.d(LOG_TAG, "Cursor is null");




        dbHelper.close();
    }

    public void onClickRegistration(View view){
        Intent intent = new Intent(EnterActivity.this, RegistrationActivity.class);
        startActivity(intent);
    }

}
