package com.mygdx.game.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.FallingBlocks;
import com.mygdx.game.entity.enemies.Enemy;


import static com.mygdx.game.FallingBlocks.PPM;
/**
 * Defines a body to be created in the WORLD passed.
 * This is used as a parent class to every entity in this game
 */
public class Entity {

    private Body body;
    public World world;

    private final Vector2 bodyDimension;

    /**
     * Sets the paradigm for every entity to be shown
     * @param world the world to place the objects in
     * @param bodyDimension height and width of the object to be created
     */
    public Entity(World world, Vector2 bodyDimension, EntityType entityType){
        this.world=world;
        this.bodyDimension=bodyDimension;
        defineEntity(entityType);
    }


    /**
     * Keep in mind all values are normalised in this constructor to my memory at least!!!
     * Since using PPM, numbers are divided by 100
     * @param world the world to place bodies in
     * @param bodyDimension height and width of the body to be created
     * @param spawnLocationX width of the screen. DO NOT NORMALIZE BY DIVIDING BY 100
     *
     */
    public Entity(World world, Vector2 bodyDimension, float spawnLocationX, EntityType entityType){
        this.world=world;
        this.bodyDimension= new Vector2(bodyDimension.x/100, bodyDimension.y/100);
        spawnLocationX = spawnLocationX / 100;
        float spawnLocationY= 10;
        defineEnemyEntity(spawnLocationX, spawnLocationY, entityType);
    }


    /**
     * Defines body for Enemy Class
     */
    private void defineEnemyEntity(float spawnLocationX, float spawnLocationY, EntityType entityType){
        BodyDef bodyDef  = new BodyDef();
        bodyDef.type= BodyDef.BodyType.StaticBody;
        bodyDef.position.set(5, 10);
        bodyDef.fixedRotation = true;
        body = world.createBody(bodyDef);

        //Create shape for the body
        PolygonShape rectangleShape = new PolygonShape();
        rectangleShape.setAsBox(bodyDimension.x, bodyDimension.y);

        // Creates fixture definition and applies to body
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = rectangleShape;
        fixtureDef.density = 1.0f;
        fixtureDef.friction = 0.0f;
        fixtureDef.restitution = 0.0f;

        body.createFixture(fixtureDef).setUserData(entityType);
        rectangleShape.dispose();
    }


    /**
     * Defines Body for Player class.
     * TODO remove this and make the two private method the same
     */
    private void defineEntity(EntityType entityType){

        //Defining BoyDef with zero Restitution and No friction
        BodyDef bodyDef  = new BodyDef();
        bodyDef.type= BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(500/ FallingBlocks.PPM, 500/FallingBlocks.PPM);
        bodyDef.fixedRotation = true;
        body = world.createBody(bodyDef);

        //Create shape for the body
        PolygonShape rectangleShape = new PolygonShape();
        rectangleShape.setAsBox(bodyDimension.x/FallingBlocks.PPM, bodyDimension.y/FallingBlocks.PPM);

        // Creates fixture definition and applies to body
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = rectangleShape;
        fixtureDef.density = 1.0f;
        fixtureDef.friction = 0.0f;
        fixtureDef.restitution = 0.0f;

        body.createFixture(fixtureDef).setUserData(entityType);
        rectangleShape.dispose();
    }


    protected Body getBody(){
        return body;
    }

}
