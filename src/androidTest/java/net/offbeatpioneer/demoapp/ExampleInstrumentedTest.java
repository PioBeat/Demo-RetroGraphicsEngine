package net.offbeatpioneer.demoapp;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.DisplayMetrics;

import net.offbeatpioneer.retroengine.core.RetroEngine;
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
}
