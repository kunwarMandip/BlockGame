package com.mygdx.game.map.objects;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.StaticVariables;

/**
 * Creates a closed Group of Box2D bodies where enemies can't escape from
 */
public class PlayerWall extends TileObjects {
    public PlayerWall(World world, TiledMap tiledMap, MapObject object) {
        super(world, tiledMap, object);
        fixture.setUserData(this);
        setCategoryFilter(StaticVariables.CATEGORY_WALL);
    }
}
