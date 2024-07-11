package com.mygdx.game.map;

import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.utils.Array;

public class TileLayerManager {

    private final MapLayers mapLayers;
    private final Array<TileGroup> tileGroups;
    private final Array<Integer> currentUpperTiles;
    private final Array<Integer> currentLowerTiles;

    public TileLayerManager(TiledMap tiledMap){
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
        tileGroup.addToUpperTile(mapLayers.getIndex("redBackground"));
        tileGroup.addToLowerTiles(mapLayers.getIndex("greenTop"));
        System.out.println("Tiles Index: "+ mapLayers.getIndex("greenTop")+ mapLayers.getIndex("redBackground"));
        tileGroups.add(tileGroup);
    }

    /**
     * Setting PURPLE and TEAL color
     */
    private void purpleTeal(){
        System.out.println("Setting PurpleTeal");
//        TileGroup tileGroup= new TileGroup("blueTeal");
//        tileGroup.addToUpperTile(mapLayers.getIndex("tealTop"));
//        tileGroup.addToLowerTiles(mapLayers.getIndex("purpleBackground"));
//        tileGroups.add(tileGroup);
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

    public Array<TileGroup> getTileGroups() {
        return tileGroups;
    }
}
