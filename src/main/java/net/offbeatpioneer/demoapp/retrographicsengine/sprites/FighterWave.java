package net.offbeatpioneer.demoapp.retrographicsengine.sprites;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RectF;

import net.offbeatpioneer.retroengine.core.RetroEngine;
import net.offbeatpioneer.retroengine.core.sprites.AnimatedSprite;
import net.offbeatpioneer.retroengine.core.sprites.IObstacleSprite;
import net.offbeatpioneer.retroengine.core.sprites.NoFrameUpdate;
import net.offbeatpioneer.retroengine.core.util.MathUtils;

/**
 * Erzeugt einen Gegner f�r eine gegnerische Welle. Dieser fliegt nur geradeaus.
 * Die gegnerische Welle wird explizit in {@link net.offbeatpioneer.demoapp.retrographicsengine.helper.EnemyGenerator} erzeugt.
 *
 * @author Dominik Grzelak
 */
public class FighterWave extends AnimatedSprite implements IObstacleSprite {
    private int speedScalar;

    private final double bz = 0.3;
    private int Power;

    public FighterWave() {
        Power = 2;
        frameUpdate = new NoFrameUpdate();
    }

    public void init(Bitmap tex, int frameCount, PointF speed, PointF pos,
                     int speedScalar) {
        super.init(tex, pos, speed);
        this.speedScalar = speedScalar;
        speed = new PointF(0, 0);
        autoDestroy = true;
        Power = 2;
        frameUpdate = new NoFrameUpdate();
    }

    @Override
    public void draw(Canvas canvas, long currentTime) {
        if (active) {
            if (currentTime > starttime + 100) {
                starttime = currentTime;
                speed.x = (int) (Math.sin(MathUtils.getRad(angle)) * speedScalar);
                speed.y = -(int) (Math.cos(MathUtils.getRad(angle)) * speedScalar);
            }

            position.x += speed.x;
            position.y += speed.y;

            Matrix rotator = new Matrix();
            rotator.preTranslate(position.x, position.y);
            rotator.postRotate(angle, position.x + frameW / 2, position.y + frameH / 2);

            canvas.drawBitmap(texture, rotator, paint);
            if (autoDestroy) {
                PointF o = getViewportOrigin();
                if (!ContainsRect(new RectF(o.x - (int) (RetroEngine.W * bz),
                        o.y - (int) (RetroEngine.H * bz), o.x
                        + (int) (RetroEngine.W * (1.0 + bz)), o.y
                        + (int) (RetroEngine.H * (1.0 + bz))))) {
                    active = false;
                }
            }
        }
    }

    public int getPower() {
        return Power;
    }

    public void setPower(int power) {
        Power = power;
    }

    @Override
    public void updateLogic() {

    }

    @Override
    public void onAction(Object param) {
        Power -= 1;
        if (Power <= 0)
            setActive(false);
//		return ExplosionCreator.getExplosionTwo(getPosition());
        return;
    }
}
