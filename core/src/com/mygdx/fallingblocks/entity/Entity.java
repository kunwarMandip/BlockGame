package com.mygdx.fallingblocks.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import static com.mygdx.fallingblocks.GlobalStaticVariables.CATEGORY_ENEMY;
import static com.mygdx.fallingblocks.GlobalStaticVariables.CATEGORY_WALL;

public abstract class Entity{

    public Body body;

    /**
     * Create a Default box2D body in given world, location and size
     * @param world Pre-Defined box2D world to load the body in
     * @param spawnLocation The location to set the body in
     * @param bodyDimensions Set the size of the box2D body
     */
    public abstract void defaultBody(World world, Vector2 spawnLocation, Vector2 bodyDimensions);

    /**
     * Update the class with whatever necessary statements
     * @param delta time since last render
     */
    public abstract void update(float delta);


    /**
     * Draw the texture for the box2D body
     * @param spriteBatch spritebatch
     */
    public abstract void draw(SpriteBatch spriteBatch);


//    public abstract void dispose(World world);

    public Vector2 getPosition(){
        return body.getPosition();
    }

    public void dispose(World world) {
        if(body==null){return;}
        world.destroyBody(body);
    }

}
