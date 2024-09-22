package com.mygdx.fallingblocks.map;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.fallingblocks.map.objects.EnemySpawnArea;
import com.mygdx.fallingblocks.map.objects.OuterBound;
import com.mygdx.fallingblocks.map.objects.PlayerWall;


public class LoadMapObjects {

    private final World world;
    private final TiledMap tiledMap;

    public LoadMapObjects(World world, TiledMap tiledMap){
        this.world=world;
        this.tiledMap=tiledMap;
        loadWall();
        loadOuterBound();
    }

    private void loadWall(){
        MapLayer targetLayer= tiledMap.getLayers().get("Wall");
        for (RectangleMapObject object : targetLayer.getObjects().getByType(RectangleMapObject.class)) {
            new PlayerWall(world, tiledMap, object);
        }
    }

    private void loadOuterBound(){
        MapLayer targetLayer= tiledMap.getLayers().get("OuterBound");
        for (RectangleMapObject object : targetLayer.getObjects().getByType(RectangleMapObject.class)) {
            new OuterBound(world, tiledMap, object);
        }
    }

    private void loadEnemySpawnArea(){
        MapLayer targetLayer = tiledMap.getLayers().get("EnemySpawn");
        for (RectangleMapObject object : targetLayer.getObjects().getByType(RectangleMapObject.class)) {
            new EnemySpawnArea(world, tiledMap, object);
        }
    }



}
