package com.mygdx.game.map;

import java.util.ArrayList;

/**
 * Holds the Integers to choose which TILE-LAYER(S) to be rendered
 */
public class TileGroup {

    private final String primaryColorName;
    private final ArrayList<Integer> upperTiles;
    private final ArrayList<Integer> lowerTiles;

    public TileGroup(String primaryColorName){
        this.primaryColorName=primaryColorName;
        upperTiles=new ArrayList<>();
        lowerTiles=new ArrayList<>();
    }

    /**
     * Add given integer to the upperTile Array which can be accessed later
     * @param i integer to be added to lowerTiles Array
     */
    public void addToUpperTile(int i){
        upperTiles.add(i);
    }

    /**
     * Add given integer to the lowerTile Array which can be accessed later
     * @param i integer to be added to lowerTiles Array
     */
    public void addToLowerTiles(int i){
        lowerTiles.add(i);
    }

    public String getPrimaryColorName(){
        return primaryColorName;
    }

    public ArrayList<Integer> getUpperTile() {
        return upperTiles;
    }

    public ArrayList<Integer> getLowerTile(){
        return lowerTiles;
    }
}
