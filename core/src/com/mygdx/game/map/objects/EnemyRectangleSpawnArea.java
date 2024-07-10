package com.mygdx.game.map.objects;

import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;

/**
 * Responsible for finding the spawnArea Object in TiledMap
 * Creates a Rectangle Object in the Box2D world (not a body though)
 * Allows Enemies to be spawned from directly within that area
 */
public class EnemyRectangleSpawnArea {

    private static Rectangle rectangleSpawnArea;
    private final String rectangleDirection;

    public EnemyRectangleSpawnArea(RectangleMapObject rectangleObject){
        rectangleSpawnArea = rectangleObject.getRectangle();
        this.rectangleDirection= rectangleObject.getProperties().get("name", String.class);

        System.out.println("Rectangle Properties: "+ "Position: " + rectangleSpawnArea.x +  " : " + rectangleSpawnArea.y
                +  " . " + rectangleDirection);
    }


    public String getRectangleDirection(){
        return rectangleDirection;
    }

    public Rectangle getRectangleSpawnArea(){
        return rectangleSpawnArea;
    }
}
