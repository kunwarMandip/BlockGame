package com.mygdx.fallingblocks.entity.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import static com.mygdx.fallingblocks.StaticVariables.CATEGORY_PLAYER;

/**
 * PLayer Entity Class. Contains everything about the player class
 */
public class Player {

    private final World world;
    private Body body;
    private final PlayerAnimation playerAnimation;

    /**
     * Sets the player "object" on the given world
     * @param world the world to place the objects in.
     */
    public Player(World world) {
        this.world=world;
        createBox2DBody(new Vector2(100, 100));
        playerAnimation= new PlayerAnimation();

        //Creating Player Controller
        GestureDetector gestureDetector= new GestureDetector(new GesturePlayerController(body));
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(gestureDetector);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }


    public void draw(SpriteBatch spriteBatch){
        playerAnimation.draw(body.getPosition(), spriteBatch);
    }

    public Body getBody(){
        return body;
    }

    public String getPlayerTextureName(){
        return playerAnimation.getTextureName();
    }

    private void createBox2DBody(Vector2 bodyDimension){
        //Defining BoyDef with zero Restitution and No friction

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(14, 27);
        bodyDef.fixedRotation = true;
        body = world.createBody(bodyDef);

        //Create shape for the body
        PolygonShape rectangleShape = new PolygonShape();
        rectangleShape.setAsBox(2, 2);

        // Creates fixture definition and applies to body
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = rectangleShape;
        fixtureDef.density = 1.0f;
        fixtureDef.friction = 0.0f;
        fixtureDef.restitution = 0.0f;

        fixtureDef.filter.categoryBits=CATEGORY_PLAYER;
        body.createFixture(fixtureDef).setUserData(this);
        rectangleShape.dispose();
        System.out.println("Players Body Location: " + body.getPosition().x +" :...: "+ body.getPosition().y );
    }

}
