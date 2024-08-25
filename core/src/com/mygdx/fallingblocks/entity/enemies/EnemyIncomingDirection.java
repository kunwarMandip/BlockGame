package com.mygdx.fallingblocks.entity.enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;

import static com.mygdx.fallingblocks.GlobalStaticVariables.PPM;

/**
 * Responsible for showing which four Cardinal direction
 * the Enemy will be spawning from
 */
public class EnemyIncomingDirection {

    public Rectangle bound;
    private final Texture texture;
    private float textureRotation=0;

    public EnemyIncomingDirection(TiledMap tiledMap){
        setEnemyPointerObject(tiledMap);
        this.texture= new Texture("badlogic.jpg");
    }

    public EnemyIncomingDirection(TiledMap tiledMap, Texture texture){
        setEnemyPointerObject(tiledMap);
        this.texture=texture;
    }

    private void setEnemyPointerObject(TiledMap tiledMap){
        MapLayer targetLayer= tiledMap.getLayers().get("EnemyDirection");
        for (RectangleMapObject object : targetLayer.getObjects().getByType(RectangleMapObject.class)) {
            this.bound = object.getRectangle();
            break;
        }
    }

    public void draw(SpriteBatch spriteBatch){
        float x = bound.x / PPM;
        float y = bound.y / PPM;
        float width = bound.width / PPM;
        float height = bound.height / PPM;
        float originX = width / 2;
        float originY = height / 2;

//      Draw texture with adjusted origin and position
        spriteBatch.draw(texture, x, y, originX, originY, width, height, 1, 1,
                textureRotation, 0, 0, texture.getWidth(), texture.getHeight(), false, false);

    }

    /**
     * Set strings to show which direction to draw the texture towards
     * @param direction the angle to set the texture towards
     */
    public void setDirection(String direction){
        switch (direction){
            case "right":
                textureRotation=270;
                break;
            case "bottom":
                textureRotation=180;
                break;
            case "left":
                textureRotation=90;
                break;
            default:
                textureRotation=0;
                break;
        }
    }

}
