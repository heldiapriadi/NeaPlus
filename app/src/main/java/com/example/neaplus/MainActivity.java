package com.example.neaplus;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.neaplus.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private boolean onsearch = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActionBar supportActionBar = this.getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.hide();
        }
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_bookmark)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    public void button_click(View view) throws InterruptedException {
        if(onsearch == false){
            YoYo.with(Techniques.FadeOut)
                    .duration(500)
                    .playOn(findViewById(R.id.text_news));
            YoYo.with(Techniques.SlideInLeft)
                    .duration(500)
                    .playOn(findViewById(R.id.view_search));
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    findViewById(R.id.text_news).setVisibility(View.GONE);
                    findViewById(R.id.view_search).setVisibility(View.VISIBLE);
                }
            }, 500);
            onsearch = true;
        }else{
            YoYo.with(Techniques.FadeIn)
                    .duration(500)
                    .playOn(findViewById(R.id.text_news));
            YoYo.with(Techniques.SlideOutUp)
                    .duration(500)
                    .playOn(findViewById(R.id.view_search));
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    findViewById(R.id.text_news).setVisibility(View.VISIBLE);
                    findViewById(R.id.view_search).setVisibility(View.GONE);
                }
            }, 500);
            onsearch = false;
        }
    }
    public void click1(View view){
        findViewById(R.id.button1).setBackgroundColor(Color.RED);
        findViewById(R.id.button2).setBackgroundColor(Color.WHITE);
        findViewById(R.id.button3).setBackgroundColor(Color.WHITE);
        findViewById(R.id.button4).setBackgroundColor(Color.WHITE);
        findViewById(R.id.button5).setBackgroundColor(Color.WHITE);
    }
    public void click2(View view){
        findViewById(R.id.button1).setBackgroundColor(Color.WHITE);
        findViewById(R.id.button2).setBackgroundColor(Color.RED);
        findViewById(R.id.button3).setBackgroundColor(Color.WHITE);
        findViewById(R.id.button4).setBackgroundColor(Color.WHITE);
        findViewById(R.id.button5).setBackgroundColor(Color.WHITE);
    }
    public void click3(View view){
        findViewById(R.id.button1).setBackgroundColor(Color.WHITE);
        findViewById(R.id.button2).setBackgroundColor(Color.WHITE);
        findViewById(R.id.button3).setBackgroundColor(Color.RED);
        findViewById(R.id.button4).setBackgroundColor(Color.WHITE);
        findViewById(R.id.button5).setBackgroundColor(Color.WHITE);
    }
    public void click4(View view){
        findViewById(R.id.button1).setBackgroundColor(Color.WHITE);
        findViewById(R.id.button2).setBackgroundColor(Color.WHITE);
        findViewById(R.id.button3).setBackgroundColor(Color.WHITE);
        findViewById(R.id.button4).setBackgroundColor(Color.RED);
        findViewById(R.id.button5).setBackgroundColor(Color.WHITE);
    }
    public void click5(View view){
        findViewById(R.id.button1).setBackgroundColor(Color.WHITE);
        findViewById(R.id.button2).setBackgroundColor(Color.WHITE);
        findViewById(R.id.button3).setBackgroundColor(Color.WHITE);
        findViewById(R.id.button4).setBackgroundColor(Color.WHITE);
        findViewById(R.id.button5).setBackgroundColor(Color.RED);
    }
}