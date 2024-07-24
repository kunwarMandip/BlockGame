package com.mygdx.game.contactlistener;

import com.badlogic.gdx.physics.box2d.Fixture;
import com.mygdx.game.GameStateVariables;
import com.mygdx.game.entity.EntityManager;
import com.mygdx.game.entity.enemies.Enemy;
import com.mygdx.game.entity.enemies.EnemyManager;

/**
 * Same as GameContactListener.java but just with advanced handling
 */
public class ContactManager {

    private final EntityManager entityManager;
    private final EnemyManager enemyManager;
    private final GameStateVariables gameStateVariables;

    public ContactManager(EntityManager entityManager, GameStateVariables gameStateVariables){
        this.entityManager= entityManager;
        this.enemyManager=entityManager.getEnemyManager();
        this.gameStateVariables=gameStateVariables;
    }


    /**
     * Handles contact between enemy and player instance
     * @param a the player instance class
     * @param b the enemy instance class
     */
    public void EnemyPlayerContact(Fixture a, Fixture b){
        System.out.println("Player and Enemy Touching.");

        Enemy enemy= (Enemy) b.getUserData();
        if(checkEnemyNull(enemy)){return;}

        gameStateVariables.resetGame(true);
    }


    /**
     * When Enemy contacts OuterBound, it gets deleted
     * @param a should always be Enemy
     * @param b should always be outerBound
     */
    public void handleEnemyOuterBoundContact(Fixture a, Fixture b){
        System.out.println("Enemy and OuterBound Matched");

        Enemy enemy=(Enemy) a.getUserData();
        if(checkEnemyNull(enemy)){return;}

        gameStateVariables.incrementScore();
        enemyManager.getEnemiesToRemove().add(enemy);
    }



    private boolean checkEnemyNull(Enemy enemy){
        if (enemy == null) {
            throw new RuntimeException("ContactManager checkEnemyNull");
        }
        return false;
    }

}
