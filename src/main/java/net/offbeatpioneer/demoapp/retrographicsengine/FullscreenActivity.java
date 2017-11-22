package net.offbeatpioneer.demoapp.retrographicsengine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import net.offbeatpioneer.demoapp.R;
import net.offbeatpioneer.demoapp.retrographicsengine.examples.backgrounds.ParallaxBackgroundState;
import net.offbeatpioneer.demoapp.retrographicsengine.examples.backgrounds.SideScrollerState;
import net.offbeatpioneer.demoapp.retrographicsengine.examples.effects.ExplosionSpriteState;
import net.offbeatpioneer.demoapp.retrographicsengine.examples.effects.RandomCirclesState;
import net.offbeatpioneer.demoapp.retrographicsengine.examples.effects.TiledBackgroundState;
import net.offbeatpioneer.demoapp.retrographicsengine.examples.grid.SpriteGridGroupExample;
import net.offbeatpioneer.demoapp.retrographicsengine.examples.sprites.BasicSpriteExample;
import net.offbeatpioneer.demoapp.retrographicsengine.examples.sprites.SpriteQuadTreeExample;
import net.offbeatpioneer.retroengine.core.RetroEngine;
import net.offbeatpioneer.retroengine.view.DrawView;
import net.offbeatpioneer.retroengine.view.RenderThread;
import net.offbeatpioneer.retroengine.view.TouchListener;

import static android.os.Process.THREAD_PRIORITY_FOREGROUND;


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
        drawView = findViewById(R.id.graphics);
        renderThread = new RenderThread(drawView); //, Thread.MAX_PRIORITY);
        renderThread.addStates(new BasicSpriteExample(), new SideScrollerState(),
                new ParallaxBackgroundState(), new RandomCirclesState(),
                new ExplosionSpriteState(), new TiledBackgroundState(), new SpriteQuadTreeExample(), new SpriteGridGroupExample());

        Class<?> tmp = BasicSpriteExample.class;
        Bundle b = getIntent().getExtras();
        if (b != null) {
            // Hole aktuelle Gamestate, falls vorhanden
            Class<?> tmp2 = (Class<?>) b.get("currentState");
            if (tmp2 != null) {
                Log.v("MainActivity", "Load transfered State: " + tmp2.getClass());
                tmp = tmp2;
            }
        }
        renderThread.setCurrentState(tmp);

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
