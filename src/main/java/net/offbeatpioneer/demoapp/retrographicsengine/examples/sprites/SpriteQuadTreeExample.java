package net.offbeatpioneer.demoapp.retrographicsengine.examples.sprites;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import net.offbeatpioneer.demoapp.retrographicsengine.ResManager;
import net.offbeatpioneer.demoapp.retrographicsengine.sprites.Debris;
import net.offbeatpioneer.retroengine.auxiliary.background.StaticBackgroundLayer;
import net.offbeatpioneer.retroengine.core.RetroEngine;
import net.offbeatpioneer.retroengine.core.sprites.AbstractSprite;
import net.offbeatpioneer.retroengine.core.sprites.SpriteQuadtreeGroup;
import net.offbeatpioneer.retroengine.core.states.State;

/**
 * Example on how to use the {@link SpriteQuadtreeGroup} in a {@link State}.
 * <p>
 * Some animated sprites will be drawn horizontally filling the whole width of the screen.
 * The query range (search rectangle) for the quadtree of the sprite group is always renewed
 * by touching the screen. A predefined rectangle at the pointed position on the screen will be drawn
 * which serves also as the query range for the quadtree.
 * This demonstrates how the quadtree sprite group is working. Although many sprites are created only
 * a fraction is actually drawn - specifically only the ones which fall in the search rectangle.
 *
 * @author Dominik Grzelak
 * @since 04.05.2017
 */
public class SpriteQuadTreeExample extends State {
    private RectF queryRange;

    public SpriteQuadTreeExample() {
        super(new SpriteQuadtreeGroup());
    }

    @Override
    public void init() {
        StaticBackgroundLayer bg = new StaticBackgroundLayer(ResManager.BACKGROUND_STAR_2);
        addBackgroundLayer(bg);
//        setReferenceSpriteOffsets(-RetroEngine.W / 2, -RetroEngine.H / 2);

        setQueryRange(0, 0);
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
        this.queryRange = new RectF(o.x + startX, o.y + startY, o.x + startX + 200, o.y + startY + 200);
    }

    @Override
    public void render(Canvas canvas, Paint paint, long currentTime) {
//        drawBackground(canvas);
        canvas.drawColor(Color.rgb(255, 204, 88));
        Paint p = new Paint();
        p.setColor(Color.BLUE);
        p.setStrokeWidth(3f);
        p.setStyle(Paint.Style.STROKE);
        canvas.drawRect(getRect(), p);

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
        if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
            Log.d("TouchTest", "Touch up");
            setQueryRange((int) event.getX(), (int) event.getY());
        }
        return false;
    }
}
