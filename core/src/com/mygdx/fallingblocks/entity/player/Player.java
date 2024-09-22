package com.mygdx.fallingblocks.entity.player;

import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.fallingblocks.entity.Entity;
import com.mygdx.fallingblocks.utilities.GameStateVariables;
import com.mygdx.fallingblocks.input.InputListenersManager;
import com.mygdx.fallingblocks.utilities.SolidTextureCreator;

import java.util.Random;

import static com.mygdx.fallingblocks.GlobalStaticVariables.CATEGORY_PLAYER;
import static com.mygdx.fallingblocks.GlobalStaticVariables.PLAYER_COLOR_CHANGE_INTERVAL;

/**
 * PLayer Entity Class. Contains everything about the player class
 */
public class Player extends Entity {

    private GameStateVariables gameStateVariables;
    private PlayerAnimation playerAnimation;

    private int colorChangeCounter;
    private int colorListSize;

    public Player(World world,
                  GameStateVariables gameStateVariables,
                  SolidTextureCreator solidColorCreator,
                  RayHandler rayHandler,
                  InputListenersManager inputListenersManager) {
        this.gameStateVariables=gameStateVariables;
        this.colorListSize=solidColorCreator.getSize();
        this.createPlayer(world, new Vector2(100, 100));
        this.playerAnimation = new PlayerAnimation(solidColorCreator);
        this.setController(inputListenersManager);
    }

    public Player(World world,
                  GameStateVariables gameStateVariables,
                  SolidTextureCreator solidColorCreator,
                  InputListenersManager inputListenersManager) {
        this.gameStateVariables=gameStateVariables;
        this.colorListSize=solidColorCreator.getSize();
        this.createPlayer(world,new Vector2(100, 100));
        this.playerAnimation = new PlayerAnimation(solidColorCreator);
        this.setController(inputListenersManager);
    }


    public Player(World world, SolidTextureCreator solidTextureCreator){
        Vector2 bodyLocation= new Vector2(14, 27);
        Vector2 bodyDimensions= new Vector2(2, 2);
        defaultBody(world, bodyLocation, bodyDimensions);
        playerAnimation= new PlayerAnimation(solidTextureCreator);
    }


    /**
     * Create a Default box2D body in given world, location and size
     *
     * @param world          world to load the box2D object/body in
     * @param spawnLocation   the location to set the bodyPosition in
     * @param bodyDimensions the size of the box2D body
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


    @Override
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



    public void createPlayer(World world, Vector2 bodyDimension){
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
    }

    public Vector2 getPlayerLocation(){
        return body.getPosition();
    }

    public void setController(InputListenersManager inputListenersManager){
        GestureDetector gestureDetector= new GestureDetector(new GesturePlayerController(body));
        inputListenersManager.addInputProcessor(gestureDetector);
    }
}
