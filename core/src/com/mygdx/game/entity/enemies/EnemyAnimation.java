package com.mygdx.game.entity.enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.FallingBlocks;

public class EnemyAnimation {

    private final Texture enemyTexture;
    private final float width=200/ FallingBlocks.PPM;
    private final float height=200/FallingBlocks.PPM;

    public EnemyAnimation(){
        this.enemyTexture=new Texture("box.png");
    }

    /**
     * Draws the sprite on to the screen
     * @param playerPosition position where the sprite is meant to be drawn
     * @param spriteBatch faster way to draw than sprites only
     */
    public void draw(Vector2 playerPosition, SpriteBatch spriteBatch){
        spriteBatch.draw(enemyTexture, playerPosition.x, playerPosition.y, width, height);
    }

    public void dispose(){
        enemyTexture.dispose();
    }
}
