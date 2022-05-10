package com.smartkid.dd.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.smartkid.dd.R;

public class SplashActivity extends AppCompatActivity {

    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        this.loadAnimation();

        Thread splashScreenStarter = new Thread() {
            public void run() {
                try {
                    int delay = 0;
                    while (delay < 2000) {
                        sleep(150);
                        delay = delay + 100;
                    }
                    startActivity(new Intent(SplashActivity.this, AuthActivity.class));

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    finish();
                }
            }

        };
        splashScreenStarter.start();
    }

    private void loadAnimation(){
        // Animation tv_title TextView
        img = (ImageView) findViewById(R.id.imageView);
        Animation animImg = AnimationUtils.loadAnimation(this, R.anim.bounce);
        animImg.setStartOffset(500);
        img.startAnimation(animImg);
    }
}