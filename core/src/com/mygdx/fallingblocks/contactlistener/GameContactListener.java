package com.mygdx.fallingblocks.contactlistener;

import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.fallingblocks.GameStateVariables;
import com.mygdx.fallingblocks.entity.EntityManager;
import com.mygdx.fallingblocks.entity.enemies.Enemy;
import com.mygdx.fallingblocks.entity.player.Player;
import com.mygdx.fallingblocks.map.objects.OuterBound;
import com.mygdx.fallingblocks.utilities.SolidColorCreator;

/**
 * Responsible for handling when two box2D bodies collide or touch
 */
public class GameContactListener implements ContactListener {

    private final ContactManager contactManager;
    private final SolidColorCreator solidColorCreator;
    public GameContactListener(EntityManager entityManager, GameStateVariables gameStateVariables){
        contactManager= new ContactManager(entityManager, gameStateVariables);
        this.solidColorCreator=entityManager.solidColorCreator;
    }

    @Override
    public void beginContact(Contact contact) {
        Fixture a = contact.getFixtureA();
        Fixture b = contact.getFixtureB();

        // Just checking for missing error aka Random errors or bug
        if (a == null || b == null){return;}
        if (a.getUserData() == null|| b.getUserData() == null){return;}

        //JUST KEEP ADDING ELSE IF
        if(checkPlayerEnemyContact(a, b)){return;}
        if(checkEnemyBoundContact(a, b)){return;}

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

        Fixture a = contact.getFixtureA();
        Fixture b = contact.getFixtureB();

        if (a == null || b == null){return;}
        if (a.getUserData() == null|| b.getUserData() == null){return;}

        if (a.getUserData() instanceof Player && b.getUserData() instanceof Enemy){
            Enemy enemy= (Enemy) b.getUserData();
            Integer playerID= solidColorCreator.getPlayerColorID();
            if (playerID.equals(enemy.getColorID())) {
                enemy.isFriendly=true;
                contact.setEnabled(false);
            }
        }

        if (a.getUserData() instanceof Enemy && b.getUserData() instanceof Player){
            Enemy enemy= (Enemy) a.getUserData();
            Integer playerID= solidColorCreator.getPlayerColorID();
            if (playerID.equals(enemy.getColorID())) {
                enemy.isFriendly=true;
                contact.setEnabled(false);
            }
        }


    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }


    private boolean checkPlayerEnemyContact(Fixture a, Fixture b){
        System.out.println("Checking Player and Enemy");
        if (a.getUserData() instanceof Player && b.getUserData() instanceof Enemy){
            contactManager.EnemyPlayerContact(a, b);
            return true;
        }

        if (a.getUserData() instanceof Enemy && b.getUserData() instanceof Player){
            contactManager.EnemyPlayerContact(b, a);
            return true;
        }
        System.out.println("Not Matched: Player and Enemy");
        return false;
    }

    private boolean checkEnemyBoundContact(Fixture a, Fixture b){
        System.out.println("Checking Enemy and OuterBound");
        if (a.getUserData() instanceof Enemy && b.getUserData() instanceof OuterBound){
            contactManager.handleEnemyOuterBoundContact(a, b);
            return true;
        }

        if (a.getUserData() instanceof OuterBound && b.getUserData() instanceof Enemy){
            contactManager.handleEnemyOuterBoundContact(b, a);
            return true;
        }
        System.out.println("Not Matched: Enemy and OuterBound");
        return false;

    }


}
