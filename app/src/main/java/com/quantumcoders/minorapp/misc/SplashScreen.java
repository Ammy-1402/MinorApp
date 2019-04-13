package com.quantumcoders.minorapp.misc;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.quantumcoders.minorapp.R;
import com.quantumcoders.minorapp.activities.MainActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(()->{
            startActivity(new Intent(this, MainActivity.class));
        },2000);
    }
}
