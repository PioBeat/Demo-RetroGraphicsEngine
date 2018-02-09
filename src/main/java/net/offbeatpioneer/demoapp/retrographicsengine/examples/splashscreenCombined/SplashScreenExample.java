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
import net.offbeatpioneer.retroengine.view.RenderThread;

public class SplashScreenExample extends AppCompatActivity {

    RenderThread renderThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen_example);

        ResManager.initImages(getResources());

        DrawView drawView = (DrawView) findViewById(R.id.graphics);
        renderThread = new RenderThread(drawView);
        renderThread.addStates(new SplashScreenAnimationState());
        renderThread.setCurrentState(SplashScreenAnimationState.class);

        Animation scaleAlpha = AnimationUtils.loadAnimation(this, R.anim.scalealpha);
        ImageView circle1 = (ImageView) findViewById(R.id.image_circle_1);
        circle1.startAnimation(scaleAlpha);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        RetroEngine.running = false;
//        try {
//            renderThread.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    protected void onResume() {
        super.onResume();

//        if (renderThread != null) {
            RetroEngine.resumeRenderThread();
//            RetroEngine.shouldWait = false;
//        }
    }

    @Override
    protected void onPause() {
        super.onPause();

//        if (renderThread != null) {
            RetroEngine.pauseRenderThread();
//            RetroEngine.running = true;
//        }
    }
}
