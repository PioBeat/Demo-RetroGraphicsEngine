package net.offbeatpioneer.demoapp.retrographicsengine.tutorials;

import android.os.Bundle;

import net.offbeatpioneer.demoapp.R;
import net.offbeatpioneer.demoapp.retrographicsengine.ResManager;
import net.offbeatpioneer.retroengine.core.RetroEngine;
import net.offbeatpioneer.retroengine.core.StateManager;
import net.offbeatpioneer.retroengine.view.DrawView;
import net.offbeatpioneer.retroengine.view.RenderThread;

import androidx.appcompat.app.AppCompatActivity;

public class TouchInteractionActivity extends AppCompatActivity {

    public static RenderThread renderThread;
    private DrawView drawView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_interaction);

        RetroEngine.init(this);
        ResManager.initImages(getResources());
        StateManager.getInstance().clearStates();

        drawView = findViewById(R.id.graphics);
        renderThread = new RenderThread(drawView);

        renderThread.addState(new RectangleGameState());
        renderThread.addState(new BlueScreenState());
        renderThread.setCurrentState(RectangleGameState.class);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
