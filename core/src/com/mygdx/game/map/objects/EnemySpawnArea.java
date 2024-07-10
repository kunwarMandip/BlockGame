package com.mygdx.game.map.objects;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.GlobalVariables;

public class EnemySpawnArea extends TileObjects{

    private final String spawnDirection;
    private final Vector2 rectangleDimension;

    public EnemySpawnArea(World world, TiledMap tiledMap, MapObject object) {
        super(world, tiledMap, object);
        fixture.setSensor(true);
        fixture.setUserData(this);
        setCategoryFilter(GlobalVariables.CATEGORY_ENEMY_SPAWN_AREA);
        this.spawnDirection=mapObject.getProperties().get("name", String.class);
        System.out.println("Rectangle Created. Direction: " + spawnDirection);

        float x= bounds.getWidth() / 2 / GlobalVariables.PPM;
        float y=bounds.getHeight() / 2 / GlobalVariables.PPM;
        rectangleDimension=new Vector2(x, y);
        System.out.println("Position"+  bounds);
    }

    public Vector2 getPosition(){
        return body.getPosition();
    }
    public String getSpawnDirection(){
        return spawnDirection;
    }

    public Body getBody(){
        return body;
    }

    public Vector2 getRectangleDimension(){
        return rectangleDimension;
    }
}
