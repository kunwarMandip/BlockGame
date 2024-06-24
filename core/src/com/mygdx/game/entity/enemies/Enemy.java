package com.mygdx.game.entity.enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.FallingBlocks;
import com.mygdx.game.entity.Entity;

public class Enemy extends Entity {

    private Vector2 color;
    private Texture texture;

    private final float width=200/ FallingBlocks.PPM;
    private final float height=200/FallingBlocks.PPM;

    /**
     * Before enemies drop, there has to be a wait time to show the user
     * that the enemies are dropping
     */
    private int waitTime = 0;

    /**
     *
     * @param world the world to place the objects in
     * @param bodyDimension height and width of the object to be created
     * @param color the color of the block dropping:
     *              if y != 0, color of block may change to that.
     *              if y==0, block will not change color and the color of that will be fallingSpeed.x
     */
    public Enemy(World world, Vector2 bodyDimension, float spawnLocationX) {
        super(world, bodyDimension, spawnLocationX);
        texture=new Texture("box.png");
    }



    /**
     * Override this method on YOUR enemies class
     * to do actions with them
     */
    public void update(){
        waitTime ++;
        if(waitTime >100){
            setBodyDynamic();
        }
    }

    public void draw(SpriteBatch spriteBatch){
        update();
        Vector2 bodyPosition=getBody().getPosition();
        spriteBatch.draw(texture, bodyPosition.x, bodyPosition.y, width, height);
    }

    /**
     * Changed Body to dynamic to allow it to drop
     */
    public void setBodyDynamic(){
        getBody().setType(BodyDef.BodyType.DynamicBody);
    }




}
