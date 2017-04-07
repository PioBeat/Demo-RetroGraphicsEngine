package net.offbeatpioneer.demoapp.retrographicsengine.helper;

import android.graphics.PointF;

import net.offbeatpioneer.demoapp.retrographicsengine.ResManager;
import net.offbeatpioneer.retroengine.core.animation.AbsoluteSingleNodeLinearTranslation;
import net.offbeatpioneer.retroengine.core.sprites.AbstractSprite;
import net.offbeatpioneer.retroengine.core.sprites.AnimatedSprite;

/**
 * Factory-Klasse f�r die Generierung von Gegnern. Bestimmte Gegnertypen werden in bestimmten Abst�nden generiert.
 * Dabei werden Minen nur alle 5s und gegnerische Raumschiffe alle 4s erzeugt.
 * @author Dominik Grzelak
 *
 */
public class EnemyGenerator {
	
	public static int ttl_mine = 5000;
	public static int ttl_fighter = 4000;
	
	public static boolean fireEnemyWave = false;
	
	//St�rke der zu erzeugenden Gegner
	public static int Simple_Enemy_Life = 1;
	
	//Werden in GamePlay verwendet:
	
	//Nach wie vielen Punkten jeweils soll die Welle erzeugt werden?
	public static int Enemy_Wave_After = 30; 
	//Nach wie vielen Punkten soll jeweils der Gegner st�rker werden?
	public static int Stronger_Enemy_After = 15;
	
	
	private static long startedMine = 0;
	private static long startedFighter = 0; 
	

//	public static Sprite generate(long nowTime) {
//		if (nowTime > (startedMine + ttl_mine)) {
//			startedMine = nowTime;
//			Mine mine = new Mine();
//			mine.initAsAnimation(ResManager.ENEMY_MINE, 32, 32, 1, 1, Gameplay.getRandomStartPosition(Gameplay.getViewportOrigin()), true);
//			return mine;
//		}
//
//		if (nowTime > (startedFighter + ttl_fighter)) {
//			int randAnim = MathUtils.getRandomBetween(0, 1);
//			Log.v("ANIMATION", ""+randAnim);
//			int randSpeed = MathUtils.getRandomBetween(3, 8);
//			startedFighter = nowTime;
//			Fighter enemy = new Fighter();
////            enemy.setActionEventCallback(new FigherAction(enemy));
//			enemy.Power = Simple_Enemy_Life;
//			enemy.initAsAnimation(ResManager.ENEMY_FIGHTER, 1, new PointF(0, 0),
//					Gameplay.getRandomStartPosition(Gameplay.getViewportOrigin()), randSpeed, randAnim);
//			return enemy;
//		}
//		return null;
//	}

    public static AbstractSprite createFighterForIntro(PointF position, PointF start, PointF end, int time) {
        AnimatedSprite enemy = new AnimatedSprite();
        enemy.init(ResManager.ENEMY_FIGHTER,
				position, new PointF(0,0)
		);
        enemy.setAngle(90);

        AbsoluteSingleNodeLinearTranslation linearTranslation2 = new AbsoluteSingleNodeLinearTranslation(enemy, AbsoluteSingleNodeLinearTranslation.Direction.TOPRIGHT, time);
        linearTranslation2.setLoop(false);
        linearTranslation2.setDoReset(true);
        enemy.addAnimation(linearTranslation2);
        return enemy;
    }
	
//	public static Sprite[] generateEnemyWave() {
//		//L�nges der Breite Fighter aufstellen: Anzahl berechnen
//		int anzahlFighter = RetroEngine.W / (64 + 5); //5 Pixel abstand
//		//Kommen alle von oben, daher nach unten drehen
//
//		FighterWave[] wave = new FighterWave[anzahlFighter];
//		PointF start = getViewportOrigin();
//		float px = 0, py = 0;
//		for (int i = 0; i < wave.length; i++) {
//			wave[i] = new FighterWave();
//			Bitmap tex = ResManager.ENEMY_FIGHTER2;
//			if(i % 2 == 0)
//				tex = ResManager.ENEMY_FIGHTER3;
//
//			wave[i].initAsAnimation(tex, 1, new PointF(0, 0), new PointF(start.x + px, start.y + py), MathUtils.getRandomBetween(5, 10));
//			wave[i].setAngle(180);
//			wave[i].Power = 1;
//			px += 70;
//		}
//		return wave;
//	}
	
	public static void setDefaultStatus() {
		ttl_mine = 5000;
		ttl_fighter = 4000;
		Simple_Enemy_Life = 1;
		fireEnemyWave = false;
		Enemy_Wave_After = 30;
		Stronger_Enemy_After = 15;
	}

}
