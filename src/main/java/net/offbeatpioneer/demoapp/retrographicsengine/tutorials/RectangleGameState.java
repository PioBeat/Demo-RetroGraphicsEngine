package net.offbeatpioneer.demoapp.retrographicsengine.tutorials;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import net.offbeatpioneer.demoapp.R;
import net.offbeatpioneer.retroengine.core.RetroEngine;
import net.offbeatpioneer.retroengine.core.animation.AlphaValueTransition;
import net.offbeatpioneer.retroengine.core.animation.AnimationSuite;
import net.offbeatpioneer.retroengine.core.animation.IAnimationSuiteListener;
import net.offbeatpioneer.retroengine.core.sprites.AbstractSprite;
import net.offbeatpioneer.retroengine.core.sprites.SpriteListGroup;
import net.offbeatpioneer.retroengine.core.sprites.simple.RectangleSprite;
import net.offbeatpioneer.retroengine.core.states.State;
import net.offbeatpioneer.retroengine.core.util.ColorTools;
import net.offbeatpioneer.retroengine.core.util.MathUtils;
import net.offbeatpioneer.retroengine.view.DefaultSwipeListener;

import java.util.Locale;

/**
 * Code for <b>Tutorial 1: Handling Touch Events</b>
 *
 * @author Dominik Grzelak
 */
public class RectangleGameState extends State {

    private TextView counterView;
    private long counter = 0;

    @Override
    public void init() {
        counterView = manager.getParentActivity().findViewById(R.id.text_counter);
    }

    public AbstractSprite createRectangle(int color) {
        RectangleSprite sprite = new RectangleSprite(color);
        // create squares
        int size = MathUtils.getRandomBetween(20, 100);
        int posX = MathUtils.getRandomBetween(size, RetroEngine.W - size);
        int posY = MathUtils.getRandomBetween(size, RetroEngine.H - size);

        final AlphaValueTransition fadeIn = new AlphaValueTransition(0, 255, 2000);
        fadeIn.setListener(new IAnimationSuiteListener() {
            @Override
            public void onAnimationStart(AnimationSuite animationSuite) {
            }

            @Override
            public void onAnimationRepeat(AnimationSuite animationSuite) {
            }

            @Override
            public void onAnimationEnd(AnimationSuite animationSuite) {
                // start the fade out animation
                animationSuite.getAnimatedSprite().beginAnimation(1);
            }
        });

        final AlphaValueTransition fadeOut = new AlphaValueTransition(255, 0, 1000);
        fadeOut.setListener(new IAnimationSuiteListener() {
            @Override
            public void onAnimationStart(AnimationSuite animationSuite) {
            }

            @Override
            public void onAnimationRepeat(AnimationSuite animationSuite) {
            }

            @Override
            public void onAnimationEnd(AnimationSuite animationSuite) {
                animationSuite.getAnimatedSprite().setActive(false);
            }
        });
        sprite.addAnimations(fadeIn, fadeOut);
//        sprite.addAnimation(fadeOut);
        return sprite.init(new PointF(posX, posY), size, size);
    }

    private long timeNow = 0;
    private String[] COLORS = new String[]{"red", "blue"};

    @Override
    public void updateLogic() {
        long tc = RetroEngine.getTickCount();
        if (tc > timeNow + 2000) {
            timeNow = tc;
            int col = MathUtils.getRandomBetween(0, 1);
            AbstractSprite rect = createRectangle(Color.parseColor(COLORS[col]));
            addSprite(rect);
            rect.beginAnimation(0);
        }
        for (AbstractSprite each : ((SpriteListGroup) getRootGroup()).getChildren()) {
            if (each.checkColWithPoint(new PointF(x, y))) {
                if (ColorTools.closeMatch(each.getTexture().getPixel(2, 2), Color.parseColor("red"), 0)) {
                    counter++;
                    x = Float.MIN_VALUE;
                    y = Float.MIN_VALUE;
                    getHandler().post(new Runnable() {
                        @Override
                        public void run() {
                            counterView.setText(String.format(Locale.getDefault(), "Hits: %d", counter));
                        }
                    });
                } else {
                    manager.changeGameState(BlueScreenState.class);
                }
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

    }

    private float x = 0;
    private float y = 0;

    @Override
    public boolean onTouchEvent(View v, MotionEvent event) {
        x = event.getX();
        y = event.getY();
        return false;
    }

    @Override
    public boolean onSwipeEvent(DefaultSwipeListener.Direction direction) {
        return false;
    }
}
