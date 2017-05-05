package net.offbeatpioneer.demoapp.retrographicsengine.examples.sprites;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import net.offbeatpioneer.demoapp.retrographicsengine.Player;
import net.offbeatpioneer.demoapp.retrographicsengine.ResManager;
import net.offbeatpioneer.demoapp.retrographicsengine.helper.ExplosionCreator;
import net.offbeatpioneer.demoapp.retrographicsengine.helper.PlayerCreator;
import net.offbeatpioneer.demoapp.retrographicsengine.sprites.Debris;
import net.offbeatpioneer.retroengine.auxiliary.background.ParallaxLayer;
import net.offbeatpioneer.retroengine.core.GameFont;
import net.offbeatpioneer.retroengine.core.RetroEngine;
import net.offbeatpioneer.retroengine.core.animation.AnimationSuite;
import net.offbeatpioneer.retroengine.core.animation.IAnimationSuiteListener;
import net.offbeatpioneer.retroengine.core.animation.RelativeLinearTranslation;
import net.offbeatpioneer.retroengine.core.animation.RotationAnimation;
import net.offbeatpioneer.retroengine.core.animation.ScaleAnimation;
import net.offbeatpioneer.retroengine.core.sprites.AnimatedSprite;
import net.offbeatpioneer.retroengine.core.sprites.decorator.TextElement;
import net.offbeatpioneer.retroengine.core.sprites.simple.CircleSprite;
import net.offbeatpioneer.retroengine.core.sprites.simple.RectangleSprite;
import net.offbeatpioneer.retroengine.core.sprites.simple.TriangleSprite;
import net.offbeatpioneer.retroengine.core.states.State;
import net.offbeatpioneer.retroengine.core.util.MathUtils;

//TODO add action events for some sprite and display toast
/**
 * Example showing different types of sprite and combination of them.
 * Also the use of animation is shown here.
 *
 * @author Dominik Grzelak
 * @since 13.01.2017.
 */

public class BasicSpriteExample extends State {

    Player player;
    CircleSprite circle;

    public BasicSpriteExample() {
        super();
        setStateName("BasicSpriteExample");
    }

    @Override
    public void init() {

        ParallaxLayer backgroundLayer = new ParallaxLayer(ResManager.PARALAYER_STAR_1, 0.6f);
        ParallaxLayer bgLayer = new ParallaxLayer(ResManager.BACKGROUND_STAR_2, 0.7f);
//        FixedScrollableLayer bgLayer = new FixedScrollableLayer(ResManager.BG_LEVEL_1, 500, 0.7f);
        player = PlayerCreator.create();


        addBackgroundLayer(bgLayer);
        addBackgroundLayer(backgroundLayer);
        setReferenceSprite(player);
        circle = new CircleSprite();
        circle.initWithRadius(50, RetroEngine.W / 2, RetroEngine.H / 2 - 50);

        ScaleAnimation scaleAnimation = new ScaleAnimation(0.1f, 1f, 4000);
        scaleAnimation.setLoop(true);
//        circle.addAnimation(scaleAnimation);
//        circle.beginAnimation();
//        circle.setColor(Color.BLUE);
//        AlphaValueTransition valueTransition = new AlphaValueTransition(0, 255, 2000);
//        valueTransition.setLoop(true);
//        circle.addAnimation(valueTransition);

//        RelativeLinearTranslation translation = new RelativeLinearTranslation(new PointF(RetroEngine.W / 2, RetroEngine.H / 2), 4000);
//        translation.setLoop(true);
//        circle.addAnimation(translation);
//        circle.beginAnimation();

//        addSprite(circle);
//        rootGroup.add(player);

        generateEnvironment();
        createTextSprites();
    }

    private void createTextSprites() {
        TextElement text1 = new TextElement("Test");
        text1.init(new PointF(0, 0));
        addSprite(text1);


        RectangleSprite rectangleSprite = new RectangleSprite();
        rectangleSprite.init(new PointF(text1.getTextWidth(), 0), text1.getTextWidth(), text1.getTextHeight());
        addSprite(rectangleSprite);

        CircleSprite circleSprite = new CircleSprite(Color.BLUE);
        circleSprite.initWithRadius(20, RetroEngine.W/2, RetroEngine.H/2);
        addSprite(circleSprite);

        TriangleSprite triangleSprite = new TriangleSprite(Color.GREEN);
        triangleSprite.initWithLength(200, new PointF(RetroEngine.W - 200, 0));
        addSprite(triangleSprite);
    }

    private void generateEnvironment() {
//        if (Math.random() < 0.07) {
        Debris debri = new Debris();
        int x = MathUtils.getRandomBetween(50, RetroEngine.W - 150);
        int y = MathUtils.getRandomBetween(50, RetroEngine.H - 150);
        PointF posRandom = new PointF(x, y);

        PointF p = getViewportOrigin();//backgroundNode.getViewportOrigin();
//            p.y += MathUtils.getRandomBetween(0, RetroEngine.H);
        debri.initAsAnimation(ResManager.RUNNUNG_GRANT, 79, 42, 20, 12, p, true);
        debri.setViewportOrigin(p);
        debri.setPosition(new PointF(100, 100));

        RotationAnimation rot = new RotationAnimation(0, 360, 4000);
        rot.setLoop(true);
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.1f, 2.5f, 4000);
        scaleAnimation.setLoop(true);
        scaleAnimation.setListener(new IAnimationSuiteListener() {
            @Override
            public void onAnimationStart(AnimationSuite animationSuite) {
                System.out.println("Animation gestartet");
            }

            @Override
            public void onAnimationRepeat(AnimationSuite animationSuite) {
                System.out.println("Animation wiederholt sich!");
//                animationSuite.getAnimatedSprite().setActive(true);
            }

            @Override
            public void onAnimationEnd(AnimationSuite animationSuite) {
                System.out.println("Animation beendet");
            }
        });
        debri.addAnimation(scaleAnimation);
        debri.addAnimation(rot);
        debri.beginAnimation();


        AnimatedSprite debris1 = new AnimatedSprite();
        debris1.initAsAnimation(ResManager.DEBRIS_1, 64, 61, 6, 6, new PointF(300, 200), true);
        debris1.setViewportOrigin(p);
//        debri.setAngle(90);
        addSprite(debris1);


        ScaleAnimation scaleAnimation1 = new ScaleAnimation(1, 1.5f, 800);
        scaleAnimation1.setLoop(true);
//        TextElement text = (TextElement) SpriteFactory.createTextSprite("Hello, World", new PointF(100, 200));
//        EmptySprite empty = new EmptySprite();
//        empty.init(ResManager.EMPTY, new PointF(300, 400));

        TextElement text = new TextElement(debris1); //new TextElement("Hallo, Welt", new PointF(300, 400));
        GameFont font = new GameFont(34); //(TextElement) SpriteFactory.createTextSprite("Hello, World", new PointF(100, 200));
        text.setFont(font);
        text.initWithText("Asteroid");
        text.addAnimation(scaleAnimation1);
//        text.beginAnimation();

        PointF endPosition = new PointF(0, -130);
        RelativeLinearTranslation relativeLinearTranslation = new RelativeLinearTranslation(endPosition, 1500);
        relativeLinearTranslation.setLoop(true);

        text.addAnimation(relativeLinearTranslation);
        text.beginAnimation();
//        debris1.addAnimation(relativeLinearTranslation);
//        debris1.beginAnimation();
//        GameFont font = new GameFont(18);
//        text.setFont(font);
        addSprite(text);
////        AnimatedSprite sprite = (AnimatedSprite) ExplosionCreator.getRandomExplosion(p);
//        AnimatedSprite sprite = (AnimatedSprite) ExplosionCreator.getDebris(p);
//        sprite.setViewportOrigin(backgroundNode.getViewportOrigin());


//            AnimatedSprite sprite = SpriteAnimatorFactory.createAnimatedSprite(p, new PointF(RetroEngine.W, RetroEngine.H/2), 8000);

        addSprite(debri);
        int x2 = MathUtils.getRandomBetween(50, RetroEngine.W - 150);
        int y2 = MathUtils.getRandomBetween(50, RetroEngine.H - 150);
        PointF p2 = new PointF(x2, y2);
        addSprite(ExplosionCreator.getRandomExplosion(p2));

    }

    @Override
    public void render(Canvas canvas, Paint paint, long currentTime) {
        //Zuerst der Hintergrund
//        drawBackground(canvas);

        canvas.drawColor(Color.WHITE);
        drawSprites(canvas, currentTime);

    }

    @Override
    public void updateLogic() {
//        backgroundNode.setReferencePoint(player.getPosition());//immer update player position
//        rootGroup.setViewportOrigin(backgroundNode.getViewportOrigin());

        updateSprites();
    }


    @Override
    public void cleanUp() {
        clearSprites();
    }

    @Override
    public boolean onKeyEvent(View v, int keyCode, KeyEvent keyEvent) {
        return true;
    }

    @Override
    public boolean onTouchEvent(View v, MotionEvent event) {
//        PointF p = player.getPosition();
//        PointF np = new PointF(p.x + 10, p.y);
//        player.setPosition(p);
        return true;
    }


}
