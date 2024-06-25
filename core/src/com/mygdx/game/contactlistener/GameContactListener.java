package com.mygdx.game.contactlistener;

import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.entity.EntityType;

public class GameContactListener implements ContactListener {

    private final ContactManager contactManager;

    public GameContactListener(){
        contactManager= new ContactManager();
    }
    @Override
    public void beginContact(Contact contact) {
        Fixture a = contact.getFixtureA();
        Fixture b = contact.getFixtureB();

        // Just checking for missing error aka Random errors or bug
        if (a == null || b == null){return;}
        if (a.getUserData() == null|| b.getUserData() == null){return;}

        // Player collides with Enemy OR enemy collides with Player
        if ((a.getUserData() == EntityType.PLAYER && b.getUserData() == EntityType.ENEMY) ||
                (a.getUserData() == EntityType.ENEMY && b.getUserData() == EntityType.PLAYER)) {
            contactManager.EnemyPlayerContact(a, b);
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
}
