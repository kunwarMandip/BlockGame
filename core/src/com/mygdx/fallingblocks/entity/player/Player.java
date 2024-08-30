package com.mygdx.fallingblocks.entity.player;

import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.fallingblocks.utilities.GameStateVariables;
import com.mygdx.fallingblocks.utilities.InputListenersManager;
import com.mygdx.fallingblocks.utilities.DynamicTextureCreator;

import java.util.Random;

import static com.mygdx.fallingblocks.GlobalStaticVariables.CATEGORY_PLAYER;
import static com.mygdx.fallingblocks.GlobalStaticVariables.PLAYER_COLOR_CHANGE_INTERVAL;

/**
 * PLayer Entity Class. Contains everything about the player class
 */
public class Player {

    private Body body;
    private final World world;

    private final GameStateVariables gameStateVariables;
    private final PlayerAnimation playerAnimation;

    private int colorChangeCounter;
    private final int colorListSize;

    public Player(World world,
                  GameStateVariables gameStateVariables,
                  DynamicTextureCreator solidColorCreator,
                  RayHandler rayHandler,
                  InputListenersManager inputListenersManager) {
        this.world=world;
        this.gameStateVariables=gameStateVariables;
        this.colorListSize=solidColorCreator.getSize();
        this.createBox2DBody(new Vector2(100, 100), rayHandler);
        this.playerAnimation = new PlayerAnimation(solidColorCreator);;
        this.setController(inputListenersManager);
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
        if(gameStateVariables.getScore() == gameStateVariables.getLastScore()){
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



    private void createBox2DBody(Vector2 bodyDimension, RayHandler rayHandler){
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

        PointLight pointLight = new PointLight(rayHandler, 500, new Color(1, 1, 1, 1), 5, 0, 0);
        pointLight.attachToBody(body);

        System.out.println("Players Body Location: " + body.getPosition().x +" :...: "+ body.getPosition().y );
    }

    /**
     * Set Player Controller
     */
    public void setController(InputListenersManager inputListenersManager){
        GestureDetector gestureDetector= new GestureDetector(new GesturePlayerController(body));
        inputListenersManager.setInputProcessor(gestureDetector);
    }
}
