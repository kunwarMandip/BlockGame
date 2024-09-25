package com.mygdx.fallingblocks.contactlistener.handler;

import com.badlogic.gdx.physics.box2d.Contact;
import com.mygdx.fallingblocks.entity.enemies.Enemy;
import com.mygdx.fallingblocks.entity.player.Player;
import com.mygdx.fallingblocks.utilities.SolidTextureCreator;

public interface PlayerEnemyCollisionInterface {

     default void onPlayerHitEnemy(Contact contact, Player player, Enemy enemy){
          System.out.println("Running default onPlayerHitEnemyMethod");
          enemy.isEnemyToBeRemoved=true;

          if (SolidTextureCreator.playerColorID == enemy.getColorID()) {
               enemy.isFriendly = true;
          }

          player.isPlayerDead=true;
     }
}
