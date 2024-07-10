package com.mygdx.game.map;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.map.objects.EnemySpawnArea;
import com.mygdx.game.map.objects.OuterBound;
import com.mygdx.game.map.objects.PlayerWall;


public class LoadMapObjects {

    private final World world;
    private final TiledMap tiledMap;

    private Array<EnemySpawnArea> spawnAreaList;

    public LoadMapObjects(World world, TiledMap tiledMap){
        this.world=world;
        this.tiledMap=tiledMap;
        loadWall();
        loadOuterBound();
        loadEnemyRectangleSpawnArea();
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

    private void loadEnemyRectangleSpawnArea(){
        spawnAreaList=new Array<>();
        MapLayer targetLayer= tiledMap.getLayers().get("EnemyRectangleSpawnArea");

        for (RectangleMapObject object : targetLayer.getObjects().getByType(RectangleMapObject.class)) {
            spawnAreaList.add(new EnemySpawnArea(world, tiledMap, object));
        }
    }

    public Array<EnemySpawnArea> getSpawnAreaList(){
        return spawnAreaList;
    }

}
