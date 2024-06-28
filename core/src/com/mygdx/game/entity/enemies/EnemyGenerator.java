package com.mygdx.game.entity.enemies;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

/**
 * Choose enemy and then
 */
public class EnemyGenerator {

    private World world;
    private Vector2 spawnLocation;
    private Vector2 playerLocation;

    public EnemyGenerator(World world){
        this.world=world;
        spawnLocation= new Vector2();
        playerLocation= new Vector2();
    }


    public void update(Array<Enemy> currentEnemies){
        currentEnemies.add(new Enemy(world, new Vector2(5, 5), 6));
//        Enemy enemy= new Enemy(world, new Vector2(5, 5), 6);
    }

    public void chooseEnemy(){
    }


    public void setPlayerLocation(float playerLocationX){
        playerLocation.x=playerLocationX;
    }

    public void spawnEnemy(){

    }

}
