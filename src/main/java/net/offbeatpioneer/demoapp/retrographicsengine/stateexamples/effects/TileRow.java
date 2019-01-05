package net.offbeatpioneer.demoapp.retrographicsengine.stateexamples.effects;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.util.DisplayMetrics;

import net.offbeatpioneer.demoapp.R;
import net.offbeatpioneer.retroengine.core.RetroEngine;
import net.offbeatpioneer.retroengine.core.sprites.AnimatedSprite;
import net.offbeatpioneer.retroengine.core.sprites.SpriteListGroup;

/**
 * Repräsentiert eine Reihe von Tiles
 *
 * @author Dominik Grzelak
 * @since 07.11.2014
 */
public class TileRow extends SpriteListGroup {
    public static int DEFAULT_WIDTH_TILE = 64;
    public static int DEFAULT_HEIGHT_TILE = 64;

    private int widthTile;
    private int heightTile;

    private int widthScreen;
    private int heightScreen;
    private Resources resources;

    public TileRow(int widthTile, int heightTile, int widthScreen, int heightScreen, PointF position) {
        this.widthTile = widthTile;
        this.heightTile = heightTile;
        this.widthScreen = widthScreen;
        this.heightScreen = heightScreen;
        this.position = position;
        setHidden(false);
        setAutoDestroy(false);
        setActive(true);
    }

    public TileRow(int widthScreen, int heightScreen) {
        this(DEFAULT_WIDTH_TILE, DEFAULT_HEIGHT_TILE, widthScreen, heightScreen, new PointF(0, 0));
    }


    public void init() {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inScaled = true;
        opt.inDensity = DisplayMetrics.DENSITY_DEFAULT;
        opt.inTargetDensity = DisplayMetrics.DENSITY_DEFAULT;

        Bitmap bitmapTile = BitmapFactory.decodeResource(RetroEngine.Resources,
                R.drawable.tile_1,
                opt);
        //int orientation = resources.getConfiguration().orientation;
//        List<AnimatedSprite> tileImages = new ArrayList<AnimatedSprite>();
        int count = Math.round(widthScreen / widthTile);
        float startX = position.x;
        float startY = position.y;
        for (int i = 0; i <= (count + 1); i++) {
//            TileImage tileImage = new TileImage(BitmapManager.TILE);
            AnimatedSprite tileImage = new AnimatedSprite();
            tileImage.init(bitmapTile, new PointF(startX, startY));
            add(tileImage);
            startX += widthTile;
        }
    }

    public int getWidthTile() {
        return widthTile;
    }

    public void setWidthTile(int widthTile) {
        this.widthTile = widthTile;
    }

    public int getHeightTile() {
        return heightTile;
    }

    public void setHeightTile(int heightTile) {
        this.heightTile = heightTile;
    }

    public int getWidthScreen() {
        return widthScreen;
    }

    public void setWidthScreen(int widthScreen) {
        this.widthScreen = widthScreen;
    }

    public int getHeightScreen() {
        return heightScreen;
    }

    public void setHeightScreen(int heightScreen) {
        this.heightScreen = heightScreen;
    }
//
//    public Point getPosition() {
//        return position;
//    }
//
//    public void setPosition(Point position) {
//        this.position = position;
//    }
}
