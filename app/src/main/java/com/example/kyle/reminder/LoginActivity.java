package com.example.kyle.reminder;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import shem.com.materiallogin.DefaultLoginView;
import shem.com.materiallogin.DefaultRegisterView;
import shem.com.materiallogin.MaterialLoginView;

public class LoginActivity extends AppCompatActivity {

    protected SQLiteDatabase mDb = null;
    ReminderDataHelper dbHelper;

    public static final String TABLE_NAME = "user";
    public static final String NAME = "name";
    public static final String PWD = "pwd";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        dbHelper = new ReminderDataHelper(this);
        mDb = dbHelper.getWritableDatabase();

        final MaterialLoginView login = (MaterialLoginView) findViewById(R.id.login);
        ((DefaultLoginView)login.getLoginView()).setListener(new DefaultLoginView.DefaultLoginViewListener() {
            @Override
            public void onLogin(TextInputLayout loginUser, TextInputLayout loginPass) {
                String userName = loginUser.getEditText().getText().toString();
                String userPass = loginPass.getEditText().getText().toString();
                User user;
                user  = selectionner(userName, userPass);
                if(user == null){
                    Toast.makeText(LoginActivity.this, "Error ", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(LoginActivity.this, "successful ", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("USER", user);
                    startActivity(intent);
                    finish();
                }

            }
        });

        ((DefaultRegisterView)login.getRegisterView()).setListener(new DefaultRegisterView.DefaultRegisterViewListener() {
            @Override
            public void onRegister(TextInputLayout registerUser, TextInputLayout registerPass, TextInputLayout registerPassRep) {
                String userName = registerUser.getEditText().getText().toString();
                String userPass = registerPass.getEditText().getText().toString();
                String repeatPass = registerPassRep.getEditText().getText().toString();
                User user;
                if((userName.isEmpty()) || (!userPass.equals(repeatPass)) || userPass.isEmpty()){
                    Toast.makeText(LoginActivity.this, "All fields are required ", Toast.LENGTH_LONG).show();
                }else {

                    //db.open();
                    user  = selectionnerName(userName);
                    if(user != null){
                        Toast.makeText(LoginActivity.this, "username already exists ", Toast.LENGTH_LONG).show();
                    }else {
                        user = new User(userName, userPass);
                        ajouter(user);
                        Toast.makeText(LoginActivity.this, "successful ", Toast.LENGTH_LONG).show();
                        mDb.close();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("USER", user);
                        startActivity(intent);
                        finish();
                    }

                }
            }
        });

    }


    public void ajouter(User user) {

        ContentValues value = new ContentValues();
        value.put(NAME ,  user.getName());
        value.put(PWD ,  user.getPwd());

        mDb.insert(TABLE_NAME, null, value);
    }

    public User selectionner(String name, String pwd) {

        Cursor cursor = mDb.rawQuery("select * from " + TABLE_NAME + " where name = ? and pwd = ?", new String[]{name, pwd});

        User user  = null;
        while (cursor.moveToNext()) {

            long ids = cursor.getLong(0);
            String nom = cursor.getString(1);
            String pas = cursor.getString(2);

            user = new User(nom, pas);
        }
        cursor.close();

        return user;

    }

    public User selectionnerName(String name) {

        Cursor cursor = mDb.rawQuery("select * from " + TABLE_NAME + " where name = ? ", new String[]{name});

        User user  = null;
        while (cursor.moveToNext()) {

            long ids = cursor.getLong(0);
            String nom = cursor.getString(1);
            String pas = cursor.getString(2);

            user = new User(nom, pas);
        }
        cursor.close();

        return user;

    }
}
