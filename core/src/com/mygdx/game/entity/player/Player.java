package com.mygdx.game.entity.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Object Body that the user controls aka: the user itself.
 */
public class Player {

    private final World world;
    private Body body;
    PlayerController playerController;
    private final PlayerAnimation playerAnimation;

    /**
     * Sets the player "object" on the given world
     * @param world the world to place the objects in.
     */
    public Player(World world,  OrthographicCamera gameCamera) {
        this.world=world;
        createBox2DBody(new Vector2(100, 100));
        playerAnimation= new PlayerAnimation();

//        playerController = new PlayerController(body, gameCamera);
//        Gdx.input.setInputProcessor(playerController);

        GestureDetector gestureDetector= new GestureDetector(new GesturePlayerController(body, gameCamera));
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(gestureDetector);
        Gdx.input.setInputProcessor(inputMultiplexer);


    }

    /**
     * Creates box2D body for player
     * @param bodyDimension size of the box2D body
     */
    private void createBox2DBody(Vector2 bodyDimension){
        //Defining BoyDef with zero Restitution and No friction
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(14, 27);
        bodyDef.fixedRotation = true;
        body = world.createBody(bodyDef);

        //Create shape for the body
        PolygonShape rectangleShape = new PolygonShape();
        rectangleShape.setAsBox(bodyDimension.x/100, bodyDimension.y/100);

        // Creates fixture definition and applies to body
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = rectangleShape;
        fixtureDef.density = 1.0f;
        fixtureDef.friction = 0.0f;
        fixtureDef.restitution = 0.0f;

        body.createFixture(fixtureDef).setUserData(this);
        rectangleShape.dispose();
    }

    public void update(){

    }

    public void draw(SpriteBatch spriteBatch){
        Vector2 bodyPosition=body.getPosition();
        playerAnimation.draw(new Vector2(bodyPosition.x, bodyPosition.y), spriteBatch);
    }

    public Body getBody(){
        return body;
    }
}
