package com.mygdx.game.map;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

/**
 * Responsible for loading all TiledMap map objects
 */
public class MapManager {

    private final World world;
    private final TiledMap tiledMap;
    private ShapeRenderer shapeRenderer;
    private OrthographicCamera gameCamera;

    private StaticMapObjects staticMapObjects;
    private DynamicMapObjects dynamicMapObjects;
    private SpawnArea spawnArea;

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

    private void init(){

        //init Static Map Objects
        staticMapObjects= new StaticMapObjects(world, tiledMap);
        spawnArea= new SpawnArea(tiledMap);
        //init dynamic Map Objects

    }

    public void update(){
        System.out.println("Manger screen updating");
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


    public StaticMapObjects getStaticMapObjects() {
        return staticMapObjects;
    }

    public DynamicMapObjects getDynamicMapObjects() {        return dynamicMapObjects;    }
}
