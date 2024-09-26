package com.mygdx.fallingblocks.entity.player;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.fallingblocks.entity.Entity;
import com.mygdx.fallingblocks.input.InputListenersManager;
import com.mygdx.fallingblocks.utilities.SolidTextureCreator;

import static com.mygdx.fallingblocks.GlobalStaticVariables.CATEGORY_PLAYER;

public class NewPlayer extends Entity {

    private final PlayerAnimation playerAnimation;

    public NewPlayer(World world, SolidTextureCreator solidTextureCreator){
        Vector2 bodyLocation= new Vector2(14, 27);
        Vector2 bodyDimensions= new Vector2(2, 2);
        defaultBody(world, bodyLocation, bodyDimensions);
        playerAnimation= new PlayerAnimation(solidTextureCreator);
    }


    /**
     * Create a Default box2D body in given world, location and size
     *
     * @param world          Pre-Defined box2D world to load the body in
     * @param spawnLocation  The location to set the body in
     * @param bodyDimensions Set the size of the box2D body
     */
    @Override
    public void defaultBody(World world, Vector2 spawnLocation, Vector2 bodyDimensions) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(spawnLocation.x, spawnLocation.y);
        bodyDef.fixedRotation = true;
        body = world.createBody(bodyDef);

        //Create shape for the body
        PolygonShape rectangleShape = new PolygonShape();
        rectangleShape.setAsBox(bodyDimensions.x, bodyDimensions.y);

        // Creates fixture definition and applies to body
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = rectangleShape;
        fixtureDef.density = 1.0f;
        fixtureDef.friction = 0.0f;
        fixtureDef.restitution = 0.0f;

        fixtureDef.filter.categoryBits=CATEGORY_PLAYER;
        body.createFixture(fixtureDef).setUserData(this);
        rectangleShape.dispose();
    }

    /**
     * Update the class with whatever necessary statements
     *
     * @param delta time since last render
     */
    @Override
    public void update(float delta) {

    }

    /**
     * Draw the texture for the box2D body
     *
     * @param spriteBatch spritebatch
     */
    @Override
    public void draw(SpriteBatch spriteBatch) {
        playerAnimation.draw(body.getPosition(), spriteBatch);
    }

    public void setController(InputListenersManager inputListenersManager){
        GestureDetector gestureDetector= new GestureDetector(new GesturePlayerController(body));
        inputListenersManager.addInputProcessor(gestureDetector);
    }
}
