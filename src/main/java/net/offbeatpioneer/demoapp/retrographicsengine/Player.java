package net.offbeatpioneer.demoapp.retrographicsengine;

import android.graphics.Bitmap;
import android.graphics.PointF;

import net.offbeatpioneer.retroengine.core.RetroEngine;
import net.offbeatpioneer.retroengine.core.sprites.AnimatedSprite;
import net.offbeatpioneer.retroengine.core.sprites.NoFrameUpdate;
import net.offbeatpioneer.retroengine.core.util.MathUtils;

/**
 * Spieler-Sprite ist abgeleitet von Oberklasse {@link }.
 * In ihr werden die Attribute <i>Leben</i>, <i>Schussrate</i> und <i>Verwundbarkeit</i>
 * definiert und stellt somit eine Erweiterung dar, zum Basis-Sprite. Gehört deshalb nicht mehr zur Grafik-Engine.
 * <br/>
 * Die dazugehörige Logik bei Kollisionen usw., wird in den dazugehörigen ActionEvents definiert. Diese kennen
 * das Player-Sprite.
 * @author Dominik Grzelak
 * 
 */
public class Player extends AnimatedSprite {
	
	public boolean HIT_ABLE = true;
	public int LIFES;
	public int FIRE_INTERVALL = 250;
	
	public boolean TOUCH_ACTION = false;
	public boolean moveLeft = false;

	// SPEED
	private float steps = 1f;
	public int speedScalar;
    //Für Gegner-Sprites
    public int Power = 1;
	/**
	 * 1: einfaches Feuer nach vorne <br/>
	 * 2: Feuer nach vorne und hinten
	 */
	public int CANON_TYPE = 1;

	public Player() {
		super();
		HIT_ABLE = true;
		LIFES = 1;
        frameUpdate = new NoFrameUpdate();
	}

	@Override
	public AnimatedSprite init(Bitmap tex, PointF pos, PointF spd) {
		super.init(tex, pos, spd);

		speedScalar = 5;
		speed.x = speedScalar;
		speed.y = -speedScalar; // Konstante Bewegung nach "vorne"

        frameUpdate = new NoFrameUpdate();
		return this;
	}

	public void setTouch(float x, float y) {
		if (x < RetroEngine.W / 2) {
			// nach links steuern
			moveLeft = true;
		} else {
			// nach rechts steuern
			moveLeft = false;
		}

	}

    @Override
    public void updateLogicTemplate() {
        if (TOUCH_ACTION) {

            if (moveLeft) {
                angle -= steps;
            } else {
                angle += steps;
            }

            //Langsamer Anstieg der Drehgeschwindigkeit des Raumschiffes
            steps += 0.5f;
            //Verhindert schnelles "�berdrehen" des Raumschiffes
            if (steps > 10f)
                steps = 10f;

            speed.x = (int) (Math.sin(MathUtils.getRad(angle)) * speedScalar);
            speed.y = -(int) (Math.cos(MathUtils.getRad(angle)) * speedScalar);
        } else {
            steps = 0.5f;
        }

        position.x += speed.x;
        position.y += speed.y;
    }

    public int getPower() {
		return Power;
	}

	public void setPower(int power) {
		Power = power;
	}


	public void resetSpeed() {
        speed.x = (int) (Math.sin(MathUtils.getRad(angle)) * speedScalar);
        speed.y = -(int) (Math.cos(MathUtils.getRad(angle)) * speedScalar);
    }
}
