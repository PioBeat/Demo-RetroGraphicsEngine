package net.offbeatpioneer.demoapp.retrographicsengine.sprites;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RectF;

import net.offbeatpioneer.retroengine.core.RetroEngine;
import net.offbeatpioneer.retroengine.core.sprites.AnimatedSprite;


/**
 * Sprite stellt eine Schuss vom Spieler dar. Ist abgeleitet von {@link AnimatedSprite}. <br><br>Befindet sich der Schuss au�erhalb des Screens,
 * wird dieser automatisch inaktiv gesetzt, damit er nicht mehr gezeichnet wird.
 *
 * @author Dominik Grzelak
 */
public class Bullet extends AnimatedSprite {

    public Bullet() {
        autoDestroy = true;
    }

    public long counter = 0;

    @Override
    public void preUpdateHook() {
        if (speed.x == 0 && speed.y == 0)
            active = false;

        //if (active) {

        position.x += speed.x;
        position.y += speed.y;
        counter++;

//        if (autoDestroy) {
//            PointF o = getViewportOrigin();
//            if (!containsRect(new RectF(o.x, o.y, o.x + RetroEngine.W, o.y + RetroEngine.H))) {
//                active = false;
//            }
//        }
    }

    //TODO: entfernen, auslagern
    @Override
    public void draw(Canvas canvas, long currentTime) {
        Matrix rotator = new Matrix();
        rotator.preTranslate(position.x, position.y);
        rotator.postRotate(angle, position.x + frameW / 2, position.y + frameH / 2);

        canvas.drawBitmap(texture, rotator, paint);

        if (autoDestroy) { //Sprites sollen nicht unmittelbar zerstört werden
            //int dist = MainActivity.H*2;
            PointF o = getViewportOrigin();
            if (!containsRect(new RectF(o.x, o.y, o.x + RetroEngine.W, o.y + RetroEngine.H))) {
                active = false;
            }
        }
    }

    @Override
    public void onAction(Object param) {
        setActive(false);
    }

}
