package net.offbeatpioneer.demoapp.retrographicsengine;

import android.os.Bundle;
import android.util.Log;

import net.offbeatpioneer.demoapp.R;
import net.offbeatpioneer.demoapp.retrographicsengine.stateexamples.backgrounds.ParallaxBackgroundState;
import net.offbeatpioneer.demoapp.retrographicsengine.stateexamples.backgrounds.SideScrollerState;
import net.offbeatpioneer.demoapp.retrographicsengine.stateexamples.effects.ExplosionSpriteState;
import net.offbeatpioneer.demoapp.retrographicsengine.stateexamples.effects.RandomCirclesState;
import net.offbeatpioneer.demoapp.retrographicsengine.stateexamples.effects.TiledBackgroundState;
import net.offbeatpioneer.demoapp.retrographicsengine.stateexamples.datastructures.SpriteGridGroupExample;
import net.offbeatpioneer.demoapp.retrographicsengine.stateexamples.sprites.BasicSpriteExample;
import net.offbeatpioneer.demoapp.retrographicsengine.stateexamples.datastructures.SpriteQuadTreeExample;
import net.offbeatpioneer.retroengine.core.RetroEngine;
import net.offbeatpioneer.retroengine.core.StateManager;
import net.offbeatpioneer.retroengine.core.states.State;
import net.offbeatpioneer.retroengine.view.DrawView;
import net.offbeatpioneer.retroengine.view.RenderThread;
import net.offbeatpioneer.retroengine.view.TouchListener;

import androidx.appcompat.app.AppCompatActivity;


public class FullscreenActivity extends AppCompatActivity {

    public RenderThread renderThread;
    private DrawView drawView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);

        RetroEngine.init(this);
        ResManager.initImages(getResources());
//        StateManager.getInstance().clearStates();

        drawView = findViewById(R.id.graphics);
        renderThread = new RenderThread(drawView); //, Thread.MAX_PRIORITY);
        renderThread.addStates(
                new BasicSpriteExample(),
                new SideScrollerState(), new ParallaxBackgroundState(),
                new RandomCirclesState(),
                new ExplosionSpriteState(), new TiledBackgroundState(), new SpriteQuadTreeExample(), new SpriteGridGroupExample());

        Class<? extends net.offbeatpioneer.retroengine.core.states.State> tmp = BasicSpriteExample.class;
        Bundle b = getIntent().getExtras();
        if (b != null) {
            // Hole aktuelle Gamestate, falls vorhanden
            Class<? extends net.offbeatpioneer.retroengine.core.states.State> tmp2 = (Class<? extends State>) b.get("currentState");
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
        RetroEngine.resumeRenderThread();
    }

    @Override
    protected void onPause() {
        super.onPause();
        RetroEngine.pauseRenderThread();
    }

    @Override
    protected void onStop() {
        super.onStop();
        RetroEngine.changeRunningState(false);
        if (renderThread != null) {
            renderThread.cleanUp();
        }
    }
}
