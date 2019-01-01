package net.offbeatpioneer.demoapp.retrographicsengine.helper;

import android.graphics.Color;
import android.graphics.PointF;

import net.offbeatpioneer.demoapp.retrographicsengine.ResManager;
import net.offbeatpioneer.retroengine.core.GameFont;
import net.offbeatpioneer.retroengine.core.sprites.eventhandling.EmptyAction;
import net.offbeatpioneer.retroengine.core.sprites.AbstractSprite;
import net.offbeatpioneer.retroengine.core.sprites.AnimatedSprite;
import net.offbeatpioneer.retroengine.core.sprites.EmptySprite;
import net.offbeatpioneer.retroengine.core.sprites.decorator.TextElement;


/**
 * Created by Dome on 14.09.2014.
 */
public class SpriteFactory {
    private static GameFont gameFont = new GameFont(28, Color.GREEN);

//    public static AnimatedSprite createPlusOne(PointF position) {
//        TextElement nothing = new TextElement("+1", position);
////        AnimatedSprite nothing = new EmptySprite();
////        nothing.setAlphaValue(255);
////        nothing.init(ResManager.EMPTY, position, new PointF(0,0));
////        nothing = SpriteFactory.addText(nothing, "+1");
//        nothing.setActionEventCallback(new EmptyAction());
//        return nothing;
//    }

    public static AnimatedSprite createPlusOne(PointF position) {
        AnimatedSprite nothing = new EmptySprite();
        nothing.setAlphaValue(255);
        nothing.init(ResManager.EMPTY, position, new PointF(0,0));
        nothing = SpriteFactory.addText(nothing, "+1");
        nothing.setActionEventCallback(new EmptyAction());
        return nothing;
    }

    public static AnimatedSprite createTextSprite(String text, PointF position) {
        AnimatedSprite nothing = new EmptySprite();
        nothing.init(ResManager.EMPTY, position, new PointF(0, 0));
        nothing = SpriteFactory.addText(nothing, text);
        return nothing;
    }


    public static TextElement addText(AbstractSprite sprite, String text) {
        TextElement a = new TextElement(text, sprite);
        a.setFont(gameFont);
        a.init();
//        a.initWithText(text, sprite.getPosition());
        return a;
    }
}
