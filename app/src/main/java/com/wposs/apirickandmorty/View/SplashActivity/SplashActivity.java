package com.wposs.apirickandmorty.View.SplashActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import com.wposs.apirickandmorty.Base.App;
import com.wposs.apirickandmorty.R;
import com.wposs.apirickandmorty.View.Home.Implementations.MainActivity;
import android.os.Handler;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends App {
    private static final int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                changeActivity(MainActivity.class);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}