package com.mygdx.fallingblocks;

import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class GlobalStaticVariables {

    //9:18 RATIO. PORTRAIT MODE ONLY
    public static final float PPM                   = 16;
    public static final float VIRTUAL_WIDTH         = 720;
    public static final float VIRTUAL_HEIGHT        = 1440;


    //CONTACT MANAGER --- SET WHICH EACH CAN COLLIDE WITH
    public static final short CATEGORY_PLAYER           = 0x0001;
    public static final short CATEGORY_ENEMY            = 0x0002;
    public static final short CATEGORY_WALL             = 0x0004;
    public static final short CATEGORY_ENEMY_SPAWN_AREA = 0x0008;
    public static final short CATEGORY_OUTER_BOUND      = 0x00016;

    //Player Info
    public static final float PLAYER_MOVEMENT_SPEED         = 30f;
    public static final int PLAYER_COLOR_CHANGE_INTERVAL    = 5;

    //Enemy Info...Increasing difficulty
    public static final float ENEMY_MOVEMENT_SPEED_TINY_INCREASE = 0.5f;
    public static final float ENEMY_MOVEMENT_SPEED_HUGE_INCREASE = 3f;
    public static final Vector2 ENEMY_BASE_MOVEMENT_SPEED = new Vector2(0.5f, 0.5f);

    //Most enemies that can be in the map at the same time
    public static final int MAX_ENEMY_ALLOWED                       = 5;
    public static final int[] INCREASE_MAX_ENEMY_ALLOWED_THRESHOLDS = {1, 15, 20, 40};

    //Choose if enemy and player should have the same color
    public static final int MIN_LUCKY_REWARD_THRESHOLD          = 5;
    public static final int MAX_LUCKY_REWARD_THRESHOLD          = 10;
    public static final int TEXTURE_CHOOSER_LUCK_PROBABILITY    = 101;

    public static final String[] COLOR_ARRAY={"#FF0000", "#FF5733", "#33FF57", "#FF33A6", "#33FFF3", "#FFDB33",
                                                "#8A33FF", "#FF8633", "#33FF8A"};

    private static final Random random= new Random();

    /**
     * Get random integer between upperBound and lowerBound
     * @param upperBound highest int value possible
     * @param lowerBound lowest int value possible
     * @return random int between upper and lower bound
     */
    public static int GET_RANDOM_INTEGER(int upperBound, int lowerBound){
        return random.nextInt(upperBound - lowerBound) + lowerBound;
    }

    /**
     * Get random integer between 0 and upperBound
     * @param upperBound highest int value possible
     * @return random int between 0 and upperBound
     */
    public static int GET_RANDOM_INTEGER(int upperBound){
        return random.nextInt(upperBound);
    }


}