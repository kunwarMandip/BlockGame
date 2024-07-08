package com.mygdx.game.map;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;
import java.util.regex.*;


/**
 * Responsible for loading all TiledMap map objects
 * Creating layers for Tiled Map tile layer
 */
public class MapManager {

    private final ShapeRenderer shapeRenderer;
    private final OrthographicCamera gameCamera;

    private final StaticMapObjects staticMapObjects;
    private DynamicMapObjects dynamicMapObjects;
    private final SpawnArea spawnArea;

    //Set which layer should be drawn before box2D and which ones after
    private final int[] upperTiles;
    private final int[] lowerTiles;

    /**
     * Enables dynamic loading of tile layers to allow for different layers
     * Allows some layers to be displayed first or later, to create Z-index(see CSS)
     * @param world box2D world
     * @param tiledMap TiledMap
     * @param shapeRenderer for debugging purposes only
     * @param gameCamera to get correct position for the spawn Area
     */
    public MapManager(World world, TiledMap tiledMap, ShapeRenderer shapeRenderer, OrthographicCamera gameCamera){
        this.shapeRenderer=shapeRenderer;
        this.gameCamera=gameCamera;

        //Below code separates EACH tile map layer into upper and lower tiles
        //depending on when they should be rendered
        System.out.println("INIT MapManager...");
        ArrayList<Integer> upperTileLayers = new ArrayList<>();
        ArrayList<Integer> lowerTileLayers=  new ArrayList<>();

        MapLayers mapLayers= tiledMap.getLayers();
        for (MapLayer layer : tiledMap.getLayers()) {

            String layerName = layer.getName();
            System.out.println("Checking Layer Name: " + layerName);
            Pattern pattern = Pattern.compile("top");
            Matcher matcher = pattern.matcher(layerName);

            if (matcher.find()) {
                upperTileLayers.add(mapLayers.getIndex(layerName));
                System.out.println("Top Layer Found: " + layerName);
            } else {
                lowerTileLayers.add(mapLayers.getIndex(layerName));
                System.out.println("Bottom Layer Found: " + layerName);
            }
        }

        //Convert Arraylist into Array since render takes Array
        upperTiles = upperTileLayers.stream().mapToInt(Integer::intValue).toArray();
        lowerTiles = lowerTileLayers.stream().mapToInt(Integer::intValue).toArray();

        staticMapObjects = new StaticMapObjects(world, tiledMap);
        spawnArea= new SpawnArea(tiledMap);
        System.out.println("Map Manager Init Finished");
    }

    public void update(){
        spawnArea.draw(shapeRenderer, gameCamera);
    }

    public void draw(SpriteBatch spriteBatch){
        dynamicMapObjects.draw(spriteBatch);
        staticMapObjects.draw(spriteBatch);
    }

    public int[] getUpperTiles(){
        return upperTiles;
    }
    public int[] getLowerTiles(){
        return lowerTiles;
    }

}
