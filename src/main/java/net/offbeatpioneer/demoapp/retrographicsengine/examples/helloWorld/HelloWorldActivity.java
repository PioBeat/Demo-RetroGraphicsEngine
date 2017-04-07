package net.offbeatpioneer.demoapp.retrographicsengine.examples.helloWorld;

import android.app.Activity;
import android.os.Bundle;

import net.offbeatpioneer.demoapp.R;
import net.offbeatpioneer.retroengine.core.RetroEngine;
import net.offbeatpioneer.retroengine.view.DrawView;
import net.offbeatpioneer.retroengine.view.GameThread;

public class HelloWorldActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_world);

        RetroEngine.init(this);
        DrawView drawView = (DrawView) findViewById(R.id.graphics);
        GameThread gameThread = new GameThread(drawView);

        HelloState state = new HelloState();
        gameThread.addState(state);
        gameThread.initState();
    }
}
