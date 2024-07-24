package com.mygdx.fallingblocks.entity.enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.fallingblocks.entity.DefineTexture;

public class EnemyAnimation {

    private final String textureName;
    private final Texture enemyTexture;

    public EnemyAnimation(String textureColor){
        Texture tempTexture=DefineTexture.getTexture(textureColor);
        if(tempTexture!=null){
            this.textureName=textureColor;
            this.enemyTexture= DefineTexture.getTexture(textureColor);
            System.out.println("Enemy Texture: " + textureName);
        }else {
            throw new RuntimeException("Error. Can't find EnemyAnimation");
        }

    }

    public void draw(Vector2 enemyPosition, SpriteBatch spriteBatch){
        float textureWidth=6, textureHeight=6;
        float x=enemyPosition.x - textureWidth /2;
        float y=enemyPosition.y - textureHeight /2;
        spriteBatch.draw(enemyTexture, x, y, textureWidth, textureHeight);
    }

    public String getTextureName(){
        return textureName;
    }


}
