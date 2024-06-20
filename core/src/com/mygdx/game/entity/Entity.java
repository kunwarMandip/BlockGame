package com.mygdx.game.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Defines a body to be created in the WORLD passed.
 * This is used as a parent class to every entity in this game
 */
public class Entity {

    public  Body body;
    public World world;
    public Vector2 bodyDimension;

    /**
     * Sets the paradigm for every entity to be shown
     * @param world the world to place the objects in
     * @param bodyDimension height and width of the object to be created
     */
    public Entity(World world, Vector2 bodyDimension){
        this.world=world;
        this.bodyDimension=bodyDimension;
        defineEntity();
    }

    /**
     * Defines the Body to be shown in the screen
     */
    private void defineEntity(){

        //Defining BoyDef with zero Restitution and No friction
        BodyDef bodyDef  = new BodyDef();
        bodyDef.type= BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(100,100);
        bodyDef.fixedRotation = true;

        PolygonShape rectangleShape = new PolygonShape();
        rectangleShape.setAsBox(bodyDimension.x/2, bodyDimension.y/2);

        // Creates fixture definition and applies to body
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = rectangleShape;
        fixtureDef.density = 1.0f;
        fixtureDef.friction = 0.0f;
        fixtureDef.restitution = 0.0f;

        body = world.createBody(bodyDef);
        body.createFixture(fixtureDef);
    }

    public void draw(SpriteBatch spriteBatch) {}

    public void update() {}

}
