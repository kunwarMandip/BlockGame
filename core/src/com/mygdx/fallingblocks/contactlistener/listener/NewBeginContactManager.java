package com.mygdx.fallingblocks.contactlistener.listener;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.mygdx.fallingblocks.contactlistener.handler.EnemyOuterBoundCollisionInterface;
import com.mygdx.fallingblocks.contactlistener.handler.PlayerEnemyCollisionInterface;
import com.mygdx.fallingblocks.entity.enemies.Enemy;
import com.mygdx.fallingblocks.entity.player.Player;
import com.mygdx.fallingblocks.map.objects.OuterBound;
import com.mygdx.fallingblocks.utilities.SolidTextureCreator;

public class NewBeginContactManager {

    PlayerEnemyCollisionInterface playerEnemyCollisionHandler;
    EnemyOuterBoundCollisionInterface enemyOuterBoundCollisionInterface;

    public NewBeginContactManager(PlayerEnemyCollisionInterface playerEnemyCollisionHandler,
                                  EnemyOuterBoundCollisionInterface enemyOuterBoundCollisionInterface){
        this.playerEnemyCollisionHandler=playerEnemyCollisionHandler;
        this.enemyOuterBoundCollisionInterface = enemyOuterBoundCollisionInterface;
    }

    /**
     * Handles collisions between a Player and an Enemy.
     * Marks the enemy as friendly if colors match; otherwise, sets the player as dead.
     *
     * @param contact The collision contact object.
     * @return true if the collision was between a Player and an Enemy; otherwise, false.
     */
    public boolean handleEnemyPlayerContact(Contact contact) {
        Fixture a = contact.getFixtureA();
        Fixture b = contact.getFixtureB();

        // Check if the contact involves both a Player and an Enemy
        if (!(a.getUserData() instanceof Player && b.getUserData() instanceof Enemy) &&
                !(b.getUserData() instanceof Player && a.getUserData() instanceof Enemy)) {
            return false;
        }

        System.out.println("Enemy Player Contact Handled");
        Player player = (Player) (a.getUserData() instanceof Player ? a.getUserData() : b.getUserData());
        Enemy enemy = (Enemy) (a.getUserData() instanceof Enemy ? a.getUserData() : b.getUserData());

        playerEnemyCollisionHandler.onPlayerHitEnemy(contact, player, enemy);
        // Check if the enemy is friendly
        if (SolidTextureCreator.playerColorID == enemy.getColorID()) {
            enemy.isFriendly = true;
            return true;
        }

        player.isPlayerDead=true;
        return true;
    }


    public boolean handleEnemyOuterBoundContact(Contact contact){
        Fixture a = contact.getFixtureA();
        Fixture b = contact.getFixtureB();

        if (!(a.getUserData() instanceof OuterBound && b.getUserData() instanceof Enemy) &&
                !(b.getUserData() instanceof OuterBound && a.getUserData() instanceof Enemy)) {
            return false;
        }

        Enemy enemy = (Enemy) (a.getUserData() instanceof Enemy ? a.getUserData() : b.getUserData());

        System.out.println("Enemy OuterBound Contact Handled");
        enemyOuterBoundCollisionInterface.handlePlayerOuterBound(enemy);
        return true;
    }


}
