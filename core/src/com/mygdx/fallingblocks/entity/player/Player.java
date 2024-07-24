package com.mygdx.fallingblocks.entity.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.fallingblocks.GameStateVariables;
import com.mygdx.fallingblocks.utilities.SolidColorCreator;

import java.util.Random;

import static com.mygdx.fallingblocks.StaticVariables.CATEGORY_PLAYER;
import static com.mygdx.fallingblocks.StaticVariables.PLAYER_COLOR_CHANGE_INTERVAL;

/**
 * PLayer Entity Class. Contains everything about the player class
 */
public class Player {

    private final World world;
    private Body body;

    private GameStateVariables gameStateVariables;
    private final PlayerAnimation playerAnimation;

    private int colorChangeCounter;
    private final int colorListSize;

    public Player(World world,
                  GameStateVariables gameStateVariables,
                  SolidColorCreator solidColorCreator) {
        this.world=world;
        this.gameStateVariables=gameStateVariables;
        this.colorListSize=solidColorCreator.getSize();
        this.createBox2DBody(new Vector2(100, 100));
        this.playerAnimation = new PlayerAnimation(solidColorCreator);;
        this.setController();
    }

    public Body getBody(){
        return body;
    }

    public void draw(SpriteBatch spriteBatch){
        playerAnimation.draw(body.getPosition(), spriteBatch);
    }

    /**
     * Check if we have to change player color or not
     */
    public void update(int playerColorID){
        if(gameStateVariables.score == gameStateVariables.lastScore){
            return;
        }

        if(colorChangeCounter% PLAYER_COLOR_CHANGE_INTERVAL==0){
            playerAnimation.setTexture(differentRandomColor(playerColorID));
        }
        colorChangeCounter++;
    }


    public int differentRandomColor(int playerColorID){
        Random random= new Random();
        int randomColor;
        do{
            randomColor = random.nextInt(colorListSize + 1); // Gen
        }while(playerColorID==randomColor);
        return randomColor;
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

    /**
     * Set Player Controller
     */
    public void setController(){
        GestureDetector gestureDetector= new GestureDetector(new GesturePlayerController(body));
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(gestureDetector);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }
}
