package net.offbeatpioneer.demoapp.retrographicsengine.stateexamples.effects;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import net.offbeatpioneer.demoapp.R;
import net.offbeatpioneer.retroengine.core.GameFont;
import net.offbeatpioneer.retroengine.core.RetroEngine;
import net.offbeatpioneer.retroengine.core.sprites.AnimatedSprite;
import net.offbeatpioneer.retroengine.core.sprites.decorator.TextElement;
import net.offbeatpioneer.retroengine.core.states.State;
import net.offbeatpioneer.retroengine.view.DefaultSwipeListener;

/**
 * Example - Sprite with an explosion animation decorated with a text sprite.
 *
 * @author Dominik Grzelak
 */
public class ExplosionSpriteState extends State {

    @Override
    public void init() {
        //Load the bitmap
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.ARGB_8888;
        opt.inScaled = false;

        Bitmap bitmapExplosion = BitmapFactory.decodeResource(RetroEngine.Resources,
                R.drawable.explode_3,
                opt);

        PointF position = new PointF(RetroEngine.W / 2, RetroEngine.H / 2);
        AnimatedSprite explosion = new AnimatedSprite();
        explosion.setScale(2f);
        explosion.initAsAnimation(bitmapExplosion, 64, 64, 25, 12, position, true);

        TextElement textElement = new TextElement(explosion);
        GameFont font = new GameFont(34);
        font.setFontColor(Color.WHITE);
        textElement.setFont(font);
        textElement.initWithText("This is an explosion");

        addSprite(textElement);
    }

    @Override
    public void updateLogic() {
        updateSprites();
    }

    @Override
    public void render(Canvas canvas, Paint paint, long currentTime) {
        canvas.drawColor(Color.rgb(150, 150, 150));
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

    @Override
    public boolean onSwipeEvent(DefaultSwipeListener.Direction direction) {
        return false;
    }
}
