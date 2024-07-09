package com.mygdx.game.contactlistener;

import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.entity.EntityManager;
import com.mygdx.game.entity.enemies.Enemy;
import com.mygdx.game.entity.player.Player;

/**
 * Responsible for handling when two box2D bodies collide or touch
 */
public class GameContactListener implements ContactListener {

    private final ContactManager contactManager;

    public GameContactListener(World world, EntityManager entityManager){
        contactManager= new ContactManager(world, entityManager);
    }

    @Override
    public void beginContact(Contact contact) {
        Fixture a = contact.getFixtureA();
        Fixture b = contact.getFixtureB();

        // Just checking for missing error aka Random errors or bug
        if (a == null || b == null){return;}
        if (a.getUserData() == null|| b.getUserData() == null){return;}

        if(checkPlayerEnemyContact(a, b)){
            return;
        }
        if(checkEnemyFloorContact(a, b)){
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

    /**
     * Check if its player and enemy Co
     * @param a Fixture A
     * @param b Fixture b
     * @return true if its player and enemy, false if it isn't
     */
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
        System.out.println("False");
        return false;
    }

    private boolean checkEnemyFloorContact(Fixture a, Fixture b){
        System.out.println("Checking Enemy and Wall");
        if(a.getUserData() instanceof Enemy && b.getUserData()=="wall"){
            contactManager.EnemyFloorContact(a, b);
            return  true;
        }

        if(b.getUserData() instanceof Enemy && a.getUserData()=="wall"){
            contactManager.EnemyFloorContact(a, b);
            return true;
        }
        System.out.println("False");
        return false;
    }


}
