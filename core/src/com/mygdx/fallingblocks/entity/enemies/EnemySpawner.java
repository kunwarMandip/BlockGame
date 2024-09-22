package com.mygdx.fallingblocks.entity.enemies;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.objects.RectangleMapObject;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.fallingblocks.map.objects.EnemySpawnArea;

import java.util.Random;


public class EnemySpawner {

    private int lastNumber = 0;
    private final Random random= new Random();

    private final int spawnAreasCount;
    private final Array<EnemySpawnArea> spawnAreas= new Array<>();

    public EnemySpawner(World world, TiledMap tiledMap) {
        MapLayer targetLayer = tiledMap.getLayers().get("EnemySpawn");
        for (RectangleMapObject object : targetLayer.getObjects().getByType(RectangleMapObject.class)) {
            this.spawnAreas.add(new EnemySpawnArea(world, tiledMap, object));
        }
        this.spawnAreasCount=spawnAreas.size;
    }


    public EnemySpawnArea getRandomSpawnArea() {
        return spawnAreas.get(chooseRectangleToSpawn());
    }

    /**
     * Choose which rectangle to spawn Enemy from.
     * If Integer is repeated, re-rolls 75% of time to get a new integer
     * @return Random integer between 0 and spawnAreaCount -1
     */
    private int chooseRectangleToSpawn(){
        int number = random.nextInt(spawnAreasCount);

        while (number == lastNumber && random.nextDouble() < 0.75) {
            number = random.nextInt(spawnAreasCount);
        }
        lastNumber = number;
        return number;
    }


}
