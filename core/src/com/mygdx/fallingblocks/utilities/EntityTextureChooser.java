package com.mygdx.fallingblocks.utilities;

import java.util.Random;

import static com.mygdx.fallingblocks.StaticVariables.*;

public class EntityTextureChooser {

    private final int colorListSize;
    private int rewardThresholdCounter=0;
    private final Random random= new Random();

    public EntityTextureChooser(int colorListSize){
        this.colorListSize=colorListSize;
    }

    /**
     * @param playerColorNumber current Unique id of the player texture color
     * @return int id of the color texture to get
     */
    public int getColorNumber(int playerColorNumber){
        if(rewardThresholdCounter >= MAX_LUCKY_REWARD_THRESHOLD){
            rewardThresholdCounter=0;
            return playerColorNumber;
        }

        if(rewardThresholdCounter >= MIN_LUCKY_REWARD_THRESHOLD){
            int randomNum=random.nextInt(TEXTURE_CHOOSER_BOUNDS);

            if(randomNum==TEXTURE_CHOOSER_BOUNDS/2){
                rewardThresholdCounter=0;
                return playerColorNumber;
            }
        }

        rewardThresholdCounter++;
        return differentRandomColor(playerColorNumber);
    }


    public int differentRandomColor(int playerColorNumber){
        int randomColor;
        do{
            randomColor = random.nextInt(colorListSize); // Gen
        }while(playerColorNumber==randomColor);
        System.out.println("COLOR:: "+ randomColor + " . " + colorListSize);
        return randomColor;
    }


}
