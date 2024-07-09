package com.mygdx.game;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;

public class GlobalVariables {
    public static int SCORE=0;

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
    public static final short CATEGORY_OUTER_BOUND  = 0x0008;


    public enum STATIC_TILED_MAP_OBJECTS{
        wall;
    }

    /**
     * Given a String, check if a corresponding layer can be found
     * @param map the map file to search within
     * @param layerName the name of the layer
     * @return true if found, false if not
     */
    public static boolean CHECK_LAYER(TiledMap map, String layerName){
        for (MapLayer layer : map.getLayers()) {
            if (layer.getName().equalsIgnoreCase(layerName)) {
                System.out.println("Map Layer: "+ layerName+ " found.");
                return true;
            }
        }
        System.out.println("Map Layer: "+ layerName +" not found.");
        return false;
    }

}