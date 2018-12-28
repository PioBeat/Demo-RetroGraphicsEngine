package net.offbeatpioneer.demoapp.retrographicsengine.stateexamples.helloWorld;

import android.app.Activity;
import android.os.Bundle;

import net.offbeatpioneer.demoapp.R;
import net.offbeatpioneer.retroengine.core.RetroEngine;
import net.offbeatpioneer.retroengine.view.DrawView;
import net.offbeatpioneer.retroengine.view.RenderThread;

public class HelloWorldActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_world);

        RetroEngine.init(this);
        DrawView drawView = (DrawView) findViewById(R.id.graphics);
        RenderThread renderThread = new RenderThread(drawView);

        HelloState state = new HelloState();
        renderThread.addState(state);
        renderThread.setCurrentState(HelloState.class);
    }
}
