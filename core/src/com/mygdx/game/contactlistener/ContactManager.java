package com.mygdx.game.contactlistener;

import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.entity.enemies.Enemy;

/**
 * Same as GameContactListener.java but just with advanced handling
 */
public class ContactManager {

    private final World world;
    private Array<Enemy> enemiesToRemove;

    public ContactManager(World world, Array<Enemy> enemiesToRemove){
        this.world=world;
        this.enemiesToRemove= enemiesToRemove;
    }


    /**
     * Handles contact between enemy and player instance
     * @param a the player instance class
     * @param b the enemy instance class
     */
    public void EnemyPlayerContact(Fixture a, Fixture b){
        System.out.println("Player and Enemy Touching.");
        if(friendlyEnemy(a, b)){
            return;
        }

        //Add that enemy to remove it from the world
        Enemy enemy= (Enemy) b.getUserData();

        if (enemy != null) {
            System.out.println("Enemy not null");
            enemiesToRemove.add(enemy);
            return;
        }
        System.out.println("Enemy null.");

    }




    /**
     * Manages if player and enemy have the same color
     * @return true if same color, false if different color
     */
    public boolean friendlyEnemy(Fixture a, Fixture b){
        return false;
    }
}
