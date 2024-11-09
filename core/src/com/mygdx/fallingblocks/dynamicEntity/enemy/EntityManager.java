package com.mygdx.fallingblocks.dynamicEntity.enemy;

import com.badlogic.gdx.utils.Array;
import com.mygdx.fallingblocks.reduction.WavesDto;

public class EntityManager {

    private final Array<Enemy> spawnedEnemies= new Array<>();
    private final Array<Enemy> enemiesToSpawn= new Array<>();

    private final WavesDto wavesDto;
    private int currentWave;
    private int lastWave;

    public EntityManager(WavesDto wavesDto){
        this.wavesDto=wavesDto;
        this.currentWave=0;
        this.lastWave= wavesDto.getLastWaveNumber();
    }


    public void update(){

    }



}
