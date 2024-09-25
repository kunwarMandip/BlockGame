package com.mygdx.fallingblocks.contactlistener.handler;

import com.mygdx.fallingblocks.entity.enemies.Enemy;

public interface EnemyOuterBoundCollisionInterface {

     default void handlePlayerOuterBound(Enemy enemy){
         System.out.println("Running default handlePlayerOuterBound");
         enemy.isEnemyToBeRemoved=true;
     }
}
