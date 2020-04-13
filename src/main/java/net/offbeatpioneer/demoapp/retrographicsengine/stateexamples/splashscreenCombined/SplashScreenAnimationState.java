package net.offbeatpioneer.demoapp.retrographicsengine.stateexamples.splashscreenCombined;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import net.offbeatpioneer.demoapp.retrographicsengine.ResManager;
import net.offbeatpioneer.retroengine.core.GameFont;
import net.offbeatpioneer.retroengine.core.RetroEngine;
import net.offbeatpioneer.retroengine.core.animation.AbsoluteSingleNodeLinearTranslation;
import net.offbeatpioneer.retroengine.core.animation.AlphaValueTransition;
import net.offbeatpioneer.retroengine.core.animation.timeline.AnimationTimeline;
import net.offbeatpioneer.retroengine.core.animation.timeline.StoryLineSlot;
import net.offbeatpioneer.retroengine.core.sprites.AbstractSprite;
import net.offbeatpioneer.retroengine.core.sprites.AnimatedSprite;
import net.offbeatpioneer.retroengine.core.sprites.decorator.TextElement;
import net.offbeatpioneer.retroengine.core.states.State;
import net.offbeatpioneer.retroengine.view.DefaultSwipeListener;

import java.util.List;

/**
 * @author Dominik Grzelak
 * @since 11.02.2017.
 */
public class SplashScreenAnimationState extends State {

    private AnimationTimeline storyLine = new AnimationTimeline();

    @Override
    public void init() {

        int duration = 4000;
        AnimatedSprite fighter = new AnimatedSprite();
        fighter.init(ResManager.ENEMY_FIGHTER, new PointF(0, 0));
        AbsoluteSingleNodeLinearTranslation anim = new AbsoluteSingleNodeLinearTranslation(fighter, AbsoluteSingleNodeLinearTranslation.Direction.BOTTOMRIGHT, duration);
        fighter.addAnimation(anim);

        StoryLineSlot slot0 = new StoryLineSlot(duration);
        slot0.setOverwrite(true);
        slot0.addSprite(fighter);


        AlphaValueTransition alphaAnim = new AlphaValueTransition(0, 255, 2000);
        TextElement logoText = new TextElement("Hello, World", new PointF(RetroEngine.W / 2 - 50, RetroEngine.H / 2 - 50));
        logoText.setFont(new GameFont(35));
        logoText.init();
        logoText.addAnimation(alphaAnim);
        StoryLineSlot slot1 = new StoryLineSlot(logoText, duration);
        slot1.setOverwrite(true);

        storyLine.add(slot0);
        storyLine.add(slot1);

        List<AbstractSprite> spriteList = storyLine.getCurrentElement().getAnimatedSprites();
        storyLine.initCurrentSlot(); //beginne
        storyLine.finalizeTimeline();

        addSprites(spriteList);
    }

    @Override
    public void updateLogic() {
        List<AbstractSprite> erg = storyLine.update();
        if (erg != null) {
            if (storyLine.isOverwrite()) {
                clearSprites();
                addSprites(erg);
            } else {
                addSprites(erg);
            }
        }

        updateSprites();
    }

    @Override
    public void render(Canvas canvas, Paint paint, long currentTime) {
        canvas.drawColor(Color.WHITE);
        drawSprites(canvas, currentTime);
    }

    @Override
    public boolean onKeyEvent(View v, int keyCode, KeyEvent keyEvent) {
        return false;
    }

    @Override
    public void cleanUp() {
        clearSprites();
//        manager.clearStates();
    }

    @Override
    public boolean onTouchEvent(View v, MotionEvent event) {
        return false;
    }

    @Override
    public boolean onSwipeEvent(DefaultSwipeListener.Direction direction) {
        return false;
    }
}
