package com.android.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class splashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent iHome = new Intent(splashScreen.this,LoginActivity.class); //in new Intent() first one the current file and the second one the destination file.
                startActivity(iHome);
                finish(); //This is for not coming back to flash screen
            }
        },2500);
    }
}