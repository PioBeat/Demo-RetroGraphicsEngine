package net.offbeatpioneer.demoapp.retrographicsengine.sprites;

import android.graphics.Canvas;
import android.graphics.PointF;

import net.offbeatpioneer.retroengine.core.sprites.AnimatedSprite;
import net.offbeatpioneer.retroengine.core.sprites.IObstacleSprite;


/**
 * Stellt ein unbewegliches Objekt dar, welches als Mine in Erscheinung tritt.
 * Ist von {@link net.offbeatpioneer.retroengine.core.sprites.AnimatedSprite} abgeleitet. <br/>
 * Wird die Mine getroffen, so wird diese durch ein anderes Image ersetzt,
 * welche diese als "kaputt" markiert. Zus�tzlich wird noch eine Explosion
 * abgespielt.
 * <p>
 * <br>
 * <br>
 * Befindet sich die Mine au�erhalb des Screens, wird diese automatisch inaktiv
 * gesetzt, damit diese nicht mehr gezeichnet werden muss und vom Speicher
 * gel�scht werden kann.
 *
 * @author Dominik Grzelak
 */
public class Mine extends AnimatedSprite implements IObstacleSprite {

    public Mine() {
        super();
        frameW = 50;
        frameH = 50;
    }

    @Override
    public void preUpdateHook() {
        setPosition(new PointF(position.x + 10, position.y + 20));
    }

    @Override
    public void draw(Canvas canvas, long currentTime) {
        super.draw(canvas, currentTime);
//        canvas.drawBitmap(texture, position.x, position.y, paint);
    }

}
