package com.mygdx.game.entity.enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class EnemyAnimation {

    private final Texture enemyTexture;
    public EnemyAnimation(Texture texture){
        this.enemyTexture=texture;
    }


    public void draw(Vector2 enemyPosition, SpriteBatch spriteBatch){
        float textureWidth=4, textureHeight=4;
        float x=enemyPosition.x - textureWidth /2;
        float y=enemyPosition.y - textureHeight /2;
        spriteBatch.draw(enemyTexture, x, y, textureWidth, textureHeight);
    }

    public void dispose(){
        enemyTexture.dispose();
    }
}
