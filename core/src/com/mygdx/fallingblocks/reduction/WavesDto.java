package com.mygdx.fallingblocks.reduction;

import com.mygdx.fallingblocks.level.enemy.Enemy;

import java.util.Stack;

public class WavesDto {

    private final boolean isWaveTimed;
    private final double waveDuration, waveStartDelay;
    private final String spawnPattern;
    private final Stack<Enemy> enemies;

    private float deltaTime=0f;
    public WavesDto(Stack<Enemy> enemies, boolean isWaveTimed, double duration, double waveStartDelay, String spawnPattern) {
        this.enemies = enemies;
        this.isWaveTimed=isWaveTimed;
        this.waveDuration = duration;
        this.spawnPattern = spawnPattern;
        this.waveStartDelay = waveStartDelay;
    }

    public Enemy isEnemySpawnAble(float deltaTime){
        this.deltaTime+=deltaTime;

        if(this.deltaTime!=waveDuration){
            return null;
        }
        this.deltaTime=0f;
        return enemies.pop();
    }

    public double getWaveStartDelay() {
        return waveStartDelay;
    }

    public boolean isWaveTimed() {
        return isWaveTimed;
    }

    public double getWaveDuration() {
        return waveDuration;
    }

    public String getSpawnPattern() {
        return spawnPattern;
    }

    public Enemy getNextEnemy(){
        return enemies.pop();
    }



//    public static class WavesBuilder{
//        private int waveNumber;
//        private boolean isWaveTimed;
//        private double startDelay, duration;
//        private String spawnPattern;
//        private Stack<Enemy> enemies;
//
//
//        public WavesBuilder setWaveNumber(int waveNumber){
//            this.waveNumber=waveNumber;
//            return this;
//        }
//
//        public WavesBuilder setStartDelay(double startDelay) {
//            this.startDelay = startDelay;
//            return this;
//        }
//
//        public WavesBuilder setDuration(double duration) {
//            this.duration = duration;
//            return this;
//        }
//
//        public WavesBuilder setSpawnPattern(String spawnPattern) {
//            this.spawnPattern = spawnPattern;
//            return this;
//        }
//
//        public WavesBuilder setEnemies(Stack<Enemy> enemies) {
//            this.enemies = enemies;
//            return this;
//        }
//
//        public WavesBuilder isWaveTimed(boolean isWaveTimed){
//            this.isWaveTimed=isWaveTimed;
//            return this;
//        }
//
//        public WavesDto buildWave(){
//            return new WavesDto(waveNumber, spawnPattern, enemies, isWaveTimed, duration, startDelay);
//        }
//
//    }
}
