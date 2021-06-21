package com.example.waterleveltracker;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private ImageView logoImage;
    private TextView txtView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        logoImage = findViewById(R.id.logo);
        txtView = findViewById(R.id.textView);


        Animation animation = AnimationUtils.loadAnimation(this, R.anim.top_to_down_anim);
        logoImage.setAnimation(animation);

        Animation animation1 = AnimationUtils.loadAnimation(this, R.anim.down_to_top_anim);
        txtView.setAnimation(animation1);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        },2500);



    }
}