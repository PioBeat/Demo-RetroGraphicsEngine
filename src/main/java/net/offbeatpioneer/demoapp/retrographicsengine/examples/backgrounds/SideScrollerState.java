package net.offbeatpioneer.demoapp.retrographicsengine.examples.backgrounds;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import net.offbeatpioneer.demoapp.R;
import net.offbeatpioneer.demoapp.retrographicsengine.Player;
import net.offbeatpioneer.demoapp.retrographicsengine.ResManager;
import net.offbeatpioneer.demoapp.retrographicsengine.helper.ExplosionCreator;
import net.offbeatpioneer.demoapp.retrographicsengine.helper.PlayerCreator;
import net.offbeatpioneer.retroengine.auxiliary.background.FixedScrollableLayer;
import net.offbeatpioneer.retroengine.auxiliary.background.ParallaxLayer;
import net.offbeatpioneer.retroengine.core.RetroEngine;
import net.offbeatpioneer.retroengine.core.sprites.AnimatedSprite;
import net.offbeatpioneer.retroengine.core.states.State;

import static net.offbeatpioneer.retroengine.core.util.ColorTools.closeMatch;

/**
 * State example on how to use the {@link FixedScrollableLayer} for side scrolling backgrounds.
 * Additionally a simple collision detection is implemented for the background by "color coding".
 * Before, a concrete colour is defined as collision colour. Then the colour value of the background
 * at the position of the player ({@link PointF}) is evaluated. If this colour matches with the predfined
 * collision colour then the player is obviously near an obstacle or something.
 * <p>
 * More colours can be used to encode different obstacle types.
 *
 * @author Dominik Grzelak
 */
public class SideScrollerState extends State {
    private final static String TAG = SideScrollerState.class.getSimpleName();

    private Player player;
    private TextView textViewColl;
    private ParallaxLayer backgroundLayer;
    private FixedScrollableLayer bgLayer;
    private boolean addExplosion = false;

    public SideScrollerState() {
        super();
        setStateName("SideScrollerState");
    }

    @Override
    public void init() {

        backgroundLayer = new ParallaxLayer(ResManager.PARALAYER_STAR_1, 0.6f);
        bgLayer = new FixedScrollableLayer(ResManager.BG_LEVEL_1, 500, 0.7f);
        player = PlayerCreator.create();

        textViewColl = (TextView) manager.getParentActivity().findViewById(R.id.textViewColl);
        textViewColl.setText("Collision detected");
        textViewColl.setVisibility(View.INVISIBLE);

        addBackgroundLayer(bgLayer);
        addBackgroundLayer(backgroundLayer);
        setReferenceSprite(player);

        addSprite(player);
    }

    @Override
    public void render(Canvas canvas, Paint paint, long currentTime) {
        //Draw the background first
        drawBackground(canvas);

        drawSprites(canvas, currentTime);
    }

    @Override
    public void updateLogic() {
        setReferenceSprite(player);

        if (addExplosion) {
            addExplosion = false;
            PointF posEx = new PointF(player.getPosition().x + 50, player.getPosition().y);
            AnimatedSprite s = ExplosionCreator.getRandomExplosion(posEx);
            addSprite(s);
            s.setDisable(false);
        }

        updateSprites();

        //check
        FixedScrollableLayer layer = (FixedScrollableLayer) getBackgroundLayer(0);
        Bitmap bitmap = layer.getBackground();
        int pixel = bitmap.getPixel((int) player.getPosition().x, (int) player.getPosition().y);
        int redValue = Color.red(pixel);
        int blueValue = Color.blue(pixel);
        int greenValue = Color.green(pixel);
//        Log.v(TAG, player.getPosition() + " = " + redValue + "," + greenValue + "," + blueValue);
        if (closeMatch(Color.argb(1, 136, 0, 21), pixel, 25)) {
            Log.v(TAG, "Collision detected");
        }
    }

    @Override
    public void cleanUp() {
        this.backgroundLayer.recycle();
        this.bgLayer.recycle();
    }

    @Override
    public boolean onKeyEvent(View v, int keyCode, KeyEvent keyEvent) {
        return true;
    }

    @Override
    public boolean onTouchEvent(View v, MotionEvent event) {
        boolean touchAction = false;
        boolean collisionDetected = false;


        //Test collision
        FixedScrollableLayer layer = (FixedScrollableLayer) getBackgroundLayer(0); //(FixedScrollableLayer) backgroundNode.getBackgrounds().get(0);
        Bitmap bitmap = layer.getBackground();
        int pixel = bitmap.getPixel((int) player.getPosition().x, (int) player.getPosition().y);
        collisionDetected = closeMatch(Color.argb(1, 136, 0, 21), pixel, 25);
        if (collisionDetected) {
            textViewColl.setVisibility(View.VISIBLE);
        } else {
            textViewColl.setVisibility(View.INVISIBLE);
        }

        Log.v("SideScrollerState", "touchevent");
        if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {
            Log.d("TouchTest:Gameplay", "Touch down");
            touchAction = true;
        } else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
            Log.d("TouchTest:Gameplay", "Touch up");
            touchAction = false;

            if (collisionDetected) {
                addExplosion = true;
            }

        }

        PointF pos = player.getPosition();
        if (event.getX() < RetroEngine.W / 2) {
            // nach links steuern
            pos.set(pos.x - 10, pos.y);
            player.setPosition(pos);

        } else {
            pos.set(pos.x + 10, pos.y);
            player.setPosition(pos);
//            scrollWorld = true;
        }


        return true;
    }


}
