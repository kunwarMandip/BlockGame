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

    private final GameStateVariables gameStateVariables;
    private final TileLayersManager tileLayersManager;
    private int[] upperTiles;
    private int[] lowerTiles;

    private int lastChangeScore;

    /**
     * Enables dynamic loading of tile layers to allow for different layers
     * Allows some layers to be displayed first or later, to create Z-index(see CSS)
     * @param world box2D world
     * @param tiledMap TiledMap
     */
    public MapManager(World world, TiledMap tiledMap, GameStateVariables gameStateVariables){
        System.out.println("INIT MapManager...");
        this.gameStateVariables= gameStateVariables;

        tileLayersManager = new TileLayersManager(tiledMap);
        tileLayersManager.setNewTiles("CyanLightBlue");
        upperTiles= tileLayersManager.getCurrentUpperTile();
        lowerTiles= tileLayersManager.getCurrentLowerTiles();

        LoadMapObjects loadMapObjects = new LoadMapObjects(world, tiledMap);
    }

    /**
     * If current score is divisible by 2, Changes the map to a new map
     * and gets its upper and lower tiles. Else, doesn't do anything at all
     */
    public void update(){
        int tempScore= gameStateVariables.getScore();
        String newPrimaryColors;
        if(tempScore%2==0 && tempScore!=0 && lastChangeScore!=tempScore){
            Random random=new Random();
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


    public int[] getUpperTiles(){
        return upperTiles;
    }

    public int[] getLowerTiles(){
        return lowerTiles;
    }

}
