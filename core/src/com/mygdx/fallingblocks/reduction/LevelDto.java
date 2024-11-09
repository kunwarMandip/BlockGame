package com.mygdx.fallingblocks.reduction;

import java.util.ArrayList;
import java.util.List;

public class LevelDto {

    private int levelNumber;
    private final List<WavesDto> wavesDtoList= new ArrayList<>();


    public int getLevelNumber() {
        return levelNumber;
    }

    public void setLevelNumber(int levelNumber){
        this.levelNumber=levelNumber;
    }

    public List<WavesDto> getWavesDtoList() {
        return wavesDtoList;
    }

    public void addWave(WavesDto wavesDto){
        this.wavesDtoList.add(wavesDto);
    }



}
