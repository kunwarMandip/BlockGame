package com.mygdx.game.contactlistener;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;

public class ContactManager {

    public ContactManager(){

    }


    public void EnemyPlayerContact(Fixture a, Fixture b){
        if(friendlyEnemy(a, b)){
            return;
        }
        System.out.println("Enemy touching");

    }

    /**
     * Manages if player and enemy have the same color
     * @return true if same color, false if different color
     */
    public boolean friendlyEnemy(Fixture a, Fixture b){

        return false;
    }
}
