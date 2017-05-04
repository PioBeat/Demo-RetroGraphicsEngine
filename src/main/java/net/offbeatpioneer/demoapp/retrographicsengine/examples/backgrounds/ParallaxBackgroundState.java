package net.offbeatpioneer.demoapp.retrographicsengine.examples.backgrounds;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.Log;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import net.offbeatpioneer.demoapp.retrographicsengine.Player;
import net.offbeatpioneer.demoapp.retrographicsengine.ResManager;
import net.offbeatpioneer.demoapp.retrographicsengine.helper.PlayerCreator;
import net.offbeatpioneer.demoapp.retrographicsengine.helper.SpriteFactory;
import net.offbeatpioneer.retroengine.auxiliary.background.ParallaxLayer;
import net.offbeatpioneer.retroengine.core.RetroEngine;
import net.offbeatpioneer.retroengine.core.animation.AbsoluteSingleNodeLinearTranslation;
import net.offbeatpioneer.retroengine.core.sprites.AbstractSprite;
import net.offbeatpioneer.retroengine.core.sprites.AnimatedSprite;
import net.offbeatpioneer.retroengine.core.sprites.SpriteListGroup;
import net.offbeatpioneer.retroengine.core.states.State;

/**
 * Example showing how to create a parallax background with a sprite as an actor (player)
 *
 * @author Dominik Grzelak
 */
public class ParallaxBackgroundState extends State {

    Player player;
    SpriteListGroup obstacles = new SpriteListGroup();

    public ParallaxBackgroundState() {
        super();
        setStateName("ParallaxBackgroundState");
    }

    @Override
    public void init() {
        
        ParallaxLayer bgLayer = new ParallaxLayer(ResManager.BACKGROUND_STAR_2, 1f);
        ParallaxLayer backgroundLayer = new ParallaxLayer(ResManager.PARALAYER_STAR_1, 0.6f);
        player = PlayerCreator.create();

        addBackgroundLayer(bgLayer);
        addBackgroundLayer(backgroundLayer);
        setReferenceSprite(player);

//        drawBackground(new Canvas());
        AnimatedSprite sprite = new AnimatedSprite();
        PointF o = getViewportOrigin();
//        sprite.initAsAnimation(ResManager.DEBRIS_1, 64, 61, 6, 6, new PointF(RetroEngine.W / 2, RetroEngine.H / 2), true);
        sprite.init(ResManager.ITEM_DOUBLECANON, new PointF(o.x + 0, o.y + 0));
        sprite.setViewportOrigin(getViewportOrigin());
        sprite.setAutoDestroy(false);
        sprite.setActive(true);
        sprite.setDisable(false);

        addSprite(player);

        addSprite(obstacles);

        obstacles.add(sprite);
    }

    @Override
    public void render(Canvas canvas, Paint paint, long currentTime) {
        drawBackground(canvas);
        drawSprites(canvas, currentTime);
    }

    @Override
    public void updateLogic() {

        setReferenceSprite(player);
//        System.out.println("MAIN: " + getViewportOrigin().x + " // " + getViewportOrigin().y);
        updateSprites();

//        System.out.println(obstacles.getChildren().get(0).getViewportOrigin().x + " // " + obstacles.getChildren().get(0).getViewportOrigin().y);
    }


    @Override
    public void cleanUp() {

    }

    @Override
    public boolean onKeyEvent(View v, int keyCode, KeyEvent keyEvent) {
        return true;
    }

    private SparseArray<PointF> mActivePointers = new SparseArray<PointF>();
    ;
    boolean twoTouch = false;

    private boolean touchAction = false;

    @Override
    public boolean onTouchEvent(View v, MotionEvent motionEvent) {
        if (motionEvent.getAction() == android.view.MotionEvent.ACTION_DOWN) {
            Log.d("TouchTest:Gameplay", "Touch down");
            touchAction = true;
        } else if (motionEvent.getAction() == android.view.MotionEvent.ACTION_UP) {
            Log.d("TouchTest:Gameplay", "Touch up");
            touchAction = false;
            AbstractSprite plusOne = bla(AbsoluteSingleNodeLinearTranslation.Direction.BOTTOMCENTER);
            addSprite(plusOne);
            AbstractSprite plusOne1 = bla(AbsoluteSingleNodeLinearTranslation.Direction.TOPCENTER);
            addSprite(plusOne1);
            AbstractSprite plusOne2 = bla(AbsoluteSingleNodeLinearTranslation.Direction.CENTERRIGHT);
            addSprite(plusOne2);
            AbstractSprite plusOne3 = bla(AbsoluteSingleNodeLinearTranslation.Direction.CENTERLEFT);
            addSprite(plusOne3);
        }
        player.setTouch(motionEvent.getX(), motionEvent.getY());
        player.TOUCH_ACTION = touchAction;
        return false;
    }

    public AbstractSprite bla(AbsoluteSingleNodeLinearTranslation.Direction direction) {
        AnimatedSprite plusOne = SpriteFactory.createPlusOne(player.getPosition());
        AbsoluteSingleNodeLinearTranslation linearTranslation2 = new AbsoluteSingleNodeLinearTranslation(player, direction, 1000);
        plusOne.addAnimation(linearTranslation2);
        plusOne.beginAnimation();
        plusOne.onAction(null);
        return plusOne;
    }

    public PointF getTopRightCorner() {
        float x = player.getPosition().x + (RetroEngine.W / 2);
        float y = player.getPosition().y - (RetroEngine.H / 2);
        return new PointF(x, y);
    }

}
