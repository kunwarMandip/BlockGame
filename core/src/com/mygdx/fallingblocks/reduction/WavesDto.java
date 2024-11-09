package com.mygdx.fallingblocks.reduction;

import com.mygdx.fallingblocks.level.Enemy;

import java.util.List;

public class WavesDto {

    private final int waveNumber;
    private final boolean isWaveTimed;
    private final double waveDuration, startDelay;
    private final String spawnPattern;
    private final List<Enemy> enemies;

    private int lastWaveNumber;
    public WavesDto(int waveNumber, String spawnPattern, List<Enemy> enemies, boolean isWaveTimed, double duration, double startDelay) {
        this.waveNumber = waveNumber;
        this.spawnPattern = spawnPattern;
        this.enemies = enemies;
        this.isWaveTimed=isWaveTimed;
        this.waveDuration = duration;
        this.startDelay = startDelay;
    }


    public int getWaveNumber() {
        return waveNumber;
    }

    public int getLastWaveNumber(){
        return 5;
    }

    public boolean isWaveTimed() {
        return isWaveTimed;
    }

    public double getStartDelay() {
        return startDelay;
    }

    public double getWaveDuration() {
        return waveDuration;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public String getSpawnPattern() {
        return spawnPattern;
    }


    public static class WavesBuilder{
        private int waveNumber;
        private boolean isWaveTimed;
        private double startDelay, duration;
        private String spawnPattern;
        private List<Enemy> enemies;


        public WavesBuilder setWaveNumber(int waveNumber){
            this.waveNumber=waveNumber;
            return this;
        }

        public WavesBuilder setStartDelay(double startDelay) {
            this.startDelay = startDelay;
            return this;
        }

        public WavesBuilder setDuration(double duration) {
            this.duration = duration;
            return this;
        }

        public WavesBuilder setSpawnPattern(String spawnPattern) {
            this.spawnPattern = spawnPattern;
            return this;
        }

        public WavesBuilder setEnemies(List<Enemy> enemies) {
            this.enemies = enemies;
            return this;
        }

        public WavesBuilder isWaveTimed(boolean isWaveTimed){
            this.isWaveTimed=isWaveTimed;
            return this;
        }

        public WavesDto buildWave(){
            return new WavesDto(waveNumber, spawnPattern, enemies, isWaveTimed, duration, startDelay);
        }

    }
}
