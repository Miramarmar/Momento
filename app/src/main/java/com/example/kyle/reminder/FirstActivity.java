package com.example.kyle.reminder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        Thread th = new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    Intent intent = new Intent(FirstActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        th.start();
    }
}
