package com.mygdx.fallingblocks.contactlistener;

import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.fallingblocks.utilities.GameStateVariables;
import com.mygdx.fallingblocks.entity.EntityManager;

/**
 * Responsible for handling when two box2D bodies collide or touch
 */
public class GameContactListener  implements ContactListener{

    private final BeginContactManager beginContactManager;
    private final PreSolveContactManager preSolveContactManager;

    public GameContactListener(EntityManager entityManager, GameStateVariables gameStateVariables){
        this.beginContactManager= new BeginContactManager(gameStateVariables, entityManager.solidColorCreator);
        this.preSolveContactManager=new PreSolveContactManager(entityManager.solidColorCreator);
    }

    @Override
    public void beginContact(Contact contact) {

        //Checking for null values
        Fixture a = contact.getFixtureA();
        Fixture b = contact.getFixtureB();
        if (a == null || b == null){return;}
        if (a.getUserData() == null|| b.getUserData() == null){return;}

        //JUST KEEP ADDING ELSE IF
        if(beginContactManager.handleEnemyPlayerContact(contact)){return;}
        if(beginContactManager.handleEnemyOuterBoundContact(contact)){return;}

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

        //Checking for null values
        Fixture a = contact.getFixtureA();
        Fixture b = contact.getFixtureB();
        if (a == null || b == null){return;}
        if (a.getUserData() == null|| b.getUserData() == null){return;}

        if(preSolveContactManager.sameColorEnemyPlayer(contact)){return;}
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }




}
