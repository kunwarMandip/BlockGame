package com.mygdx.fallingblocks.map.objects;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.maps.objects.RectangleMapObject;

public class ScoreArea{

    private Rectangle rectangleArea;

    public ScoreArea(World world, TiledMap tiledMap) {
        MapLayer targetLayer= tiledMap.getLayers().get("Score");
        for (RectangleMapObject object : targetLayer.getObjects().getByType(RectangleMapObject.class)) {
            this.rectangleArea = object.getRectangle();
        }
    }

    public Rectangle getRectangleArea(){
        return rectangleArea;
    }
}
