package com.mygdx.fallingblocks.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.fallingblocks.contactlistener.handler.EnemyOuterBoundCollisionInterface;
import com.mygdx.fallingblocks.contactlistener.handler.PlayerEnemyCollisionInterface;
import com.mygdx.fallingblocks.contactlistener.listener.GameContactListener;
import com.mygdx.fallingblocks.entity.enemies.NewEnemyManager;
import com.mygdx.fallingblocks.entity.player.NewPlayer;
import com.mygdx.fallingblocks.utilities.SolidTextureCreator;

public class NewEntityManager {

    private final NewPlayer newPlayer;
    private final NewEnemyManager newEnemyManager;
    private final SolidTextureCreator solidColorCreator;

    public NewEntityManager(World world,
                            PlayerEnemyCollisionInterface playerEnemyCollisionHandler,
                            EnemyOuterBoundCollisionInterface enemyOuterBoundCollisionInterface){
        this.solidColorCreator= new SolidTextureCreator();
        this.newPlayer = new NewPlayer(world, solidColorCreator);
        this.newEnemyManager= new NewEnemyManager(world, solidColorCreator);

        GameContactListener gameContactListener= new GameContactListener(playerEnemyCollisionHandler, enemyOuterBoundCollisionInterface);
        world.setContactListener(gameContactListener);
    }


    public void update(int numEnemiesToSpawn, Vector2 enemyMovementSpeed){
        newEnemyManager.update(numEnemiesToSpawn, newPlayer.getPosition(), enemyMovementSpeed);
    }

    public void draw(SpriteBatch spriteBatch){
        newPlayer.draw(spriteBatch);
        newEnemyManager.draw(spriteBatch);
    }

    public NewPlayer getNewPlayer(){return newPlayer;}
}
