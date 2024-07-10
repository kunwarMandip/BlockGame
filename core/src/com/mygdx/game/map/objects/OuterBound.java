package com.mygdx.game.map.objects;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.GlobalVariables;

/**
 * Responsible for deleting the enemy when they touch the outerBound of the tiledMap
 */
public class OuterBound extends TileObjects {
    public OuterBound(World world, TiledMap tiledMap, MapObject object) {
        super(world, tiledMap, object);
        fixture.setSensor(true);
        fixture.setUserData(this);
        setCategoryFilter(GlobalVariables.CATEGORY_OUTER_BOUND);

    }
}
