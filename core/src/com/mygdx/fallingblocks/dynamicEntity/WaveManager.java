package com.mygdx.fallingblocks.dynamicEntity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.mygdx.fallingblocks.dynamicEntity.enemy.Enemy;
import com.mygdx.fallingblocks.reduction.WavesDto;

import java.util.ArrayList;
import java.util.List;

public class WaveManager {

    private final WavesDto wavesDto;
    private final List<Enemy> currentEnemies= new ArrayList<>();

    private int currentWaveCounter;
    private float waveStartDelayCounter= 0f;
    private float waveDurationCounter= 0f;


    public WaveManager(WavesDto wavesDto){
        this.wavesDto=wavesDto;
    }



    public void update(float delta){
    }


    private boolean isWaveStartDelayCompleted(float delta){
        if(wavesDto.getStartDelay() > waveStartDelayCounter){
            waveStartDelayCounter+=delta;
            return false;
        }

        return true;
    }


    private boolean isWaveDurationCompleted(float delta){
        if(wavesDto.getWaveDuration() > waveDurationCounter){
            waveDurationCounter+=delta;
            return false;
        }

        return true;
    }



}
