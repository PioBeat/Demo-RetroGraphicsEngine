package net.offbeatpioneer.demoapp.retrographicsengine.helper;

import android.graphics.PointF;

import net.offbeatpioneer.demoapp.retrographicsengine.Player;
import net.offbeatpioneer.demoapp.retrographicsengine.ResManager;
import net.offbeatpioneer.retroengine.core.RetroEngine;
import net.offbeatpioneer.retroengine.core.animation.AlphaValueTransition;

/**
 * @author Dominik Grzelak
 * @since 24.09.2014
 */
public class PlayerCreator {

    private static Player player;

    public static Player create() {
        player = new Player();
        player.init(ResManager.PLAYER_ANIMATION,
                new PointF(RetroEngine.W / 2, RetroEngine.H / 2),
//                new PointF(RetroEngine.W - 10, RetroEngine.H - 30),
                new PointF(0, 0)
        );
        player.setSpeed(new PointF(0, 0));
        player.setAngle(0);
        player.HIT_ABLE = true;

        AlphaValueTransition blinkAnimation = new AlphaValueTransition(255, 0, 500);
        blinkAnimation.setLoop(true);
        blinkAnimation.setDoReset(true);
        blinkAnimation.setStopAfterMilliseconds(2000); //soll 2s blinken
        player.addAnimation(blinkAnimation);


        return player;
    }

    public static Player getPlayer() {
        return player;
    }


}
