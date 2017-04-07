package net.offbeatpioneer.demoapp.retrographicsengine.sprites;

import android.graphics.Bitmap;
import android.graphics.PointF;

import net.offbeatpioneer.demoapp.retrographicsengine.ResManager;
import net.offbeatpioneer.retroengine.core.RetroEngine;
import net.offbeatpioneer.retroengine.core.animation.AnimationSuite;
import net.offbeatpioneer.retroengine.core.animation.LinearTranslation;
import net.offbeatpioneer.retroengine.core.animation.RotationAnimation;
import net.offbeatpioneer.retroengine.core.sprites.AnimatedSprite;
import net.offbeatpioneer.retroengine.core.sprites.EmptySprite;
import net.offbeatpioneer.retroengine.core.util.MathUtils;

/**
 * Created by Dome on 14.09.2014.
 */
public class SpriteAnimatorFactory {

    public static AnimatedSprite createAnimatedSprite(PointF start, PointF to, int millisecs) {
        AnimatedSprite debri = new AnimatedSprite();
        PointF p = new PointF(0, 0);
        p.y += MathUtils.getRandomBetween(0, RetroEngine.H);
        debri.initAsAnimation(ResManager.DEBRIS_1, 25, 25, 25, 6, p, false);
        debri.setAngle(90);
        LinearTranslation translation = new LinearTranslation(p.x, to.y, millisecs);
        debri.addAnimation(translation);
        return debri;
    }

    public static AnimatedSprite createRotationalSpriteItem(PointF pos, boolean loop) {
        AnimatedSprite life = new AnimatedSprite();
        life.initAsAnimation(ResManager.ITEM_MORELIFE, -1, -1, 5, 1, pos, true);
        AnimationSuite suite = new RotationAnimation();
        suite.setLoop(loop);
        life.addAnimation(suite);
        return life;
    }

    public static AnimatedSprite createTransitionalLogo(Bitmap bitmap, PointF pos) {
        AnimatedSprite simpleSprite = new EmptySprite();
        simpleSprite.initAsAnimation(bitmap, bitmap.getHeight(), bitmap.getWidth(), 0, 0, pos, false);
        AnimationSuite suite = new LinearTranslation(0, RetroEngine.W/2, 2000);
        suite.setAnimatedSprite(simpleSprite);
        suite.setLoop(false);
        simpleSprite.addAnimation(suite);
        return simpleSprite;
    }
}
