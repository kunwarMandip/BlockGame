package com.mygdx.game.map;

import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.map.objects.TileGroup;

/**
 * Manages TileGroup Array to allow the upper and lower tiles
 * to be set more easily in the MapManager. Class
 */
public class TileLayersManager {

    private final MapLayers mapLayers;
    private final Array<TileGroup> tileGroups;

    private String currentPrimaryColorsName;
    private final Array<Integer> currentUpperTiles;
    private final Array<Integer> currentLowerTiles;


    public TileLayersManager(TiledMap tiledMap){
        System.out.println("New TileLayerManager");

        this.mapLayers=tiledMap.getLayers();
        currentUpperTiles= new Array<>();
        currentLowerTiles=new Array<>();
        tileGroups= new Array<>();
        redGreen();
        purpleTeal();
    }


    /**
     * Sets new Lower and upper tiles to be accessed given the Color
     * @param color the COLOR-GROUP we are looking for in TileGroups
     */
    public void setNewTiles(String color){
        for (TileGroup tileGroup: tileGroups){
            if(tileGroup.getPrimaryColorName().equals(color)){
                System.out.println("New Color Matched");
                currentUpperTiles.clear();
                currentLowerTiles.clear();

                for(int i: tileGroup.getUpperTile()){
                    currentUpperTiles.add(i);
                }
                for(int i: tileGroup.getLowerTile()){
                    currentLowerTiles.add(i);
                }

                this.currentPrimaryColorsName =tileGroup.getPrimaryColorName();
                return;
            }
        }
    }

    /**
     * Setting RED and GREEN color
     */
    private void redGreen(){
        System.out.println("Setting RedGreen");
        TileGroup tileGroup= new TileGroup("GreenRed");
        tileGroup.addToUpperTile(mapLayers.getIndex("YellowOutside"));
        tileGroup.addToLowerTiles(mapLayers.getIndex("BlueInside"));
        System.out.println("Tiles Index: "+ mapLayers.getIndex("YellowOutside")+ mapLayers.getIndex("BlueInside"));
        tileGroups.add(tileGroup);
    }

    /**
     * Setting PURPLE and TEAL color
     */
    private void purpleTeal(){
        System.out.println("Setting RedGreen");
        TileGroup tileGroup= new TileGroup("GreenCyan");
        tileGroup.addToUpperTile(mapLayers.getIndex("RedOutside"));
        tileGroup.addToLowerTiles(mapLayers.getIndex("CyanInside"));
        System.out.println("Tiles Index: "+ mapLayers.getIndex("GreenOutside")+ mapLayers.getIndex("CyanInside"));
        tileGroups.add(tileGroup);
    }


    public int[] getCurrentUpperTile() {
        int[] lowerTiles= new int[currentUpperTiles.size];
        for(int i=0; i<currentUpperTiles.size; i++){
            lowerTiles[i]=currentUpperTiles.get(i);
        }
        System.out.println("Getting New UPPER Tiles");
        return lowerTiles;
    }

    public int[] getCurrentLowerTiles() {
        int[] lowerTiles= new int[currentLowerTiles.size];
        for(int i=0; i<currentLowerTiles.size; i++){
            lowerTiles[i]=currentLowerTiles.get(i);
        }
        System.out.println("Getting New LOWER Tiles");
        return lowerTiles;
    }


    public String getCurrentPrimaryColorsName(){
        return currentPrimaryColorsName;
    }
    public Array<TileGroup> getTileGroups() {
        return tileGroups;
    }
}
