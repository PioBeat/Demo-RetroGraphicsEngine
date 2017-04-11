package net.offbeatpioneer.demoapp.retrographicsengine.examples.effects;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import net.offbeatpioneer.demoapp.R;
import net.offbeatpioneer.retroengine.core.RetroEngine;
import net.offbeatpioneer.retroengine.core.animation.AlphaValueTransition;
import net.offbeatpioneer.retroengine.core.animation.AnimationSuite;
import net.offbeatpioneer.retroengine.core.animation.IAnimationSuiteListener;
import net.offbeatpioneer.retroengine.core.animation.RelativeLinearTranslation;
import net.offbeatpioneer.retroengine.core.animation.RotationAnimation;
import net.offbeatpioneer.retroengine.core.animation.ScaleAnimation;
import net.offbeatpioneer.retroengine.core.sprites.simple.CircleSprite;
import net.offbeatpioneer.retroengine.core.sprites.simple.TriangleSprite;
import net.offbeatpioneer.retroengine.core.states.State;
import net.offbeatpioneer.retroengine.core.util.MathUtils;

/**
 * Example shows the creation of circles. In addition animations are applied
 * so that the sprites appear to increase in size, fading out, and falling down.
 *
 * @author Dominik Grzelak
 * @since 2017-04-09
 */
public class RandomCirclesState extends State {

    private final int MAX_CIRCLES = 5;
    private CircleBuilder circleBuilder = new CircleBuilder();

    private final int[] COLOURS = {R.color.card_colour_first, R.color.card_colour_second,
            R.color.card_colour_third, R.color.card_colour_fourth, R.color.card_colour_fifth};

    /**
     * The builder class for all the circles
     */
    private static class CircleBuilder {
        CircleSprite createAtRandomPosition(int color, float radius) {
            int posX = MathUtils.getRandomBetween((int) radius, RetroEngine.W);
            int posY = MathUtils.getRandomBetween((int) radius, RetroEngine.H);
            CircleSprite circleSprite = new CircleSprite(radius, color);
            circleSprite.init(posX, posY);
            addAnimation(circleSprite);
            return circleSprite;
        }

        void addAnimation(final CircleSprite circleSprite) {
            int duration = MathUtils.getRandomBetween(1000, 5000);
            ScaleAnimation scaleAnimation = new ScaleAnimation(1, 6, duration-200);
            PointF oldPos = circleSprite.getPosition();
            PointF end = new PointF(0, RetroEngine.H - oldPos.y);

            RelativeLinearTranslation linearTranslation = new RelativeLinearTranslation(end, duration);
            linearTranslation.setDoReset(true);
            circleSprite.addAnimation(scaleAnimation);
            circleSprite.addAnimation(linearTranslation);
            circleSprite.addAnimation(new AlphaValueTransition(0, 255, duration));

            scaleAnimation.setListener(new IAnimationSuiteListener() {
                @Override
                public void onAnimationStart(AnimationSuite animationSuite) {

                }

                @Override
                public void onAnimationRepeat(AnimationSuite animationSuite) {

                }

                @Override
                public void onAnimationEnd(AnimationSuite animationSuite) {
                    circleSprite.setActive(false);
                }
            });
        }
    }


    public RandomCirclesState() {
    }


    @Override
    public void init() {
    }

    @Override
    public void updateLogic() {
        updateSprites();

        if (getSpriteCount() < MAX_CIRCLES) {
            // choose a random colour from the array
            int ixColour = MathUtils.getRandomBetween(0, COLOURS.length - 1);
            int color = this.manager.getParentActivity().getResources().getColor(COLOURS[ixColour]);

            final CircleSprite circleSprite = circleBuilder.createAtRandomPosition(color, 10);
            circleSprite.setAlphaValue(0); //make circle transparent first
            addSprite(circleSprite);
            // do not start the animation for each circle at the same time
            int startDelay = MathUtils.getRandomBetween(300, 2000);
            getHandler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    circleSprite.beginAnimation();
                }
            }, startDelay);
        }
    }

    @Override
    public void render(Canvas canvas, Paint paint, long currentTime) {
        // Our background is white
        canvas.drawColor(Color.WHITE);

        // Draw all the sprites
        drawSprites(canvas, currentTime);
    }

    @Override
    public boolean onKeyEvent(View v, int keyCode, KeyEvent keyEvent) {
        return false;
    }

    @Override
    public void cleanUp() {
        clearSprites();
    }

    @Override
    public boolean onTouchEvent(View v, MotionEvent event) {
        return false;
    }
}
