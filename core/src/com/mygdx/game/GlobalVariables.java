package com.mygdx.game;

import com.badlogic.gdx.math.Rectangle;

public class GlobalVariables {
    public static int score=0;


    //9:18 ratio to ensure it fits on every device
    //Since game is PORTRAIT mode only, the height is longer than width
    public static final float VIRTUAL_WIDTH = 720;
    public static final float VIRTUAL_HEIGHT = 1440;
    //see description in Readme.md
    public static final float PPM=100;
    public static final String enemySpawnObjectName="spawnArea";

}