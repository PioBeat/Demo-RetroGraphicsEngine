package net.offbeatpioneer.demoapp.retrographicsengine.examples.spriteCompilation;

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
import net.offbeatpioneer.retroengine.core.sprites.SpriteQuadtreeGroup;
import net.offbeatpioneer.retroengine.core.states.State;

/**
 * @author Dominik Grzelak
 * @since 04.05.2017
 */

public class SpriteQuadTreeExample extends State {

    public SpriteQuadTreeExample() {
        super(new SpriteQuadtreeGroup());
    }

    @Override
    public void init() {
        StaticBackgroundLayer bg = new StaticBackgroundLayer(ResManager.BACKGROUND_STAR_2);
        addBackgroundLayer(bg);
//        setReferenceSpriteOffsets(-RetroEngine.W / 2, -RetroEngine.H / 2);


        addSprite(createRunningGrant(new PointF(100, 100)));
        addSprite(createRunningGrant(new PointF(250, 100)));
        addSprite(createRunningGrant(new PointF(500, 100)));
    }

    public AbstractSprite createRunningGrant(PointF pos) {
        Debris debri = new Debris();
        PointF p = getViewportOrigin();//backgroundNode.getViewportOrigin();
//            p.y += MathUtils.getRandomBetween(0, RetroEngine.H);
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
        PointF o = getViewportOrigin();
        return new RectF(0 + o.x, 0 + o.y, RetroEngine.W + o.x, RetroEngine.H + o.y);
    }

    public RectF getRect2() {
        PointF o = getViewportOrigin();
        return new RectF(0 + o.x, 0 + o.y, o.x + 250, 100);
    }

    @Override
    public void render(Canvas canvas, Paint paint, long currentTime) {
        drawBackground(canvas);
        Paint p = new Paint();
        p.setColor(Color.BLUE);
        p.setStrokeWidth(3f);
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
        return false;
    }
}
