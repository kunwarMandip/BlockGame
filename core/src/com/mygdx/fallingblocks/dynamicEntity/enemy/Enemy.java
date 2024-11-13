package com.mygdx.fallingblocks.dynamicEntity.enemy;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.fallingblocks.dynamicEntity.SpawnEnemy;

public abstract class Enemy implements SpawnEnemy {

    protected Body body;

    protected Vector2 spawnPosition, speed;
    protected int attackPower, healthPoints;
    protected String behaviour;
    protected float spawnTime;

    public boolean isEnemySpawned=false;

    public abstract void draw(SpriteBatch spriteBatch);

    public abstract void update();

}
