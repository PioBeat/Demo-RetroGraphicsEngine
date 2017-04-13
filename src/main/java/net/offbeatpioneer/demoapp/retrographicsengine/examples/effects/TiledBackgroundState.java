package net.offbeatpioneer.demoapp.retrographicsengine.examples.effects;

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
import net.offbeatpioneer.retroengine.core.RetroEngine;
import net.offbeatpioneer.retroengine.core.animation.RelativeLinearTranslation;
import net.offbeatpioneer.retroengine.core.sprites.AnimatedSprite;
import net.offbeatpioneer.retroengine.core.sprites.SpriteGroup;
import net.offbeatpioneer.retroengine.core.states.State;

/**
 * @author Dominik Grzelak
 * @since 13.04.2017
 */

public class TiledBackgroundState extends State {


    @Override
    public void init() {
        //Load the bitmap
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inScaled = false;

        Bitmap bitmapTile = BitmapFactory.decodeResource(RetroEngine.Resources,
                R.drawable.tile_1,
                opt);


        SpriteGroup background = new SpriteGroup();

        int width = bitmapTile.getWidth();
        int height = bitmapTile.getHeight();
        int rowCount = (int) (RetroEngine.W / width + 1f);
        int colCount = (int) (RetroEngine.H / height + 1f);

        for (int i = -1; i <= colCount; i++) {
            for (int j = -1; j <= rowCount; j++) {
                AnimatedSprite tile = new AnimatedSprite();
                AnimatedSprite tmp = tile.init(bitmapTile, new PointF(j * width, i * height));
                tmp.setAutoDestroy(false);
                background.add(tmp);
            }
        }

        RelativeLinearTranslation linearTranslation = new RelativeLinearTranslation(new PointF(0, 128), 500);
        linearTranslation.setLoop(true);
        background.addAnimation(linearTranslation);
        background.beginAnimation();
        addSprite(background);
    }

    @Override
    public void updateLogic() {
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
    }

    @Override
    public boolean onTouchEvent(View v, MotionEvent event) {
        return false;
    }
}
