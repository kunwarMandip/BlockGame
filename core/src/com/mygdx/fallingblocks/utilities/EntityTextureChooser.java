package com.mygdx.fallingblocks.utilities;

import static com.mygdx.fallingblocks.GlobalStaticVariables.*;
import static com.mygdx.fallingblocks.GlobalStaticVariables.GET_RANDOM_INTEGER;

public class EntityTextureChooser {

    private final int colorListSize;
    private int rewardThresholdCounter=0;

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
            int randomNumber=GET_RANDOM_INTEGER(TEXTURE_CHOOSER_LUCK_PROBABILITY);
            if(randomNumber==1){
                rewardThresholdCounter=0;
                return randomNumber;
            }
        }

        rewardThresholdCounter++;
        return differentRandomColor(playerColorNumber);
    }


    public int differentRandomColor(int playerColorNumber){
        int randomColor;
        do{
            randomColor=GET_RANDOM_INTEGER(colorListSize);
        }while(playerColorNumber==randomColor);
        return randomColor;
    }


}
