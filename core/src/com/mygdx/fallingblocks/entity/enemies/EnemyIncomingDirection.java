package com.mygdx.fallingblocks.entity.enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;

import static com.mygdx.fallingblocks.GlobalStaticVariables.VIRTUAL_HEIGHT;

/**
 * Responsible for showing which four Cardinal direction
 * the Enemy will be spawning from
 */
public class EnemyIncomingDirection {

    private final Texture texture;
    private float textureRotation=0;

    public EnemyIncomingDirection(){
        this.texture= new Texture("map/images/arrows/arrow.png");
    }


    public void draw(SpriteBatch spriteBatch){

        float viewportWidthInWorldUnits = 720 / 16.0f;
        float viewportHeightInWorldUnits = 1440 / 16.0f;

        // Convert texture size to world units
        float textureWidthInWorldUnits = 1000 / 16.0f;
        float textureHeightInWorldUnits = 1000 / 16.0f;

        // Calculate the position to center the texture
        float x = (viewportWidthInWorldUnits - textureWidthInWorldUnits) / 2;
        float y = (viewportHeightInWorldUnits - textureHeightInWorldUnits) / 2;

        // Set the origin of rotation to the center of the texture
        float originX = textureWidthInWorldUnits / 2;
        float originY = textureHeightInWorldUnits / 2;

        // Draw the texture with rotation
        spriteBatch.draw(
                texture,                // Texture to draw
                x, y,                   // Position to draw the texture
                originX, originY,       // Origin of rotation (center of the texture)
                textureWidthInWorldUnits, // Width of the texture in world units
                textureHeightInWorldUnits, // Height of the texture in world units
                0.5f, 0.5f,             // Scaling factors (1.0f means no scaling)
                textureRotation,         // Rotation in degrees
                0, 0,                   // Texture coordinates (start at 0,0)
                texture.getWidth(),     // Texture width in pixels
                texture.getHeight(),    // Texture height in pixels
                false, false            // Flip texture (not needed here)
        );
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
