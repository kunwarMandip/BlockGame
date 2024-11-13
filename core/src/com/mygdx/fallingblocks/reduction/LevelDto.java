package com.mygdx.fallingblocks.reduction;

import java.util.List;
import java.util.Stack;

public class LevelDto {

    private int levelNumber;
    private final Stack<WavesDto> wavesDtoStack= new Stack<>();

    /**
     * Get wavesDto and the remove it from the stack
     * @return Top wavesDto if exists, else null
     */
    public WavesDto getWavesDto(){
        if(wavesDtoStack.isEmpty()){
            return null;
        }

        return wavesDtoStack.pop();
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public void setLevelNumber(int levelNumber){
        this.levelNumber=levelNumber;
    }

    public List<WavesDto> getWavesDtoList() {
        return wavesDtoStack;
    }

    public void addWave(WavesDto wavesDto){
        this.wavesDtoStack.add(wavesDto);
    }



}
