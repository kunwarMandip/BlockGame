package com.mygdx.fallingblocks.entity.enemies;
import com.mygdx.fallingblocks.StaticVariables;

import java.util.Random;


public class EnemyTextureChooser {
    private final int targetNumber, rewardThreshold;
    private int rewardThresholdCounter;

    public EnemyTextureChooser(){
        targetNumber=1;
        rewardThreshold= StaticVariables.SAME_COLOR_ENEMY_THRESHOLD;
        rewardThresholdCounter=0;
    }

    /**
     * Chooses a random between 1 and 100.
     * @return returns true if random is 1, else false if its 0
     */
    public boolean update(){
        if(rewardThresholdCounter>=rewardThreshold){
            rewardThresholdCounter=0;
            return true;
        }

        //Generate a number between 1 and 100
        Random random= new Random();
        int min=1, max=100;
        int randomNum = random.nextInt((max - min) + 1) + min;
        if(randomNum==targetNumber){
            rewardThresholdCounter=0;
            return true;
        }

        rewardThresholdCounter++;
        return false;
    }


}
