package net.offbeatpioneer.demoapp.retrographicsengine.tutorials;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import net.offbeatpioneer.demoapp.R;
import net.offbeatpioneer.demoapp.retrographicsengine.ResManager;
import net.offbeatpioneer.retroengine.core.RetroEngine;
import net.offbeatpioneer.retroengine.view.DrawView;
import net.offbeatpioneer.retroengine.view.RenderThread;
import net.offbeatpioneer.retroengine.view.TouchListener;

public class TouchInteractionActivity extends AppCompatActivity {

    public static RenderThread renderThread;
    private DrawView drawView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_interaction);

        RetroEngine.init(this);
        drawView = findViewById(R.id.graphics);
        renderThread = new RenderThread(drawView);

        renderThread.addState(new RectangleGameState());
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (renderThread != null) {
            RetroEngine.shouldWait = false;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (renderThread != null) {
            RetroEngine.isRunning = true;
        }
    }
}
