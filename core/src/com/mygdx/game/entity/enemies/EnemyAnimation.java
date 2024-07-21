package com.mygdx.game.entity.enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entity.DefineTexture;
import com.mygdx.game.entity.StringTexturePair;

public class EnemyAnimation {

    private final String textureName;
    private final Texture enemyTexture;

    public EnemyAnimation(String textureColor){
        StringTexturePair stringTexturePair= DefineTexture.getTexturePair(textureColor);
        textureName=stringTexturePair.textureName;
        enemyTexture= stringTexturePair.texture;
    }

    public void draw(Vector2 enemyPosition, SpriteBatch spriteBatch){
        float textureWidth=4, textureHeight=4;
        float x=enemyPosition.x - textureWidth /2;
        float y=enemyPosition.y - textureHeight /2;
        spriteBatch.draw(enemyTexture, x, y, textureWidth, textureHeight);
    }

    public String getTextureName(){
        return textureName;
    }

    public void dispose(){
        enemyTexture.dispose();
    }
}
