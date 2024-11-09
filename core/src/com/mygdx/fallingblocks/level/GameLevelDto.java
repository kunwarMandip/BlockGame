package com.mygdx.fallingblocks.level;

import com.badlogic.gdx.utils.Array;
import com.mygdx.fallingblocks.reduction.WavesDto;

public class GameLevelDto {

    private final Player player;
    private final Array<WavesDto> waves;

    private final boolean isTimeLimited;
    private final float timePassed;
    private final float maximumTimeAllowed;

    private final float gameScore;

    private String tiledMapPath;

    public GameLevelDto(Player player,
                        Array<WavesDto> waves,
                        boolean isTimeLimited,
                        Float timePassed, Float maximumTimeAllowed, Float gameScore) {
        this.player=player;
        this.waves=waves;
        this.isTimeLimited=isTimeLimited;
        this.timePassed=timePassed;
        this.maximumTimeAllowed=maximumTimeAllowed;
        this.gameScore=gameScore;
    }

    // Getters for the fields
    public Player getPlayer() {
        return player;
    }

    public Array<WavesDto> getWaves() {
        return waves;
    }

    public boolean isTimeLimited() {
        return isTimeLimited;
    }

    public float getTimePassed() {
        return timePassed;
    }

    public float getMaximumTimeAllowed() {
        return maximumTimeAllowed;
    }

    public float getGameScore() {
        return gameScore;
    }

    public String getTiledMapPath() {
        return tiledMapPath;
    }


    public static class GameLevelDtoBuilder {

        private Array<WavesDto> waves= new Array<>();

        private Player player;
        private boolean isTimeLimited;
        private Float timePassed, maximumTimeAllowed, gameScore;
        private String tiledMapPath;

        public GameLevelDtoBuilder(){
        }

        public GameLevelDto build(){
            this.player= player==null? new Player(): player;
            this.timePassed= (timePassed==null)? 0f: timePassed;
            this.gameScore=gameScore==null? 0f: gameScore;
            this.tiledMapPath= tiledMapPath!=null? tiledMapPath: "map1";
            if(isTimeLimited){this.maximumTimeAllowed= maximumTimeAllowed==null? 0f: maximumTimeAllowed;}
            return new GameLevelDto(player, waves, isTimeLimited, timePassed, maximumTimeAllowed, gameScore);
        }

        public GameLevelDtoBuilder setPlayer(Player player) {
            this.player = player;
            return this;
        }

        public GameLevelDtoBuilder setWaves(Array<WavesDto> waves) {
            this.waves = waves;
            return this;
        }

        public GameLevelDtoBuilder setIsTimeLimited(boolean isTimeLimited) {
            this.isTimeLimited = isTimeLimited;
            return this;
        }

        public GameLevelDtoBuilder setTimePassed(float timePassed) {
            this.timePassed = timePassed;
            return this;
        }

        public GameLevelDtoBuilder setMaximumTimeAllowed(float maximumTimeAllowed) {
            this.maximumTimeAllowed = maximumTimeAllowed;
            return this;
        }

        public GameLevelDtoBuilder setGameScore(float gameScore) {
            this.gameScore = gameScore;
            return this;
        }

        public GameLevelDtoBuilder setTiledMapPath(String tiledMapPath) {
            this.tiledMapPath = tiledMapPath;
            return this;
        }

    }


}

