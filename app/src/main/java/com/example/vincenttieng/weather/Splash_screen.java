package com.example.vincenttieng.weather;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import static java.lang.Thread.sleep;

public class Splash_screen extends AppCompatActivity{
    private static int Splash_TIME_OUT = 1000; //Wait in ms
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash_screen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        },Splash_TIME_OUT);
    }
}