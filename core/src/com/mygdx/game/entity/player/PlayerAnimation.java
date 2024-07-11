package com.mygdx.game.entity.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.FallingBlocks;
import com.mygdx.game.GlobalVariables;

/**
 * Responsible for controlling all the animations
 * related to the player object
 */
public class PlayerAnimation {

    private final Texture playerTexture;

    public PlayerAnimation(){
        this.playerTexture= new Texture("box.png");
    }


    public void draw(Body body, SpriteBatch spriteBatch){


        float width=200/GlobalVariables.PPM;
        float height=200/GlobalVariables.PPM;

        float spawnLocationX=body.getPosition().x - (float) 2 / 2;
        float spawnLocationY=body.getPosition().y- (float) 2 / 2;
        spriteBatch.draw(playerTexture, spawnLocationX, spawnLocationY, 2, 2);

    }


}
