package net.offbeatpioneer.demoapp.retrographicsengine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import net.offbeatpioneer.demoapp.R;
import net.offbeatpioneer.demoapp.retrographicsengine.examples.backgrounds.ParallaxBackgroundState;
import net.offbeatpioneer.demoapp.retrographicsengine.examples.backgrounds.SideScrollerState;
import net.offbeatpioneer.demoapp.retrographicsengine.examples.effects.RandomCirclesState;
import net.offbeatpioneer.demoapp.retrographicsengine.examples.spriteCompilation.BasicSpriteExample;
import net.offbeatpioneer.retroengine.core.RetroEngine;
import net.offbeatpioneer.retroengine.view.DrawView;
import net.offbeatpioneer.retroengine.view.RenderThread;
import net.offbeatpioneer.retroengine.view.TouchListener;


public class FullscreenActivity extends AppCompatActivity {


    public static RenderThread renderThread;
    public static TouchListener touchListener;
    private DrawView drawView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);

        RetroEngine.init(this);
        ResManager.initImages(getResources());
        drawView = (DrawView) findViewById(R.id.graphics);
        renderThread = new RenderThread(drawView);
        renderThread.addStates(new BasicSpriteExample(), new SideScrollerState(), new ParallaxBackgroundState(), new RandomCirclesState());
//        renderThread.initStates();
//        drawView.setRenderThread(renderThread);

        Bundle b = getIntent().getExtras();
        if (b != null) {
            // Hole aktuelle Gamestate, falls vorhanden
            Class<?> tmp = (Class<?>) b.get("currentState");
            if (tmp != null) {
                Log.v("MainActivity", "Load transfered State: " + tmp.getClass());
                renderThread.setCurrentState(tmp);
//                drawView.setCurrentState(tmp);
            }
        } else {
            renderThread.setCurrentState(BasicSpriteExample.class);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        // starte gamethread wieder
        if (renderThread != null) {
//            Log.v("MainActivity", "starte gamethread wieder...");
            RetroEngine.shouldWait = false;
//            drawView.setRenderThread(renderThread);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        // halte gamethread an
        if (renderThread != null) {
            Log.v("MainActivity", "Halte gamethread an...");
            RetroEngine.isRunning = true;
        }
    }
}
