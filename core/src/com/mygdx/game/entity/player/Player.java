package com.mygdx.game.entity.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.entity.Entity;
import com.mygdx.game.entity.EntityType;

/**
 * Object Body that the user controls aka: the user itself.
 */
public class Player extends Entity {

    private PlayerAnimation playerAnimation;
    /**
     * Sets the player "object" on the given world
     * @param world the world to place the objects in.
     * @param bodyDimension height and width of the object to be created
     */
    public Player(World world, Vector2 bodyDimension, OrthographicCamera gameCamera) {
        super(world, bodyDimension, EntityType.PLAYER);
        PlayerController playerController = new PlayerController(getBody(), gameCamera);
        Gdx.input.setInputProcessor(playerController);
        playerAnimation= new PlayerAnimation();


    }



    public void update(){
        //TODO
    }


    public void draw(SpriteBatch spriteBatch){
        //TODO
        update();
        Vector2 bodyPosition=getBody().getPosition();
        playerAnimation.draw(new Vector2(bodyPosition.x, bodyPosition.y), spriteBatch);
    }





}
