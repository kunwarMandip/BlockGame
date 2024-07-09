package com.mygdx.game.map;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import static com.mygdx.game.GlobalVariables.*;

/**
 * Responsible for finding the spawnArea Object in TiledMap
 * Creates a Rectangle Object in the Box2D world (not a body though)
 * Allows Enemies to be spawned from directly within that area
 */
public class SpawnArea {

    private static Rectangle rectangleSpawnArea;
    private final String rectangleDirection;

    public SpawnArea(RectangleMapObject rectangleObject){
        rectangleSpawnArea = rectangleObject.getRectangle();
        this.rectangleDirection= rectangleObject.getProperties().get("name", String.class);
        System.out.println("Rectangle Properties: "+ rectangleDirection);
    }


    public void draw( OrthographicCamera gameCamera){
        ShapeRenderer shapeRenderer= new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(gameCamera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(1, 1, 1, 1); // Red color for the rectangle
        shapeRenderer.rect(rectangleSpawnArea.x / PPM,
                rectangleSpawnArea.y / PPM,
                rectangleSpawnArea.width / PPM,
                rectangleSpawnArea.height / PPM);
        shapeRenderer.end();
    }


    public String getRectangleDirection(){
        return rectangleDirection;
    }

    public Rectangle getRectangleSpawnArea(){
        return rectangleSpawnArea;
    }
    public static Vector2 getRandomSpawnPosition() {
        System.out.println("Getting Enemy Position");
        float x = rectangleSpawnArea.x + (float) Math.random() * rectangleSpawnArea.width;
        float y = rectangleSpawnArea.y + (float) Math.random() * rectangleSpawnArea.height;
        System.out.println(x + y);
        return new Vector2(x, y);
    }

}
