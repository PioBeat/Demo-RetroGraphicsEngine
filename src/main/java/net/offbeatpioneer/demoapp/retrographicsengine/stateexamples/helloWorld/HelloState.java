package net.offbeatpioneer.demoapp.retrographicsengine.stateexamples.helloWorld;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import net.offbeatpioneer.retroengine.core.sprites.decorator.TextElement;
import net.offbeatpioneer.retroengine.core.states.State;
import net.offbeatpioneer.retroengine.view.DefaultSwipeListener;

public class HelloState extends State {

    @Override
    public void init() {
        TextElement textSprite = new TextElement("Hello, World!");
        textSprite.setBgColor(Color.argb(150, 255, 204, 88));
        textSprite.init(new PointF(0, 0));
        addSprite(textSprite);
    }

    @Override
    public void updateLogic() {
        updateSprites();
    }

    @Override
    public void render(Canvas canvas, Paint paint, long currentTime) {
        canvas.drawColor(Color.WHITE);
        drawSprites(canvas, currentTime);
    }

    @Override
    public boolean onKeyEvent(View v, int keyCode, KeyEvent keyEvent) {
        return false;
    }

    @Override
    public void cleanUp() {
        clearSprites();
    }

    @Override
    public boolean onTouchEvent(View v, MotionEvent event) {
        return false;
    }

    @Override
    public boolean onSwipeEvent(DefaultSwipeListener.Direction direction) {
        return false;
    }
}