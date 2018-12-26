package net.offbeatpioneer.demoapp.retrographicsengine.sprites;

import android.graphics.PointF;
import android.graphics.RectF;

import net.offbeatpioneer.retroengine.core.RetroEngine;
import net.offbeatpioneer.retroengine.core.sprites.AnimatedSprite;
import net.offbeatpioneer.retroengine.core.sprites.NoFrameUpdate;


/**
 * Elternklasse für alle Items. Besitzen nur anderes Aussehen und andere Eigenschaften, jedoch die gleiche Logik:
 * <ul>
 * <li>Werden zerstört, wenn nicht mehr am Bildschirm</li>
 * <li>Soundeffekt wird bei Berührung abgespielt</li>
 * <li>Rotiert sich beim Aufnehmen</li>
 * </ul>
 * Created by Dome on 14.09.2014.
 */
public class Item extends AnimatedSprite {

    public Item() {
        frameUpdate = new NoFrameUpdate();
    }

    @Override
    public void updateLogicHook() {
//        if (autoDestroy) {
//            PointF o = getViewportOrigin();
//            int bz = getBufferZone();
//            if (!containsRect(new RectF(o.x - RetroEngine.W - bz, o.y
//                    - bz, o.x + RetroEngine.W + bz, o.y
//                    + RetroEngine.H + bz))) {
//                active = false;
//            }
//        }
    }

}
