package com.mygdx.game.entity.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Responsible for controlling all the animations
 * related to the player object
 */
public class PlayerAnimation {

    private final Texture playerTexture;
    private float width=100;
    private float height=100;

    public PlayerAnimation(){
        this.playerTexture= new Texture("box.png");
    }

    /**
     * Draws the sprite on to the screen
     * @param playerPosition position where the sprite is meant to be drawn
     * @param spriteBatch faster way to draw than sprites only
     */
    public void draw(Vector2 playerPosition, SpriteBatch spriteBatch){
        spriteBatch.draw(playerTexture, playerPosition.x - width / 2, playerPosition.y - height / 2, width, height);
    }

}
