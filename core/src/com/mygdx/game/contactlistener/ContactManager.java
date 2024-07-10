package com.mygdx.game.contactlistener;

import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.GlobalVariables;
import com.mygdx.game.entity.EntityManager;
import com.mygdx.game.entity.enemies.Enemy;

/**
 * Same as GameContactListener.java but just with advanced handling
 */
public class ContactManager {

    private final World world;
    private final EntityManager entityManager;

    public ContactManager(World world, EntityManager entityManager){
        this.world=world;
        this.entityManager= entityManager;
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
        if (enemy == null) {
            System.out.println("Enemy Null: ContactManager.EnemyPlayerContact");
            return;
        }

        System.out.println("New Score: " + GlobalVariables.SCORE);
        GlobalVariables.SCORE++;
        entityManager.getEnemyManager().getEnemiesToRemove().add(enemy);
    }


    /**
     *
     * @param a should always be Enemy
     * @param b should always be outerBound
     */
    public void handleEnemyOuterBoundContact(Fixture a, Fixture b){
        System.out.println("Enemy and OuterBound Matched");
        Enemy enemy=(Enemy) a.getUserData();
        if(enemy==null){
            System.out.println("Enemy Null: ContactManager.handleEnemyOuterBoundContact");
            return;
        }
        System.out.println("Set to Remove Enemy");
        entityManager.getEnemyManager().getEnemiesToRemove().add(enemy);
//        entityManager.getEnemyManager().incrementEnemiesToSpawn();
//        entityManager.getEnemyManager().getEnemyGenerator().createEnemy(entityManager.getPlayer().getBody().getPosition());
    }

    public void EnemyFloorContact(Fixture a, Fixture b){
        Enemy enemy= (Enemy) b.getUserData();
        if(enemy==null){
            System.out.println("Enemy null.");
            return;
        }
//        GlobalVariables.score++;
//        entityManager.getEnemyManager().getEnemiesToRemove().add(enemy);
    }


    /**
     * Manages if player and enemy have the same color
     * @return true if same color, false if different color
     */
    public boolean friendlyEnemy(Fixture a, Fixture b){
        return false;
    }
}
