package net.offbeatpioneer.demoapp.retrographicsengine.stateexamples.effects;

import android.graphics.PointF;

import net.offbeatpioneer.retroengine.core.sprites.SpriteListGroup;

/**
 * @author Dominik Grzelak
 * @since 08.11.2014
 */
public class TileMatrix extends SpriteListGroup {
    private int widthScreen;
    private int heightScreen;
//    private List<TileRow> tileMatrix;
//    private int direction; //Richtung, 1 = nach unten, -1 = nach oben

    public TileMatrix(int widthScreen, int heightScreen) {
        this.widthScreen = widthScreen;
        this.heightScreen = heightScreen;
        setDisabled(true);
        setActive(true);
        setPosition(new PointF(0, 0));
    }

    public void init() {
        float startY = getPosition().y - TileRow.DEFAULT_HEIGHT_TILE;
        int count = Math.round(heightScreen/TileRow.DEFAULT_HEIGHT_TILE);
        for(int i = 0; i <= count+2; i++) {
            TileRow tileRow = new TileRow(widthScreen, heightScreen);
            tileRow.setPosition(new PointF(getPosition().x, startY));
            tileRow.init();
            add(tileRow);
            startY += TileRow.DEFAULT_HEIGHT_TILE;
        }
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

}
