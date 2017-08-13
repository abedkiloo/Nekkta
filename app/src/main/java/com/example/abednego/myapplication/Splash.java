package com.example.abednego.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class Splash extends AppCompatActivity {
    private AppCompatImageView splash_image;
    Animation anim_rotate, slide_right,slide_left, anim_fade_in, anim_fade_out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //remove action bar
        getSupportActionBar().hide();

        splash_image = (AppCompatImageView) findViewById(R.id.launch_splash_icon);
        /**
         *   load all animation references
         */
        anim_rotate = AnimationUtils.loadAnimation(getBaseContext(), R.anim.rotate);
        slide_right = AnimationUtils.loadAnimation(getBaseContext(), R.anim.slide_right);
        slide_left = AnimationUtils.loadAnimation(getBaseContext(), R.anim.slide_left);
        anim_fade_in = AnimationUtils.loadAnimation(getBaseContext(), R.anim.fade_in);
        anim_fade_out = AnimationUtils.loadAnimation(getBaseContext(), R.anim.fade_out);

        /**
         * start animating the splash screen
         */
        splash_image.startAnimation(anim_fade_in);
        anim_fade_in.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                splash_image.startAnimation(anim_fade_out);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        anim_fade_out.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Splash.this.finish();
                startActivity(new Intent(getApplicationContext(), RegisterUser.class));
                overridePendingTransition(R.anim.slide_right,R.anim.slide_right);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
