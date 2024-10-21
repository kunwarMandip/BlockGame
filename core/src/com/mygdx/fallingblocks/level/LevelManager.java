package com.mygdx.fallingblocks.level;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class LevelManager {

    private static final String LEVELS_WAVES_JSON ="levels/waves.json";
    private static final String LEVELS_PLAYERS_JSON ="levels/players.json";

    JsonFactory factory = new JsonFactory();
    ObjectMapper objectMapper = new ObjectMapper();

    String levelToSearch;
    public LevelManager(String levelToSearch){
        this.levelToSearch=levelToSearch;
    }

    private JsonNode loadNodes(String levelToSearch, String nodeName){
        try (JsonParser parser = factory.createParser(new File(LEVELS_WAVES_JSON))) {
            while (parser.nextToken() != JsonToken.END_OBJECT) {
                if (parser.getCurrentName() != null && parser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    if (parser.getCurrentName().equals(levelToSearch)) {
                        JsonNode levelNode= objectMapper.readTree(parser);
                        return levelNode.get(nodeName);
                    }
                }
            }
        }catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }


    private void loadWaves(){
        try (JsonParser parser = factory.createParser(new File(LEVELS_WAVES_JSON))) {
            while (parser.nextToken() != JsonToken.END_OBJECT) {
                if (parser.getCurrentName() != null && parser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    if (parser.getCurrentName().equals(levelToSearch)) {

                        JsonNode levelNode = objectMapper.readTree(parser);
                        JsonNode wavesNode = levelNode.get("waves");

                        for(int i=0; i <wavesNode.size(); i++){

                        }
                    }
                }
            }
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void test(String level){
        try {
            // Create JsonParser, ObjectMapper instances, Parser to read the file
            JsonFactory factory = new JsonFactory();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonParser parser = factory.createParser(new File("game_data.json"));

            // Loop through the JSON tokens until we find the start of the top-level level "1"
            boolean foundLevel = false;
            while (parser.nextToken() != JsonToken.END_OBJECT) {
                // Check if this is the top-level field (not nested) for level "1"
                if (parser.getCurrentName() != null && parser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    if (parser.getCurrentName().equals(level)) {
                        // Now we know we're at the top level and at level "1"
                        parser.nextToken(); // Move to the start of level "1" data

                        JsonNode levelNode = objectMapper.readTree(parser);
                        JsonNode wavesNode = levelNode.get("waves");

                        // Iterate through the waves and enemies
                        System.out.println("Waves:");
                        for (int i = 0; i < wavesNode.size(); i++) {
                            System.out.println("Wave " + (i + 1) + ":");
                            JsonNode wave = wavesNode.get(i);

                            for (int j = 0; j < wave.size(); j++) {
                                JsonNode enemy = wave.get(j);
                                System.out.println("Enemy Type: " + enemy.get("enemyType").asText());
                                System.out.println("Spawn Position: " + enemy.get("spawnPosition"));
                                System.out.println("Spawn Time: " + enemy.get("spawnTime").asDouble());
                            }
                        }

                        foundLevel = true;
                        break;  // Exit loop after processing level "1"
                    }
                }
            }

            if (!foundLevel) {
                System.out.println("Level '1' not found.");
            }

            parser.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
