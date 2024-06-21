package com.mygdx.game.entity.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.entity.Entity;

/**
 * Object Body that the user controls aka: the user itself.
 */
public class Player extends Entity {

    private PlayerController playerController;

    /**
     * Sets the player "object" on the given world
     * @param world the world to place the objects in.
     * @param bodyDimension height and width of the object to be created.
     */
    public Player(World world, Vector2 bodyDimension) {
        super(world, bodyDimension);
        playerController= new PlayerController(this);
        Gdx.input.setInputProcessor(playerController);
    }



    public void update(){


        //TODO
    }

    @Override
    public void draw(SpriteBatch spriteBatch){
        //TODO
        update();
    }



}
