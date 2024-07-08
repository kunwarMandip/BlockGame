package com.mygdx.game;


import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;

public class GlobalVariables {
    public static int score=0;

    //9:18 ratio to ensure it fits on every device
    //Since game is PORTRAIT mode only, the height is longer than width
    public static final float VIRTUAL_WIDTH = 432;
    public static final float VIRTUAL_HEIGHT = 864;

    //To scale Box2D objects. Read more on readme.md
    public static final float PPM=16;

    //Name of Object in tiledMap where the enemy are supposed to be spawned from
    public static final String enemySpawnObjectName="spawnArea";


    /**
     * //Given a String, check if a corresponding layer can be found
     * @param map the map file to search within
     * @param layerName the name of the layer
     * @return true if found, false if not
     */
    public static boolean checkLayer(TiledMap map, String layerName){
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