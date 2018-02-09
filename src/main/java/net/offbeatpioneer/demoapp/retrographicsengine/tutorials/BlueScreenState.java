package net.offbeatpioneer.demoapp.retrographicsengine.tutorials;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import net.offbeatpioneer.retroengine.core.states.State;

/**
 * @author Dominik Grzelak
 * @since 08.02.2018
 */
public class BlueScreenState extends State {
    @Override
    public void init() {

    }

    @Override
    public void updateLogic() {

    }

    @Override
    public void render(Canvas canvas, Paint paint, long currentTime) {
        canvas.drawColor(Color.BLUE);
    }

    @Override
    public boolean onKeyEvent(View v, int keyCode, KeyEvent keyEvent) {
        return false;
    }

    @Override
    public void cleanUp() {

    }

    @Override
    public boolean onTouchEvent(View v, MotionEvent event) {
        return false;
    }
}
