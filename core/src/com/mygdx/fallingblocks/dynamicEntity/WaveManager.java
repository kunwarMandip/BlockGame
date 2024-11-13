package com.mygdx.fallingblocks.dynamicEntity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.fallingblocks.level.Enemy;
import com.mygdx.fallingblocks.reduction.WavesDto;

import java.util.ArrayList;

public class WaveManager {

    private WavesDto wavesDto;
    private final ArrayList<Enemy> activeEnemies= new ArrayList<>();

    private float waveStartDelayCounter= 0f;
    private float waveDurationCounter= 0f;

    public WaveManager(WavesDto wavesDto){
        this.wavesDto=wavesDto;
    }

    public void setWavesDto(WavesDto wavesDto){
        this.wavesDto=wavesDto;
    }

    public void update(float delta){
    }

    public void spawnNextEnemy(){
        Enemy enemy= wavesDto.getNextEnemy();
        activeEnemies.add(enemy);
    }

    public void draw(SpriteBatch spriteBatch){
        for(Enemy enemy: activeEnemies){
            enemy.draw(spriteBatch);
        }
    }

    /**
     * S
     */
    private void getNextWave(){
    }

    private boolean isWaveStartDelayCompleted(float delta){
        if(wavesDto.getWaveStartDelay() > waveStartDelayCounter){
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
