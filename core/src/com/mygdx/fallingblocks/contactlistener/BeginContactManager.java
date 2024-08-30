package com.mygdx.fallingblocks.contactlistener;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.mygdx.fallingblocks.utilities.GameStateVariables;
import com.mygdx.fallingblocks.entity.enemies.Enemy;
import com.mygdx.fallingblocks.entity.player.Player;
import com.mygdx.fallingblocks.map.objects.OuterBound;
import com.mygdx.fallingblocks.utilities.DynamicTextureCreator;

/**
 * Handle all methods for Begin Contact in contact manager
 */
public class BeginContactManager {

    private final DynamicTextureCreator solidColorCreator;
    private final GameStateVariables gameStateVariables;
    public BeginContactManager(GameStateVariables gameStateVariables, DynamicTextureCreator solidColorCreator){
        this.gameStateVariables=gameStateVariables;
        this.solidColorCreator=solidColorCreator;
    }


    /**
     *
     * @param contact
     * @return if handled, return true
     */
    public boolean handleEnemyPlayerContact(Contact contact) {
        Fixture a = contact.getFixtureA();
        Fixture b = contact.getFixtureB();

        if (!(a.getUserData() instanceof Player && b.getUserData() instanceof Enemy) &&
                !(b.getUserData() instanceof Player && a.getUserData() instanceof Enemy)) {
            return false;
        }

        System.out.println("Enemy Player Contact Handled");
        Enemy enemy = (Enemy) (a.getUserData() instanceof Enemy ? a.getUserData() : b.getUserData());
        Integer playerID = solidColorCreator.getPlayerColorID();
        if (playerID.equals(enemy.getColorID())) {
            enemy.isFriendly = true;
            return true;
        }
        gameStateVariables.resetGameVariables(true);
        return true;
    }

    /**
     *
     * @param contact
     * @return if handled, return true
     */
    public boolean handleEnemyOuterBoundContact(Contact contact){
        Fixture a = contact.getFixtureA();
        Fixture b = contact.getFixtureB();

        if (!(a.getUserData() instanceof OuterBound && b.getUserData() instanceof Enemy) &&
                !(b.getUserData() instanceof OuterBound && a.getUserData() instanceof Enemy)) {
            return false;
        }

        System.out.println("Enemy OuterBound Contact Handled");
        Enemy enemy = (Enemy) (a.getUserData() instanceof Enemy ? a.getUserData() : b.getUserData());
        gameStateVariables.incrementScore();
        enemy.isEnemyToBeRemoved=true;
        return true;
    }
}
