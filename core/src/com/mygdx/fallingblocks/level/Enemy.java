package com.mygdx.fallingblocks.level;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public abstract class Enemy {

    protected Body body;

    protected Vector2 spawnPosition, speed;
    protected int attackPower, healthPoints;
    protected String behaviour;
    protected float spawnTime;

    String type, behavior, size, effect;
    int amount, health, rewardPoints;
    double spawnInterval;

    public Enemy(){}

    public abstract void setBody();

    public void destroyBody(World world){
        if(body==null) {
            return;
        }

        world.destroyBody(body);
    }

    public Vector2 getBodyPosition(){
        return body.getPosition();
    }
}
