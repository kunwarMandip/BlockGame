package com.mygdx.game.map;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.GlobalVariables;
import com.mygdx.game.map.objects.EnemyRectangleSpawnArea;
import com.mygdx.game.map.objects.EnemySpawnArea;

import java.util.Random;


/**
 * Responsible for loading all TiledMap map objects
 * Creating layers for Tiled Map tile layer
 */
public class MapManager {

    private final LoadMapObjects loadMapObjects;
    private Array<EnemyRectangleSpawnArea> spawnAreaList;
    private final TileLayersManager tileLayersManager;
    private int[] upperTiles;
    private int[] lowerTiles;

    private final Random random;
    private int lastChangeScore;

    /**
     * Enables dynamic loading of tile layers to allow for different layers
     * Allows some layers to be displayed first or later, to create Z-index(see CSS)
     * @param world box2D world
     * @param tiledMap TiledMap
     */
    public MapManager(World world, TiledMap tiledMap){
        System.out.println("INIT MapManager...");

        tileLayersManager = new TileLayersManager(tiledMap);
        tileLayersManager.setNewTiles("CyanLightBlue");
        upperTiles= tileLayersManager.getCurrentUpperTile();
        lowerTiles= tileLayersManager.getCurrentLowerTiles();
        loadMapObjects = new LoadMapObjects(world, tiledMap);
        this.random=new Random();
    }


    public void update(){
        int tempScore=GlobalVariables.SCORE;
        String newPrimaryColors;
        if(tempScore%2==0 && tempScore!=0 && lastChangeScore!=tempScore){

            do{
                int randomIndex= random.nextInt(tileLayersManager.getTileGroups().size);
                newPrimaryColors=tileLayersManager.getTileGroups().get(randomIndex).getPrimaryColorName();

            }while(newPrimaryColors.equals(tileLayersManager.getCurrentPrimaryColorsName()));

            tileLayersManager.setNewTiles(newPrimaryColors);
            upperTiles= tileLayersManager.getCurrentUpperTile();
            lowerTiles= tileLayersManager.getCurrentLowerTiles();
            lastChangeScore=tempScore;
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

    public TileLayersManager getTileLayerManager(){
        return tileLayersManager;
    }

}
