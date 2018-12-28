package net.offbeatpioneer.demoapp.retrographicsengine;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import net.offbeatpioneer.demoapp.R;
import net.offbeatpioneer.demoapp.retrographicsengine.stateexamples.splashscreenCombined.SplashScreenAnimationState;
import net.offbeatpioneer.retroengine.core.RetroEngine;
import net.offbeatpioneer.retroengine.core.StateManager;
import net.offbeatpioneer.retroengine.view.DrawView;
import net.offbeatpioneer.retroengine.view.RenderThread;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenExample extends AppCompatActivity {

    RenderThread renderThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen_example);

        RetroEngine.init(this);
        ResManager.initImages(getResources());
        StateManager.getInstance().clearStates();

        DrawView drawView = findViewById(R.id.graphics);
        renderThread = new RenderThread(drawView);
        renderThread.addStates(new SplashScreenAnimationState());
        renderThread.setCurrentState(SplashScreenAnimationState.class);

        Animation scaleAlpha = AnimationUtils.loadAnimation(this, R.anim.scalealpha);
        ImageView circle1 = findViewById(R.id.image_circle_1);
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
