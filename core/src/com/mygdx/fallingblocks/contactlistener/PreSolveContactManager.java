package com.mygdx.fallingblocks.contactlistener;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.mygdx.fallingblocks.entity.enemies.Enemy;
import com.mygdx.fallingblocks.entity.player.Player;
import com.mygdx.fallingblocks.utilities.SolidColorCreator;

public class PreSolveContactManager {

    private final SolidColorCreator solidColorCreator;

    public PreSolveContactManager(SolidColorCreator solidColorCreator){
        this.solidColorCreator=solidColorCreator;
    }


    /**
     *
     * @param contact
     * @return
     */
    public boolean sameColorEnemyPlayer(Contact contact){
        Fixture a = contact.getFixtureA();
        Fixture b = contact.getFixtureB();

        if (!(a.getUserData() instanceof Player && b.getUserData() instanceof Enemy) &&
                !(b.getUserData() instanceof Player && a.getUserData() instanceof Enemy)) {
            return false;
        }

        Enemy enemy = (Enemy) (a.getUserData() instanceof Enemy ? a.getUserData() : b.getUserData());
        Integer playerID = solidColorCreator.getPlayerColorID();
        if (playerID.equals(enemy.getColorID())) {
            contact.setEnabled(false);
        }
        return true;
    }



}
