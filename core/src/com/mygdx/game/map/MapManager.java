package com.mygdx.game.map;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.regex.*;


/**
 * Responsible for loading all TiledMap map objects
 */
public class MapManager {

    private final World world;
    private final TiledMap tiledMap;
    private ShapeRenderer shapeRenderer;
    private OrthographicCamera gameCamera;
    private OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;

    private StaticMapObjects staticMapObjects;
    private DynamicMapObjects dynamicMapObjects;
    private SpawnArea spawnArea;

    //Set which layer should be drawn before box2D and which ones after
    private int[] upperTiles;
    private int[] lowerTiles;
    public MapManager(World world, TiledMap tiledMap){
        this.world=world;
        this.tiledMap=tiledMap;
        init();
    }

    public MapManager(World world, TiledMap tiledMap, ShapeRenderer shapeRenderer, OrthographicCamera gameCamera){
        this.world=world;
        this.tiledMap=tiledMap;
        this.shapeRenderer=shapeRenderer;
        this.gameCamera=gameCamera;
        init();
    }


     /**
     * New Constructor for the game to enable dynamic TiledMap layer loading
     * @param world
     * @param orthogonalTiledMapRenderer
     * @param tiledMap
     */
    public MapManager(World world, OrthogonalTiledMapRenderer orthogonalTiledMapRenderer, TiledMap tiledMap){
        this.world=world;
        this.tiledMap=tiledMap;


        //Separate which layers need to be rendered first and which at the end
        ArrayList<Integer> upperTileLayers = new ArrayList<>();
        ArrayList<Integer> lowerTileLayers=  new ArrayList<>();

        String layerToFind="top";
        MapLayers mapLayers= tiledMap.getLayers();

        for (MapLayer layer : tiledMap.getLayers()) {

            if(layer instanceof TiledMapTileLayer) {
                String layerName = layer.getName();
                Pattern pattern = Pattern.compile("\\btop\\b");
                Matcher matcher = pattern.matcher(layerToFind);

                if (matcher.find()) {
                    upperTileLayers.add(mapLayers.getIndex(layerName));
                    System.out.println("Top Layer Found: " + layerName);
                } else {
                    lowerTileLayers.add(mapLayers.getIndex(layerName));
                    System.out.println("Bottom Layer Found: " + layerName);
                }
            }
        }

        upperTiles = upperTileLayers.stream().mapToInt(Integer::intValue).toArray();
        lowerTiles = lowerTileLayers.stream().mapToInt(Integer::intValue).toArray();

        staticMapObjects = new StaticMapObjects(world, tiledMap);
        spawnArea= new SpawnArea(tiledMap);
    }



    private void init(){

        //init Static Map Objects
        staticMapObjects= new StaticMapObjects(world, tiledMap);
        spawnArea= new SpawnArea(tiledMap);
        //init dynamic Map Objects

    }

    public void update(){
        spawnArea.draw(shapeRenderer, gameCamera);
    }

    public void destroyMap(World world){
        Array<Body> mapBodies= staticMapObjects.getStaticMapBodies();

        if(mapBodies!=null){
            for(Body body: mapBodies){
                world.destroyBody(body);
            }
        }
    }


    public int[] getUpperTiles(){
        return upperTiles;
    }
    public int[] getLowerTiles(){
        return lowerTiles;
    }

    public StaticMapObjects getStaticMapObjects() {
        return staticMapObjects;
    }

    public DynamicMapObjects getDynamicMapObjects() {
        return dynamicMapObjects;
    }
}
