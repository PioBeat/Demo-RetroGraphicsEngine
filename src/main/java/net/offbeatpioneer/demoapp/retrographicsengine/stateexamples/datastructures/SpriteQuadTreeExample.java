package net.offbeatpioneer.demoapp.retrographicsengine.stateexamples.datastructures;

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
 * Some animated sprites will be drawn randomly on the screen. To make them visible a query is executed
 * by touching the screen. A query (search rectangle) defines the area of the screen representing coordinates for the quad tree.
 * A yellow-colored rectangle at the position on the screen will be drawn which represents the query range for the quad tree.
 * The query range for the quad tree of the sprite group is always renewed by touching the screen.
 * <p>
 * This demonstrates how the sprite group <i>quad tree</i> is working. Although many sprites are created only
 * a fraction is actually drawn - specifically only the ones which fall in the search rectangle.
 * This can be used to speed up collisions checks.
 *
 * @author Dominik Grzelak
 * @since 04.05.2017
 */
public class SpriteQuadTreeExample extends State {
    private RectF userQuery = new RectF();
    private Paint p = new Paint();

    public SpriteQuadTreeExample() {
        // replace the preset root node from type SpriteListGroup with a quad tree
        super(new SpriteQuadtreeGroup());
    }

    @Override
    public void init() {
        StaticBackgroundLayer bg = new StaticBackgroundLayer(ResManager.BACKGROUND_STARS_3, false);
        addBackgroundLayer(bg);
//        setReferenceSpriteOffsets(-RetroEngine.W / 2, -RetroEngine.H / 2);
        p.setColor(Color.YELLOW);
        p.setStrokeWidth(3f);
        p.setStyle(Paint.Style.STROKE);
        setQueryRange(0, 0);
        for (int i = 0; i < RetroEngine.W / 42; i++) {
            addSprite(createRunningGrant(new PointF(i * 42 + 10, 100)));
        }
    }

    public AbstractSprite createRunningGrant(PointF pos) {
        Debris debri = new Debris();
        PointF p = getViewportOrigin();
        pos.offset(p.x, p.y);
        debri.initAsAnimation(ResManager.RUNNUNG_GRANT, 79, 42, 20, 12, pos, true);
        debri.setViewportOrigin(p);
//        debri.setPosition(pos);
        return debri;
    }

    @Override
    public void updateLogic() {
        setQueryRange(userQuery);

        updateSprites();
    }

    public void setQueryRange(int startX, int startY) {
        PointF o = getViewportOrigin();
//        startX /= 2;
        startY /= 2;
        this.userQuery.set(o.x + startX, o.y + startY, o.x + startX + 200, o.y + startY + 200);
    }

    @Override
    public void render(Canvas canvas, Paint paint, long currentTime) {
        drawBackground(canvas);

        canvas.drawRect(userQuery, p);

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
