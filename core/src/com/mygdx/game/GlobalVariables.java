package com.mygdx.game;

public class GlobalVariables {

    public static int SCORE=0;
    public static boolean READY_TO_SPAWN=false;

    //9:18 ratio to ensure it fits on every device
    //Since game is PORTRAIT mode only, the height is longer than width
    public static final float VIRTUAL_WIDTH = 432;
    public static final float VIRTUAL_HEIGHT = 864;

    //To scale Box2D objects. Read more on readme.md
    public static final float PPM=16;

    //Name of Object in tiledMap where the enemy are supposed to be spawned from
    public static final String ENEMY_SPAWN_OBJECT_NAME ="spawnArea";

    //CATEGORY BITS FOR CONTACT MANAGER
    public static final short CATEGORY_PLAYER = 0x0001;
    public static final short CATEGORY_ENEMY  = 0x0002;
    public static final short CATEGORY_WALL   = 0x0004;
    public static final short CATEGORY_ENEMY_SPAWN_AREA   = 0x0008;
    public static final short CATEGORY_OUTER_BOUND  = 0x00016;

}