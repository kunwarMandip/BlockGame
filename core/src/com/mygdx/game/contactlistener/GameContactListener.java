package com.mygdx.game.contactlistener;

import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.GameStateVariables;
import com.mygdx.game.entity.EntityManager;
import com.mygdx.game.entity.enemies.Enemy;
import com.mygdx.game.entity.player.Player;
import com.mygdx.game.map.objects.OuterBound;

/**
 * Responsible for handling when two box2D bodies collide or touch
 */
public class GameContactListener implements ContactListener {

    private final ContactManager contactManager;

    public GameContactListener(EntityManager entityManager, GameStateVariables gameStateVariables){
        contactManager= new ContactManager(entityManager, gameStateVariables);
    }

    @Override
    public void beginContact(Contact contact) {
        Fixture a = contact.getFixtureA();
        Fixture b = contact.getFixtureB();

        // Just checking for missing error aka Random errors or bug
        if (a == null || b == null){return;}
        if (a.getUserData() == null|| b.getUserData() == null){return;}

        //JUST KEEP ADDING ELSE IF
        if(checkPlayerEnemyContact(a, b)){
            return;
        }else if(checkEnemyBoundContact(a, b)){
            return;
        }

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

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
