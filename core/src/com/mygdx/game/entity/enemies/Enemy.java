package com.mygdx.game.entity.enemies;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.entity.Entity;

public class Enemy extends Entity {

    private Vector2 color;


    /**
     *
     * @param world the world to place the objects in
     * @param bodyDimension height and width of the object to be created
     * @param color the color of the block dropping:
     *              if y != 0, color of block may change to that.
     *              if y==0, block will not change color and the color of that will be fallingSpeed.x
     */
    public Enemy(World world, Vector2 bodyDimension, Vector2 color) {
        super(world, bodyDimension);
        this.color= color;
    }

    public void draw(SpriteBatch spriteBatch){

    }
}
