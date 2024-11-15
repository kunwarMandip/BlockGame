package com.mygdx.fallingblocks.level.enemy;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.fallingblocks.level.Entity;

public abstract class Enemy extends Entity {

    protected Vector2 speed;
    protected float spawnTime;
    String behavior, effect;

    public Enemy(int health, Vector2 spawnPosition, Vector2 bodyDimension) {
        super(health, spawnPosition, bodyDimension);
    }
}
