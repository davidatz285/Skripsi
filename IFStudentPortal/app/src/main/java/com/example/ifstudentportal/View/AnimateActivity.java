package com.example.ifstudentportal.View;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ifstudentportal.R;

public class AnimateActivity extends AppCompatActivity {
    private ImageView logo;
    private final static int delay = 3000;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startup_animate_activity);
        logo=(ImageView)findViewById(R.id.logo);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(AnimateActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        }, delay);

        Animation myanim = AnimationUtils.loadAnimation(this,R.anim.my_animation);
        logo.startAnimation(myanim);
    }
}
