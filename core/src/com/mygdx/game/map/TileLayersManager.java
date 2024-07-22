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

        setTiles();
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

    private void setTiles(){
        addTileGroup("CyanLightBlue", "BlackOutside", "WhiteInside");
        addTileGroup("GreenOrange", "RedOutside", "GreyInside");
    }



    private void addTileGroup(String groupName, String outsideLayer, String insideLayer){
        TileGroup tileGroup= new TileGroup(groupName);
        tileGroup.addToUpperTile(mapLayers.getIndex(outsideLayer));
        tileGroup.addToLowerTiles(mapLayers.getIndex(insideLayer));
        tileGroups.add(tileGroup);
        System.out.println("Added Tile-Group: "+ groupName );
        System.out.println("Tiles Index: " +mapLayers.getIndex(outsideLayer) + mapLayers.getIndex(insideLayer));
    }

    /**
     * Converts Integer Array into int array
     * @return upperTiles int[] array
     */
    public int[] getCurrentUpperTile() {
        int[] lowerTiles= new int[currentUpperTiles.size];
        for(int i=0; i<currentUpperTiles.size; i++){
            lowerTiles[i]=currentUpperTiles.get(i);
        }
        System.out.println("Getting New UPPER Tiles");
        return lowerTiles;
    }

    /**
     * Converts Integer Array into int array
     * @return lowerTiles int[] array
     */
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
