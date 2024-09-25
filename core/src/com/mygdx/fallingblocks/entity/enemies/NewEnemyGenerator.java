package com.mygdx.fallingblocks.entity.enemies;

import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector4;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.fallingblocks.map.objects.EnemySpawnArea;
import com.mygdx.fallingblocks.utilities.EntityTextureChooser;
import com.mygdx.fallingblocks.utilities.SolidTextureCreator;

public class NewEnemyGenerator {


    private final World world;
    private final SolidTextureCreator solidColorCreator;

    private final EnemySpawner enemySpawner;
    private final EntityTextureChooser enemyTextureChooser;

    public NewEnemyGenerator(World world, SolidTextureCreator solidTextureCreator){
        this.world=world;
        this.solidColorCreator=solidTextureCreator;

        this.enemySpawner= new EnemySpawner(world, new TmxMapLoader().load("map/tiledMap.tmx"));
        this.enemyTextureChooser = new EntityTextureChooser(solidColorCreator.getSize());
    }

    public void spawnEnemy(Array<Enemy> currentEnemies, int spawnCount, Vector2 playerLocation, Vector2 enemySpeed){
        for(int i =0; i<spawnCount; i++){
            int enemyColor= enemyTextureChooser.getColorNumber(SolidTextureCreator.playerColorID);
            Vector4 enemySpawnAndSpeedInfo= getEnemySpawnAndSpeedInfo(playerLocation, enemySpeed);
            currentEnemies.add(new Enemy(world, enemyColor, solidColorCreator, enemySpawnAndSpeedInfo, null));
        }
    }


    private Vector4 getEnemySpawnAndSpeedInfo(Vector2 playerLocation, Vector2 enemyCurrentSpeed){
        EnemySpawnArea spawnArea= enemySpawner.getRandomSpawnArea();
        Vector2 enemyMovementSpeed, enemySpawnLocation;

        switch(spawnArea.getSpawnDirection()){
            case "bottom":
                enemySpawnLocation= new Vector2(playerLocation.x, spawnArea.getPosition().y);
                enemyMovementSpeed= new Vector2(0, enemyCurrentSpeed.y);
                break;
            case "left":
                enemySpawnLocation= new Vector2(spawnArea.getPosition().x, playerLocation.y);
                enemyMovementSpeed= new Vector2(enemyCurrentSpeed.x, 0);
                break;
            case "right":
                enemySpawnLocation= new Vector2(spawnArea.getPosition().y, playerLocation.y);
                enemyMovementSpeed= new Vector2(-enemyCurrentSpeed.x, 0);
                break;
            case "top":
            default:
                enemySpawnLocation= new Vector2(playerLocation.x, spawnArea.getPosition().y);
                enemyMovementSpeed= new Vector2(enemyCurrentSpeed.x, -enemyCurrentSpeed.y);
                break;
        }

        return new Vector4(enemyMovementSpeed.x, enemyMovementSpeed.y, enemySpawnLocation.x, enemySpawnLocation.y);
    }

}
