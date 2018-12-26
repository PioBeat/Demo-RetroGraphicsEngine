package net.offbeatpioneer.demoapp.retrographicsengine.helper;

import android.graphics.Bitmap;
import android.graphics.PointF;

import net.offbeatpioneer.demoapp.retrographicsengine.ResManager;
import net.offbeatpioneer.demoapp.retrographicsengine.sprites.Explosion;
import net.offbeatpioneer.retroengine.core.sprites.AbstractSprite;
import net.offbeatpioneer.retroengine.core.sprites.AnimatedSprite;
import net.offbeatpioneer.retroengine.core.util.MathUtils;

import java.util.Vector;

/**
 * Factory-Klasse für die Erzeugung von Explosionen.
 * @author Dominik Grzelak
 *
 */
public class ExplosionCreator {
	
	Vector<AbstractSprite> animatedSprites = new Vector<AbstractSprite>();
//    private final static Bitmap[] AVAILABLE_EXPLOSIONS = new Bitmap[] {ResManager.EXPLOSION, ResManager.EXPLOSION_BIG};
    private final static Bitmap[] AVAILABLE_EXPLOSIONS = new Bitmap[] {ResManager.EXPLOSION_BIG};

	public ExplosionCreator() {	
	}
	
	
	public static AnimatedSprite getExplosionOne(PointF position) {
		Explosion explosion1 = new Explosion();
		explosion1.initAsAnimation(ResManager.EXPLOSION, 64, 64, 25, 12, position, false);
		return explosion1;
	}
	
	/**
	 * Gro�e Explosion
	 * @param position Position f�r die Explosion
	 * @return Explosionssprite
	 */
	public static AnimatedSprite getExplosionTwo(PointF position) {
		Explosion explosion1 = new Explosion();
		explosion1.initAsAnimation(ResManager.EXPLOSION_BIG2, 48, 48, 50, 7, position, false);
		return explosion1;
	}
	
	/**
	 * Explosion f�r die Mine, wenn diese ber�hrt wird.
	 * @param position Position f�r die Explosion
	 * @return Explosionssprite
	 */
	public static AnimatedSprite getExplosionForMine(PointF position) {
		Explosion explosion1 = new Explosion();
		explosion1.initAsAnimation(ResManager.EXPLOSION_BIG, 64, 64, 25, 12, position, false);
		return explosion1;
	}

    public static AnimatedSprite getRandomExplosion(PointF position) {
        int idx = MathUtils.getRandomBetween(0, AVAILABLE_EXPLOSIONS.length-1);
        Explosion explosion = new Explosion();
        explosion.initAsAnimation(AVAILABLE_EXPLOSIONS[idx],
                64,
                64,
                50, 12, position, false);
		explosion.setDisabled(true);
        return explosion;
    }

	public static AnimatedSprite getDebris(PointF position) {
		AnimatedSprite sprite = new AnimatedSprite();
		sprite.initAsAnimation(ResManager.DEBRIS_1,
				25,
				25,
				50, 6, position, true);
		return sprite;
	}
	
	

}
