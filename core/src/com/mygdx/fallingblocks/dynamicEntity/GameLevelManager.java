package com.mygdx.fallingblocks.dynamicEntity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.fallingblocks.level.enemy.Enemy;
import com.mygdx.fallingblocks.reduction.GameLevelDto;
import com.mygdx.fallingblocks.reduction.WavesDto;

import java.util.ArrayList;

public class GameLevelManager {

    private final GameLevelDto gameLevelDto;
    private final ArrayList<Enemy> activeEnemies= new ArrayList<>();

    private WavesDto wavesDto;

    private float waveStartDelayCounter= 0f;
    private float waveDurationCounter= 0f;

    public GameLevelManager(){
        gameLevelDto= new GameLevelDto(1);
        wavesDto=gameLevelDto.getNextWavesDto();
    }

    public void update(float delta){
        for(Enemy enemy: activeEnemies){
            wavesDto.isEnemySpawnAble(delta);
            enemy.update(delta);
        }
    }

    public void draw(SpriteBatch spriteBatch){
        for(Enemy enemy: activeEnemies){
            enemy.draw(spriteBatch);
        }
    }



    public void setNextWave(){
        wavesDto=gameLevelDto.getNextWavesDto();
    }

    private void handleNextEnemyNull(){
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
