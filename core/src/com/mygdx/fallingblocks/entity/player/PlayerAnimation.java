package com.mygdx.fallingblocks.entity.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.fallingblocks.entity.DefineTexture;


public class PlayerAnimation {

    private String textureName;
    private Texture playerTexture;

    public PlayerAnimation(){
       setTexture("red");
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

    /**
     * Allows to set new texture for the PLAYER ENTITY
     * @param textureColor the color of the texture we are looking for
     */
    public void setTexture(String textureColor){

        Texture tempTexture= DefineTexture.getTexture(textureColor);
        if(tempTexture==null){
            throw new RuntimeException("NO PLAYER TEXTURE FOUND");
        }
        else{
            this.textureName=textureColor;
            this.playerTexture= DefineTexture.getTexture(textureColor);
        }
    }

    public String getTextureName(){
        return textureName;
    }

}
