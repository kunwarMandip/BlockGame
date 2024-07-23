package com.mygdx.game.entity.enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.DefineTexture;

public class EnemyAnimation {

    private final String textureName;
    private Texture enemyTexture;

    public EnemyAnimation(String textureColor){
//        StringTexturePair stringTexturePair= DefineTexture.getTexturePair(textureColor);
//        textureName=stringTexturePair.textureName;
//        enemyTexture= stringTexturePair.texture;

        Texture tempTexture=DefineTexture.getTexture(textureColor);
        if(tempTexture==null){
            this.textureName="yellow";
            this.enemyTexture=new Texture("characters/yellow.png");
            throw new RuntimeException("yhhh");
        }
        else{
            this.textureName=textureColor;
            this.enemyTexture= DefineTexture.getTexture(textureColor);
        }

        System.out.println("Enemy Texture: " + textureName);
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

    public void dispose(){
//        enemyTexture.dispose();
    }
}
