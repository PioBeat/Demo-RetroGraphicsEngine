package net.offbeatpioneer.demoapp.retrographicsengine.examples.backgrounds;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.Log;
import android.util.SparseArray;
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

/**
 * @author Dominik Grzelak
 */
public class SideScrollerState extends State {

    private Player player;
    TextView textViewColl;

    public SideScrollerState() {
        super();
        setStateName("SideScrollerState");
    }

    @Override
    public void init() {

        ParallaxLayer backgroundLayer = new ParallaxLayer(ResManager.PARALAYER_STAR_1, 0.6f);
        FixedScrollableLayer bgLayer = new FixedScrollableLayer(ResManager.BG_LEVEL_1, 500, 0.7f);
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
        updateSprites();

        //check
        FixedScrollableLayer layer = (FixedScrollableLayer) getBackgroundLayer(0); //(FixedScrollableLayer) backgroundNode.getBackgrounds().get(0);
        Bitmap bitmap = layer.getBackground();
        int pixel = bitmap.getPixel((int) player.getPosition().x, (int) player.getPosition().y);
        int redValue = Color.red(pixel);
        int blueValue = Color.blue(pixel);
        int greenValue = Color.green(pixel);
//        System.out.println(player.getPosition() + " = " + redValue + "," + greenValue + "," + blueValue);
        if (closeMatch(Color.argb(1, 136, 0, 21), pixel, 25) == true) {
            System.out.println("collision");
        }
    }

    /**
     * Test if two colours are the same
     *
     * @param color1
     * @param color2
     * @param tolerance
     * @return
     */
    public boolean closeMatch(int color1, int color2, int tolerance) {
        if ((int) Math.abs(Color.red(color1) - Color.red(color2)) > tolerance)
            return false;
        if ((int) Math.abs(Color.green(color1) - Color.green(color2)) > tolerance)
            return false;
        if ((int) Math.abs(Color.blue(color1) - Color.blue(color2)) > tolerance)
            return false;
        return true;
    }


    @Override
    public void cleanUp() {

    }

    @Override
    public boolean onKeyEvent(View v, int keyCode, KeyEvent keyEvent) {
        return true;
    }

    private SparseArray<PointF> mActivePointers = new SparseArray<PointF>();

    boolean twoTouch = false;

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

            if(collisionDetected) {
                PointF posEx = new PointF(player.getPosition().x + 50, player.getPosition().y);
                AnimatedSprite s = ExplosionCreator.getRandomExplosion(posEx);
                addSprite(s);
                s.setDisable(false);
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
