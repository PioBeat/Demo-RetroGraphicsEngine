package net.offbeatpioneer.demoapp.retrographicsengine.stateexamples.datastructures;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import net.offbeatpioneer.demoapp.retrographicsengine.ResManager;
import net.offbeatpioneer.demoapp.retrographicsengine.sprites.Debris;
import net.offbeatpioneer.retroengine.auxiliary.background.StaticBackgroundLayer;
import net.offbeatpioneer.retroengine.core.RetroEngine;
import net.offbeatpioneer.retroengine.core.sprites.AbstractSprite;
import net.offbeatpioneer.retroengine.core.states.State;
import net.offbeatpioneer.retroengine.view.DefaultSwipeListener;

/**
 * Work In Progress
 */
public class SpriteGridGroupExample extends State {

    private Paint p = new Paint();
    private RectF queryRange = new RectF();

    public SpriteGridGroupExample() {

    }

    @Override
    public void init() {
        StaticBackgroundLayer bg = new StaticBackgroundLayer(ResManager.BACKGROUND_STAR_2, false);
        addBackgroundLayer(bg);
//        setReferenceSpriteOffsets(-RetroEngine.W / 2, -RetroEngine.H / 2);
        p.setColor(Color.YELLOW);
        p.setStrokeWidth(3f);
        p.setStyle(Paint.Style.STROKE);
        setQueryRange(0, 0);
        setQueryRange(getRect());

        for (int i = 0; i < RetroEngine.W / 42; i++) {
            addSprite(createRunningGrant(new PointF(i * 42 + 10, 100)));
        }
    }

    public AbstractSprite createRunningGrant(PointF pos) {
        Debris debri = new Debris();
        PointF p = getViewportOrigin();
        debri.initAsAnimation(ResManager.RUNNUNG_GRANT, 79, 42, 20, 12, p, true);
        debri.setViewportOrigin(p);
        debri.setPosition(pos);
        return debri;
    }

    @Override
    public void updateLogic() {
        setQueryRange(getRect());

        updateSprites();
    }

    public RectF getRect() {
        return queryRange;
    }

    public void setQueryRange(int startX, int startY) {
        PointF o = getViewportOrigin();
//        startX /= 2;
        startY /= 2;
        this.queryRange.set(o.x + startX, o.y + startY, o.x + startX + 200, o.y + startY + 200);
    }

    @Override
    public void render(Canvas canvas, Paint paint, long currentTime) {
        drawBackground(canvas);

//        canvas.drawRect(getRect(), p);

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
//        if (event.getAction() == MotionEvent.ACTION_UP) {
//            Log.d("TouchTest", "Touch up");
//            setQueryRange((int) event.getX(), (int) event.getY());
//        }
        return false;
    }

    @Override
    public boolean onSwipeEvent(DefaultSwipeListener.Direction direction) {
        return false;
    }
}
