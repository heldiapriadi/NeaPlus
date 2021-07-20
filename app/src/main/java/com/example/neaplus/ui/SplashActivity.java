package com.example.neaplus.ui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.neaplus.R;


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActionBar supportActionBar = this.getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.hide();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        findViewById(R.id.imageView).setVisibility(View.VISIBLE);
        YoYo.with(Techniques.Flash)
                .duration(800)
                .playOn(findViewById(R.id.imageView));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                YoYo.with(Techniques.Tada)
                        .duration(1000)
                        .playOn(findViewById(R.id.imageView));
            }
        }, 1500);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                findViewById(R.id.textView2).setVisibility(View.VISIBLE);
                YoYo.with(Techniques.FadeInUp)
                        .duration(1000)
                        .playOn(findViewById(R.id.textView2));
            }
        }, 1500);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }
        }, 3000);
    }
}