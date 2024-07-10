package com.mygdx.game.map;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.map.objects.EnemyRectangleSpawnArea;
import com.mygdx.game.map.objects.EnemySpawnArea;


import java.util.ArrayList;
import java.util.regex.*;


/**
 * Responsible for loading all TiledMap map objects
 * Creating layers for Tiled Map tile layer
 */
public class MapManager {

//    private final ShapeRenderer shapeRenderer;
    private final OrthographicCamera gameCamera;

    private final LoadMapObjects staticMapObjects;
    private Array<EnemyRectangleSpawnArea> spawnAreaList;

    //Set which layer should be drawn before box2D and which ones after
    private int[] upperTiles;
    private int[] lowerTiles;

    /**
     * Enables dynamic loading of tile layers to allow for different layers
     * Allows some layers to be displayed first or later, to create Z-index(see CSS)
     * @param world box2D world
     * @param tiledMap TiledMap
     * @param gameCamera to get correct position for the spawn Area
     */
    public MapManager(World world, TiledMap tiledMap, OrthographicCamera gameCamera){
        System.out.println("INIT MapManager...");
        this.gameCamera=gameCamera;

        defineLowerUpperTileLayers(tiledMap);
        staticMapObjects = new LoadMapObjects(world, tiledMap);
//        createEnemySpawnRectangle(tiledMap);
    }


    /**
     * Separates TiledMap Tile Layers to Upper and Lower to allow
     * dynamic rendering of the Tile Layers;
     * @param tiledMap tiledMap
     */
    private void defineLowerUpperTileLayers(TiledMap tiledMap){
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

    }

    private void createEnemySpawnRectangle(TiledMap tiledMap){
        spawnAreaList= new Array<>();
        MapLayer targetLayer = tiledMap.getLayers().get("EnemySpawnRectangle");
        for (RectangleMapObject object : targetLayer.getObjects().getByType(RectangleMapObject.class)) {
            spawnAreaList.add(new EnemyRectangleSpawnArea(object));
        }
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

}
