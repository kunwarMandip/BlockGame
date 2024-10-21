package com.mygdx.fallingblocks.level;

//hold waves data, enemy type and all that info
public class LevelDto {

    //wave dto
    //what it holds
    //enemy type, enemy Amount, separate into wave
    //some enemy have dynamic position, some are set automatically

    public class Waves{

        public String enemyType;
        public int amount;
        public boolean isPositionDynamic;


    }
}


