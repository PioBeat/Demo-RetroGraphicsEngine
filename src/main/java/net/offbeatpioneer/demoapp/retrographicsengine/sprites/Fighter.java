package net.offbeatpioneer.demoapp.retrographicsengine.sprites;

import android.graphics.Bitmap;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.Log;

import net.offbeatpioneer.retroengine.core.RetroEngine;
import net.offbeatpioneer.retroengine.core.sprites.AbstractSprite;
import net.offbeatpioneer.retroengine.core.sprites.AnimatedSprite;
import net.offbeatpioneer.retroengine.core.sprites.IObstacleSprite;
import net.offbeatpioneer.retroengine.core.sprites.NoFrameUpdate;
import net.offbeatpioneer.retroengine.core.util.MathUtils;

/**
 * Stell einen fliegenden Gegner als Raumschiff dar. Dieser bewegt sich auf ein
 * vordefinierten Pfad. Ist von {@link AbstractSprite} abgeleitet. <br>
 * <br>
 * Befindet sich der Gegner au�erhalb des Screens, wird dieser automatisch
 * inaktiv gesetzt, damit er nicht mehr gezeichnet werden muss und vom Speicher
 * gel�scht werden kann.
 *
 * @author Dominik Grzelak
 */
public class Fighter extends AnimatedSprite implements IObstacleSprite {
    float angleOffset;
    int pathCnt;
    int speedScalar;
//    final float SCALE = MainActivity.DENSITY;

    final double bz = 0.5;

    public Fighter() {
        frameUpdate = new NoFrameUpdate();
    }

    /**
     * Animationspfad
     */
    //float animationPath[] = { 0f, 0f, 0f, 15f, 15f, 0f, -5f, -5f, -4f, -4f,
    //0.0f, 0.0f };
    //float animationPath2[] = { 0f, 30f, 0f}; //Kreisbahn

    float animationPaths[][] = {{0f, 0f, 0f, 15f, 15f, 0f, -5f, -5f, -4f, -4f,
            0.0f, 0.0f}, {0f, 30f, 0f}};
    float animPath[] = {};


    public void init(Bitmap tex, int frameCount, PointF speed, PointF pos, int speedScalar, int animPath) {
        super.init(tex, pos, speed); // s, windowSize);
        angle = MathUtils.getRandomBetween(-360, 360);
        angleOffset = 3;
        pathCnt = 0;

        this.speedScalar = speedScalar; //7;
        speed = new PointF(0, 0);
        speed.x = speedScalar;
        speed.y = -speedScalar;
        autoDestroy = true;
        this.animPath = animationPaths[animPath];
        frameUpdate = new NoFrameUpdate();
    }

    @Override
    public void preUpdateHook() {
        if (RetroEngine.getTickCount() > starttime + 100) {
            starttime = RetroEngine.getTickCount();
            angleOffset = this.animPath[pathCnt];
            pathCnt++;
            if (pathCnt > this.animPath.length - 1) {
                pathCnt = 0;
            }

            angle += angleOffset;
            speed.x = (int) (Math.sin(MathUtils.getRad(angle)) * speedScalar);
            speed.y = -(int) (Math.cos(MathUtils.getRad(angle)) * speedScalar);
        }
        position.x += speed.x;
        position.y += speed.y;
//        if (autoDestroy) {
//
//            // TODO muss vorher abgespeichert werden! sonst ist der
//            // viewpoint immer relativ
//            PointF o = getViewportOrigin();
//            //if (!containsRect(new Rect(o.x - bufferZone, o.y - bufferZone,
//            //		o.x + MainActivity.W + bufferZone, o.y + MainActivity.H
//            //				+ bufferZone))) {
//            if (!containsRect(new RectF(o.x - (int) (RetroEngine.W * bz), o.y - (int) (RetroEngine.H * bz),
//                    o.x + (int) (RetroEngine.W * (1.0 + bz)), o.y + (int) (RetroEngine.H * (1.0 + bz))
//            ))) {
//                active = false;
//                Log.v("EnemeyA", "Anemey entfernen zu weit entfernt->löschen");
//            }
//        }
    }

//    @Override
//    public void onAction(Object parameter) {
//        getActionEventCallback().onAction(parameter);
//    }
}
