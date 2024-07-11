package com.mygdx.game.map;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.map.objects.EnemyRectangleSpawnArea;
import com.mygdx.game.map.objects.EnemySpawnArea;


/**
 * Responsible for loading all TiledMap map objects
 * Creating layers for Tiled Map tile layer
 */
public class MapManager {

    private final LoadMapObjects staticMapObjects;
    private Array<EnemyRectangleSpawnArea> spawnAreaList;
    private final TileLayerManager tileLayerManager;

    private int[] upperTiles;
    private int[] lowerTiles;
    /**
     * Enables dynamic loading of tile layers to allow for different layers
     * Allows some layers to be displayed first or later, to create Z-index(see CSS)
     * @param world box2D world
     * @param tiledMap TiledMap
     * @param gameCamera to get correct position for the spawn Area
     */
    public MapManager(World world, TiledMap tiledMap){
        System.out.println("INIT MapManager...");
        //    private final ShapeRenderer shapeRenderer;

        tileLayerManager= new TileLayerManager(tiledMap);
        tileLayerManager.setNewTiles("GreenRed");
        upperTiles=tileLayerManager.getCurrentUpperTile();
        lowerTiles=tileLayerManager.getCurrentLowerTiles();

//        defineLowerUpperTileLayers(tiledMap);
        staticMapObjects = new LoadMapObjects(world, tiledMap);

    }



    public void update(){
    }

    public Array<EnemySpawnArea> getSpawnAreaList(){
        return staticMapObjects.getSpawnAreaList();
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
