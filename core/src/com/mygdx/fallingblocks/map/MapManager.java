package com.mygdx.fallingblocks.map;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.fallingblocks.GameStateVariables;

import java.util.Random;


/**
 * Responsible for loading all TiledMap map objects
 * Creating layers for Tiled Map tile layer
 */
public class MapManager {

    private int[] upperTiles;
    private int[] lowerTiles;
    private final TileLayersManager tileLayersManager;

    Random random=new Random();

    /**
     * Enables dynamic loading of tile layers to allow for different layers
     * Allows some layers to be displayed first or later, to create Z-index(see CSS)
     * @param world box2D world
     * @param tiledMap TiledMap
     */
    public MapManager(World world, TiledMap tiledMap){
        System.out.println("INIT MapManager...");
        tileLayersManager = new TileLayersManager(tiledMap);
        upperTiles= tileLayersManager.getCurrentUpperTile();
        lowerTiles= tileLayersManager.getCurrentLowerTiles();
        new LoadMapObjects(world, tiledMap);
    }

    /**
     * If current score is divisible by 2, Changes the map to a new map
     * and gets its upper and lower tiles. Else, doesn't do anything at all
     */
    public void update(int score, int lastScore){
        if (score % 2 != 0 || score == 0 || lastScore == score) {
            return;
        }

        String newPrimaryColors=getRandomPrimaryColor();
        tileLayersManager.setNewTiles(newPrimaryColors);
        upperTiles= tileLayersManager.getCurrentUpperTile();
        lowerTiles= tileLayersManager.getCurrentLowerTiles();
    }

    private String getRandomPrimaryColor(){
        String newPrimaryColors;
        do{
            int randomIndex= random.nextInt(tileLayersManager.getTileGroups().size);
            newPrimaryColors=tileLayersManager.getTileGroups().get(randomIndex).getPrimaryColorName();

        }while(newPrimaryColors.equals(tileLayersManager.getCurrentPrimaryColorsName()));
        return newPrimaryColors;
    }

    public int[] getUpperTiles(){
        return upperTiles;
    }

    public int[] getLowerTiles(){
        return lowerTiles;
    }

}
