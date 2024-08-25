package com.mygdx.fallingblocks.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.fallingblocks.GameStateVariables;
import com.mygdx.fallingblocks.contactlistener.GameContactListener;
import com.mygdx.fallingblocks.entity.enemies.EnemyManager;
import com.mygdx.fallingblocks.entity.player.Player;
import com.mygdx.fallingblocks.utilities.SolidColorCreator;

/**
 * Manage all dynamic box2D entities. Player, Enemies
 */
public class EntityManager {

    private final Player player;
    private final EnemyManager enemyManager;
    public SolidColorCreator solidColorCreator;

    /**
     * Sets the player, enemies, and the contact listener
     * @param world box2D world to deploy body in
     */
    public EntityManager(World world, TiledMap tiledMap, GameStateVariables gameStateVariables){
        this.solidColorCreator = new SolidColorCreator();
        player= new Player(world, gameStateVariables, solidColorCreator);
        enemyManager= new EnemyManager(world, tiledMap, gameStateVariables, solidColorCreator);
        GameContactListener gameContactListener = new GameContactListener(this, gameStateVariables);
        world.setContactListener(gameContactListener);
    }

    public void update(float delta){
        Vector2 playerPosition=player.getBody().getPosition();
        player.update(solidColorCreator.getPlayerColorID());
        enemyManager.update(delta, playerPosition);
    }

    /**
     * Calls to draw every entity such as Players and Enemies
     * Enemy has to be drawn first due to the EnemySpawnDirection.Class
     * @param spriteBatch used to draw sprites faster and efficiently
     */
    public void drawEntities(SpriteBatch spriteBatch){
        enemyManager.draw(spriteBatch);
        player.draw(spriteBatch);
    }

    public Player getPlayer() {
        return player;
    }

}
