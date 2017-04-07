package net.offbeatpioneer.demoapp.retrographicsengine.sprites;

import android.graphics.Canvas;

import net.offbeatpioneer.retroengine.core.sprites.AnimatedSprite;

/**
 * Created by Dome on 24.01.2017.
 */

public class Debris extends AnimatedSprite {

    @Override
    public void draw(Canvas canvas, long currentTime) {
        super.draw(canvas, currentTime);
    }

    @Override
    public void updateLogic() {
        super.updateLogic();
//        if (RetroEngine.getTickCount() > starttime + this.fps) {
//            starttime = RetroEngine.getTickCount();
//            frameNr += 1;
//
//            if (frameNr >= frameCnt) {
//                frameNr = 0;
//            }
//        }
//
//        sRectangle.left = frameNr * frameW;
//        sRectangle.right = sRectangle.left + frameW;
//
//        Bitmap texture;
//        try {
//            texture = Bitmap.createBitmap(getBackupTexture(),
//                    sRectangle.left,
//                    sRectangle.top,
//                    sRectangle.width(),
//                    sRectangle.height()
//            );
////            sprite.setsRectangle(sRectangle);
//            setTexture2(texture);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
