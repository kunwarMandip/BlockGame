package com.mygdx.game.map;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import static com.mygdx.game.GlobalVariables.PPM;


public class SpawnArea {

    private static Rectangle rectangleSpawnArea;

    public SpawnArea(TiledMap tiledMap){
        init(tiledMap);
    }

    private void init(TiledMap tiledMap){
        String targetName="spawnArea";
        if(!StaticMapObjects.checkLayer(tiledMap, targetName)){
            System.out.println("Enemy Spawn Area rectangle object not found");
            return;
        }

        MapLayer targetLayer = tiledMap.getLayers().get(targetName);

        for (RectangleMapObject object : targetLayer.getObjects().getByType(RectangleMapObject.class)) {
            rectangleSpawnArea = object.getRectangle();
            break;
        }
    }

    public void draw(ShapeRenderer shapeRenderer, OrthographicCamera gameCamera){

        shapeRenderer.setProjectionMatrix(gameCamera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(1, 1, 1, 1); // Red color for the rectangle
        shapeRenderer.rect(rectangleSpawnArea.x / PPM,
                rectangleSpawnArea.y / PPM,
                rectangleSpawnArea.width / PPM,
                rectangleSpawnArea.height / PPM);
        shapeRenderer.end();

    }

    public static Vector2 getRandomSpawnPosition() {
        float x = rectangleSpawnArea.x + (float) Math.random() * rectangleSpawnArea.width;
        float y = rectangleSpawnArea.y + (float) Math.random() * rectangleSpawnArea.height;
        return new Vector2(x, y);
    }

}
