package com.mygdx.fallingblocks.entity.player;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.fallingblocks.utilities.SolidTextureCreator;

public class PlayerAnimation {

    private Texture texture;
    private final SolidTextureCreator solidColorCreator;

    public PlayerAnimation(SolidTextureCreator solidColorCreator){
        this.solidColorCreator=solidColorCreator;
        setTexture(0);
    }

    public void setTexture(int colorNumber){
        this.texture=solidColorCreator.getColor(colorNumber, true);
    }


    public void draw(Vector2 playerPosition, SpriteBatch spriteBatch){
        float textureWidth=4, textureHeight=4;
        float x=playerPosition.x - textureWidth /2;
        float y=playerPosition.y - textureHeight /2;
        spriteBatch.draw(texture, x, y, textureWidth, textureHeight);
    }


}
