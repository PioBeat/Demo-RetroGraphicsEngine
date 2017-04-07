package net.offbeatpioneer.demoapp.retrographicsengine.examples.splashscreenCombined;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import net.offbeatpioneer.demoapp.R;
import net.offbeatpioneer.demoapp.retrographicsengine.ResManager;
import net.offbeatpioneer.retroengine.core.RetroEngine;
import net.offbeatpioneer.retroengine.view.DrawView;
import net.offbeatpioneer.retroengine.view.GameThread;

public class SplashScreenExample extends AppCompatActivity {

    GameThread gameThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen_example);

        ResManager.initImages(getResources());

        DrawView drawView = (DrawView) findViewById(R.id.graphics);
        gameThread = new GameThread(drawView);
        gameThread.addStates(new SplashScreenAnimationState());
        gameThread.setCurrentState(SplashScreenAnimationState.class);

        Animation scaleAlpha = AnimationUtils.loadAnimation(this, R.anim.scalealpha);
        ImageView circle1 = (ImageView) findViewById(R.id.image_circle_1);
        circle1.startAnimation(scaleAlpha);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        RetroEngine.isRunning = false;
//        try {
//            gameThread.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (gameThread != null) {
            RetroEngine.shouldWait = false;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (gameThread != null) {
            RetroEngine.isRunning = true;
        }
    }
}
