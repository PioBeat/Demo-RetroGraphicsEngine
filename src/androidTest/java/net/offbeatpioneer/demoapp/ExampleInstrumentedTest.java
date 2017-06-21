package net.offbeatpioneer.demoapp;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.RectF;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.DisplayMetrics;

import net.offbeatpioneer.demoapp.retrographicsengine.sprites.Mine;
import net.offbeatpioneer.retroengine.core.RetroEngine;
import net.offbeatpioneer.retroengine.core.sprites.SpriteGridGroup;
import net.offbeatpioneer.retroengine.core.sprites.SpriteListGroup;
import net.offbeatpioneer.retroengine.core.util.MathUtils;

import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertEquals;


/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        RetroEngine.init(appContext);

        float pixelA = MathUtils.convertDpToPixel(100);
        float dpA = MathUtils.convertPixelToDp(pixelA);
        System.out.println(pixelA);
        System.out.println(dpA);
    }

    @Test
    public void spritelisttest() throws Exception {
        SpriteListGroup group1 = new SpriteListGroup();
        SpriteListGroup group2 = new SpriteListGroup();
        Mine m;
        for (int i = 0; i < 11; i++) {
            m = new Mine();
            m.setAutoDestroy(false);
            m.setActive(true);
            m.setPosition(new PointF(Math.round(Math.random() * 100), Math.round(Math.random() * 100)));
            if (i % 2 == 0) {
                m.setActive(false);
                m.setPosition(new PointF(-Math.round(Math.random()*100), -Math.round(Math.random()*100)));
                group2.add(m);
            } else {
                group1.add(m);
            }
        }
        group2.setActive(false);
        group1.add(group2);

        group1.removeInActive();
        group1.updateLogic();
    }

    @Test
    public void gridtest() throws Exception {
        SpriteGridGroup gridGroup = new SpriteGridGroup();
        SpriteGridGroup gridGroup2 = new SpriteGridGroup();

        Mine m = new Mine();
        for (int i = 0; i < 11; i++) {
            m = new Mine();
            m.setAutoDestroy(false);
            m.setActive(true);
            m.setPosition(new PointF(Math.round(Math.random() * 100), Math.round(Math.random() * 100)));
            if (i % 2 == 0) {
                m.setActive(false);
                m.setPosition(new PointF(-Math.round(Math.random()*100), -Math.round(Math.random()*100)));
                gridGroup2.add(m);
            } else {
                gridGroup.add(m);
            }
            System.out.println(m.getPosition().x + "," + m.getPosition().y);
        }
        gridGroup.add(gridGroup2);

        gridGroup.setQueryRange(new RectF(0, 0, 100, 100));
        gridGroup.removeInActive();
        gridGroup.updateLogic();
    }
}
