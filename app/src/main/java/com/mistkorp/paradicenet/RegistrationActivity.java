package com.mistkorp.paradicenet;

import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

public class RegistrationActivity extends AppCompatActivity {

    DBHelper dbHelper;
    EditText email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_registration);

        email = (EditText)findViewById(R.id.editText3);
        password = (EditText)findViewById(R.id.editText4);
        dbHelper = new DBHelper(this);
    }

    public void onClickRegistration(View view){
        ContentValues contentValues = new ContentValues();

        String emailText = email.getText().toString();
        String passwordText = password.getText().toString();

        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        contentValues.put("email", emailText);
        contentValues.put("password", passwordText);

        sqLiteDatabase.insert("users", null, contentValues);

        dbHelper.close();

        Intent intent = new Intent(RegistrationActivity.this, EnterActivity.class);
        startActivity(intent);
    }
}
