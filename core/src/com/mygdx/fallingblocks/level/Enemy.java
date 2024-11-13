package com.mygdx.fallingblocks.level;

import com.badlogic.gdx.math.Vector2;

public abstract class Enemy extends Entity{

    protected Vector2 spawnPosition, speed;
    protected int attackPower;
    protected String behaviour;
    protected float spawnTime;

    String type, behavior, size, effect;
    int amount, health, rewardPoints;
    double spawnInterval;

}
