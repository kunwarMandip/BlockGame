package com.mygdx.fallingblocks;

import com.badlogic.gdx.math.Vector2;

public class StaticVariables {

    //9:18 ratio to ensure it fits on every device
    //Since game is PORTRAIT mode only, the height is longer than width
    public static final float PPM=16;
    public static final float VIRTUAL_WIDTH = 720;
    public static final float VIRTUAL_HEIGHT = 1440;

    //CATEGORY BITS FOR CONTACT MANAGER
    public static final short CATEGORY_PLAYER = 0x0001;
    public static final short CATEGORY_ENEMY  = 0x0002;
    public static final short CATEGORY_WALL   = 0x0004;
    public static final short CATEGORY_ENEMY_SPAWN_AREA   = 0x0008;
    public static final short CATEGORY_OUTER_BOUND  = 0x00016;

    //Player Info
    public static final float PLAYER_SPEED=30f;

    //Enemy Info...Increasing difficulty
    public static final Vector2 ENEMY_BASE_SPEED = new Vector2(0.5f, 0.5f);
    public static final float ENEMY_STEADY_SPEED_INCREASE =0.1f;
    public static final float ENEMY_BOOST_SPEED_INCREASE =2.5f;

    //Most enemies that can be in the map at the same time
    public static final int[] scoreThresholds= {2, 15, 20, 40};
    public static final int MAX_ENEMY_THRESHOLD =5;


    //Choose if enemy and player should have the same color
    public static final int TEXTURE_CHOOSER_BOUNDS=101;
    public static final int MIN_LUCKY_REWARD_THRESHOLD=10;
    public static final int MAX_LUCKY_REWARD_THRESHOLD= 15;

    public static final int PLAYER_COLOR_CHANGE_INTERVAL=5;

}