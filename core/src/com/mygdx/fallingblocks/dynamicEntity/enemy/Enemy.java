package com.mygdx.fallingblocks.dynamicEntity.enemy;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public abstract class Enemy{

    protected Body body;

    protected Vector2 spawnPosition, speed;
    protected int attackPower, healthPoints;
    protected String behaviour;
    protected float spawnTime;

    private boolean isEnemySpawned=false;

    public abstract void draw(SpriteBatch spriteBatch);

    public boolean isEnemySpawned(){
        return isEnemySpawned;
    }
    
}
