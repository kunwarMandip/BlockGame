package com.mygdx.fallingblocks.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.fallingblocks.reduction.WavesDto;

public class LevelManager {

    private static final String LEVELS_WAVES_JSON ="levels/waves.json";
    private static final String LEVELS_PLAYERS_JSON ="levels/players.json";

    public LevelManager(){}

    public void testThree(String level) {
        try {
            // Create a FileHandle to the JSON file
            FileHandle fileHandle = Gdx.files.internal("levels/waves.json");
            if (fileHandle.exists()) {
                // File exists, proceed with your logic
                System.out.println("File found: " + fileHandle.path());
            } else {
                // Handle the file not found case
                System.out.println("File not found.");
                return; // Exit the function if the file does not exist
            }

            // Use JsonReader to parse the JSON file
            JsonReader jsonReader = new JsonReader();
            JsonValue root = jsonReader.parse(fileHandle);

            // Look for the specified level in the JSON
            JsonValue levelNode = root.get(level);
            if (levelNode == null) {
                System.out.println("Level '" + level + "' not found.");
                return; // Exit if the specified level is not found
            }

            // Access the "waves" node
            JsonValue wavesNode = levelNode.get("waves");
            if (wavesNode == null) {
                System.out.println("No waves found for level '" + level + "'.");
                return; // Exit if no waves are found for the level
            }

            // Iterate through the waves and enemies
            System.out.println("WavesDto:");
            for (JsonValue wave : wavesNode) {
                System.out.println("new");
                int waveNumber = wave.getInt("waveNumber", -1);
                float startDelay = wave.getFloat("startDelay", 0.0f);
                float duration = wave.getFloat("duration", 0.0f);
                String spawnPattern = wave.getString("spawnPattern", "Unknown");

                System.out.println("Wave " + waveNumber + ":");
                System.out.println("Start Delay: " + startDelay);
                System.out.println("Duration: " + duration);
                System.out.println("Spawn Pattern: " + spawnPattern);

                JsonValue enemiesNode = wave.get("enemies");
                if (enemiesNode != null) {
                    for (JsonValue enemy : enemiesNode) {
                        String enemyType = enemy.getString("enemyType", "Unknown");
                        int amount = enemy.getInt("amount", 0);
                        float spawnInterval = enemy.getFloat("spawnInterval", 0.0f);
                        String behavior = enemy.getString("behavior", "Unknown");
                        int health = enemy.getInt("health", 0);
                        float speed = enemy.getFloat("speed", 0.0f);
                        int attackPower = enemy.getInt("attackPower", 0);
                        int rewardPoints = enemy.getInt("rewardPoints", 0);

                        System.out.println("Enemy Type: " + enemyType);
                        System.out.println("Amount: " + amount);
                        System.out.println("Spawn Interval: " + spawnInterval);
                        System.out.println("Behavior: " + behavior);
                        System.out.println("Health: " + health);
                        System.out.println("Speed: " + speed);
                        System.out.println("Attack Power: " + attackPower);
                        System.out.println("Reward Points: " + rewardPoints);
                    }
                } else {
                    System.out.println("No enemies found for this wave.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param filePath
     * @return
     */
    public boolean isFileExist(String filePath){
        FileHandle fileHandle = Gdx.files.internal(filePath);

        if (!fileHandle.exists()) {
            System.out.println("File not found.");
            return false;
        }

        System.out.println("File found: " + fileHandle.path());
        return true;
    }

    /**
     *
     * @param fileHandle
     * @param level
     * @param node
     * @return
     */
    public JsonValue getJsonValue(FileHandle fileHandle, String level, String node){
        JsonReader jsonReader = new JsonReader();
        JsonValue root = jsonReader.parse(fileHandle);

        // Look for the specified level in the JSON
        JsonValue levelNode = root.get(level);
        if (levelNode == null) {
            System.out.println("Level '" + level + "' not found.");
            return null;

        }

        return levelNode.get(node);
    }

    public WavesDto getWavesAsDto(){
        return null;
    }

}
