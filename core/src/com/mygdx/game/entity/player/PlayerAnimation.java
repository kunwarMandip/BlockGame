package com.mygdx.game.entity.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.FallingBlocks;
import com.mygdx.game.GlobalVariables;

/**
 * Responsible for controlling all the animations
 * related to the player object
 */
public class PlayerAnimation {

    private final Texture playerTexture;

    /**
     * Width and height of the sprites to be displayed
     */
    private final float width=200/ GlobalVariables.PPM;
    private final float height=200/GlobalVariables.PPM;

    public PlayerAnimation(){
        this.playerTexture= new Texture("box.png");
    }

    /**
     * Draws the sprite on to the screen
     * @param playerPosition position where the sprite is meant to be drawn
     * @param spriteBatch faster way to draw than sprites only
     */
    public void draw(Vector2 playerPosition, SpriteBatch spriteBatch){
        spriteBatch.draw(playerTexture, playerPosition.x, playerPosition.y, width, height);
    }


}
