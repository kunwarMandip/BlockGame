package com.mygdx.fallingblocks.entity.enemies;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class EnemyAnimation {

    private final Texture texture;

    public EnemyAnimation(Texture texture){
        this.texture=texture;
    }

    public void draw(Vector2 enemyPosition, SpriteBatch spriteBatch){
        float textureWidth=6, textureHeight=6;
        float x=enemyPosition.x - textureWidth /2;
        float y=enemyPosition.y - textureHeight /2;
        spriteBatch.draw(texture, x, y, textureWidth, textureHeight);
    }

    public void draw(SpriteBatch spriteBatch, Vector2 enemyPosition){
        float textureWidth=6, textureHeight=6;
        float x=enemyPosition.x - textureWidth /2;
        float y=enemyPosition.y - textureHeight /2;
        spriteBatch.draw(texture, x, y, textureWidth, textureHeight);
    }

    public void dispose(){
        texture.dispose();
    }

//    private String chooseEnemyTexture(String playerColor){
//        //Enemy and Player match color
//        if(enemyTextureChooser.Updateable()){
//           return playerColor;
//        }
//
//        Random random = new Random();
//        String enemyColor;
//        int randomIndex;
//        do {
//            randomIndex = random.nextInt(DefineTexture.textureHashMapSize);
//            enemyColor = DefineTexture.textureNames[randomIndex];
//        } while (enemyColor.equals(playerColor));
//        return  enemyColor;
//    }

}
