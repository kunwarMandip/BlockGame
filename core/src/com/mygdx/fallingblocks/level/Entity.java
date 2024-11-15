package com.mygdx.fallingblocks.level;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.fallingblocks.level.interfaces.DeathAction;
import com.mygdx.fallingblocks.level.interfaces.HitAction;

public abstract class Entity implements DeathAction, HitAction {

    protected Body body;
    protected boolean isDead;
    protected int attack, health, rewardPoints;
    protected Vector2 spawnPosition, bodyDimension;

    public Entity(int health, Vector2 spawnPosition, Vector2 bodyDimension){
        this.isDead=false;
        this.health=health;
        this.spawnPosition=spawnPosition;
        this.bodyDimension=bodyDimension;
    }

    public abstract void update(float delta);

    public abstract void draw(SpriteBatch spriteBatch);

    public abstract void setBody(World world);

    public void destroyBody(World world){
        world.destroyBody(body);
    }

}
