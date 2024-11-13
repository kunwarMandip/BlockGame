package com.mygdx.fallingblocks.level;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.fallingblocks.level.interfaces.DeathAction;
import com.mygdx.fallingblocks.level.interfaces.HitAction;

public abstract class Entity implements DeathAction, HitAction {

    private Body body;
    private int health;
    private Vector2 spawnPosition, bodyDimension;

    protected abstract void setBody(World world);

    public void destroyBody(World world){
        world.destroyBody(body);
    }

    public Vector2 getBodyDimension(){
        return bodyDimension;
    }

    public Vector2 getSpawnPosition(){
        return body.getPosition();
    }

}
