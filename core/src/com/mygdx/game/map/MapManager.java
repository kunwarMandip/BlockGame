package com.mygdx.game.map;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.GlobalVariables;
import com.mygdx.game.map.objects.EnemyRectangleSpawnArea;
import com.mygdx.game.map.objects.EnemySpawnArea;


/**
 * Responsible for loading all TiledMap map objects
 * Creating layers for Tiled Map tile layer
 */
public class MapManager {

    private final LoadMapObjects loadMapObjects;
    private Array<EnemyRectangleSpawnArea> spawnAreaList;
    private final TileLayerManager tileLayerManager;

    private int[] upperTiles;
    private int[] lowerTiles;
    /**
     * Enables dynamic loading of tile layers to allow for different layers
     * Allows some layers to be displayed first or later, to create Z-index(see CSS)
     * @param world box2D world
     * @param tiledMap TiledMap
     */
    public MapManager(World world, TiledMap tiledMap){
        System.out.println("INIT MapManager...");

        tileLayerManager= new TileLayerManager(tiledMap);
        tileLayerManager.setNewTiles("GreenRed");
        upperTiles=tileLayerManager.getCurrentUpperTile();
        lowerTiles=tileLayerManager.getCurrentLowerTiles();

        loadMapObjects = new LoadMapObjects(world, tiledMap);

    }



    public void update(){
        int tempScore=GlobalVariables.SCORE;

        if(tempScore!=0 && tempScore%2==0){
            tileLayerManager.setNewTiles("GreenCyan");
            upperTiles=tileLayerManager.getCurrentUpperTile();
            lowerTiles=tileLayerManager.getCurrentLowerTiles();
        }
    }

    public Array<EnemySpawnArea> getSpawnAreaList(){
        return loadMapObjects.getSpawnAreaList();
    }

    public int[] getUpperTiles(){
        return upperTiles;
    }

    public int[] getLowerTiles(){
        return lowerTiles;
    }

    public TileLayerManager getTileLayerManager(){
        return tileLayerManager;
    }

}
