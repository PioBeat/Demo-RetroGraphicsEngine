package net.offbeatpioneer.demoapp.retrographicsengine;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.util.Log;

import net.offbeatpioneer.demoapp.R;

/**
 * Klasse f�r die Verwaltung aller Bild-Ressourcen. �ber entsprechende Methoden werden s�mtliche ben�tigten Bilder geladen
 * und stehen �ber statische Objekte zur Verf�gung.
 *
 * @author Dominik Grzelak
 */
public class ResManager {

    public static Bitmap EMPTY;
    public static Bitmap PLAYER_ANIMATION;

    public static Bitmap BULLET;

    public static Bitmap BACKGROUND_STAR_2;
    public static Bitmap BACKGROUND_STAR_3;

    public static Bitmap COMPASS;


    public static Bitmap PARALAYER_STAR_1;
    public static Bitmap DEBRIS_1;

    public static Bitmap MAIN_SPLASH_LOGO;

    public static Bitmap ENEMY_FIGHTER;
    public static Bitmap ENEMY_FIGHTER2;
    public static Bitmap ENEMY_FIGHTER3;
    public static Bitmap ENEMY_MINE;
    public static Bitmap ENEMY_MINE_DESTROYED;

    public static Bitmap ITEM_SPEEDFIRE;
    public static Bitmap ITEM_MOREENEMIES;
    public static Bitmap ITEM_SLOWPLAYER;
    public static Bitmap ITEM_FASTPLAYER;
    public static Bitmap ITEM_MORELIFE;
    public static Bitmap ITEM_DOUBLECANON;

    public static Bitmap EXPLOSION;
    public static Bitmap EXPLOSION_BIG;
    public static Bitmap EXPLOSION_BIG2;

    public static Bitmap BG_LEVEL_1;
    public static Bitmap RUNNUNG_GRANT;


    //ICONS
    /*
    @Deprecated
	public static Bitmap ICON_PLAY;
	@Deprecated
	public static Bitmap ICON_AUDIO;
	@Deprecated
	public static Bitmap ICON_HEART;
*/

    /**
     * Methode l�dt s�mtliche Bilder f�r das Spiel.
     *
     * @param res
     */
    public static void initImages(Resources res) {

        // ask the bitmap factory not to scale the loaded bitmaps
        BitmapFactory.Options opts = new BitmapFactory.Options();

        //opts.inDensity = 0;
        //opts.inScaled = false;
        Log.v("Screen", "" + res.getDisplayMetrics().widthPixels + " " + res.getDisplayMetrics().heightPixels);
        if (res.getDisplayMetrics().widthPixels <= 450 || res.getDisplayMetrics().heightPixels <= 350) {
            opts.inSampleSize = 2;
        }
        opts.inScaled = false;
        //res.

        PLAYER_ANIMATION = BitmapFactory.decodeResource(res,
                R.drawable.player, opts);

        BULLET = BitmapFactory.decodeResource(res,
                R.drawable.bullets, opts);

        ENEMY_MINE = BitmapFactory.decodeResource(res,
                R.drawable.mine, opts);
        ENEMY_MINE_DESTROYED = BitmapFactory.decodeResource(res,
                R.drawable.mine_destroyed, opts);

        ENEMY_FIGHTER = BitmapFactory.decodeResource(res,
                R.drawable.fighter, opts);
        ENEMY_FIGHTER2 = BitmapFactory.decodeResource(res,
                R.drawable.fighter2, opts);
        ENEMY_FIGHTER3 = BitmapFactory.decodeResource(res,
                R.drawable.fighter3, opts);

        BACKGROUND_STAR_2 = BitmapFactory.decodeResource(res, R.drawable.star1);
        BACKGROUND_STAR_3 = BitmapFactory.decodeResource(res, R.drawable.bghighscore);
        BG_LEVEL_1 = BitmapFactory.decodeResource(res, R.drawable.level1);

        PARALAYER_STAR_1 = BitmapFactory.decodeResource(res,
                R.drawable.paralayer_star1);

        MAIN_SPLASH_LOGO = BitmapFactory.decodeResource(res,
                R.drawable.mainlogo);


        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Config.ARGB_8888;
        opt.inScaled = false;

        EXPLOSION = BitmapFactory.decodeResource(res,
                R.drawable.explode_3, opt);

        EXPLOSION_BIG = BitmapFactory.decodeResource(res,
                R.drawable.explosion_big, opt);

        EXPLOSION_BIG2 = BitmapFactory.decodeResource(res,
                R.drawable.explode_big);

        ITEM_SPEEDFIRE = BitmapFactory.decodeResource(res,
                R.drawable.bullets2x, opts);

        ITEM_MOREENEMIES = BitmapFactory.decodeResource(res,
                R.drawable.moreenemies, opts);
        ITEM_SLOWPLAYER = BitmapFactory.decodeResource(res,
                R.drawable.itemslowplayer, opts);
        ITEM_FASTPLAYER = BitmapFactory.decodeResource(res,
                R.drawable.itemfastplayer, opts);

        ITEM_MORELIFE = BitmapFactory.decodeResource(res,
                R.drawable.itemmorelife, opts);
        ITEM_DOUBLECANON = BitmapFactory.decodeResource(res,
                R.drawable.doublecanon, opts);

        DEBRIS_1 = BitmapFactory.decodeResource(res,
                R.drawable.debris1, opts);


        RUNNUNG_GRANT = BitmapFactory.decodeResource(res,
                R.drawable.runninggrant, opts);

        EMPTY = BitmapFactory.decodeResource(res, R.drawable.empty, opts);
		/*
		
		ICON_PLAY =  BitmapFactory.decodeResource(res,
				R.drawable.input_gaming);
		ICON_AUDIO =  BitmapFactory.decodeResource(res,
				R.drawable.audio_x_generic);
		
		ICON_HEART =  BitmapFactory.decodeResource(res,
				R.drawable.emblem_favorite);
		*/
    }
}
