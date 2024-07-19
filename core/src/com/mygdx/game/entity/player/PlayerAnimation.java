package com.mygdx.game.entity.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;


public class PlayerAnimation {

    private Texture playerTexture;

    public PlayerAnimation(){
        this.playerTexture= new Texture("characters/red.png");
    }

    /**
     * Change color of the user texture
     * @param texture the new color to set to
     */
    public void update(Texture texture){
        playerTexture= texture;
    }

    /**
     * Draws texture inside the given BOX2D body position
     * @param playerPosition position of the player
     * @param spriteBatch spriteBatch
     */
    public void draw(Vector2 playerPosition, SpriteBatch spriteBatch){
        float textureWidth=4, textureHeight=4;
        float x=playerPosition.x - textureWidth /2;
        float y=playerPosition.y - textureHeight /2;
        spriteBatch.draw(playerTexture, x, y, textureWidth, textureHeight);
    }


}
