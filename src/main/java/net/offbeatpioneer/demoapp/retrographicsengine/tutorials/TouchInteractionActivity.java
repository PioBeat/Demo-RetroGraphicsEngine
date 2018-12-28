package net.offbeatpioneer.demoapp.retrographicsengine.tutorials;

import android.os.Bundle;

import net.offbeatpioneer.demoapp.R;
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

        drawView = findViewById(R.id.graphics);
        renderThread = new RenderThread(drawView);

        renderThread.addState(new RectangleGameState());
        renderThread.addState(new BlueScreenState());
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
