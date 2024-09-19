package com.mygdx.fallingblocks.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.fallingblocks.utilities.GameStateVariables;
import com.mygdx.fallingblocks.GlobalStaticVariables;

import static com.mygdx.fallingblocks.GlobalStaticVariables.PPM;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
public class OldHudScreen implements Disposable {

    private final Stage stage;
    private Rectangle rectangleArea;

    private final Label scoreLabel;
    private final GameStateVariables gameStateVariables;

    private BitmapFont font;

    public OldHudScreen(GameStateVariables gameStateVariables, SpriteBatch spriteBatch, TiledMap tiledMap){
        this.gameStateVariables = gameStateVariables;

        float width = GlobalStaticVariables.VIRTUAL_WIDTH / PPM;  // Swapped width and height
        float height = GlobalStaticVariables.VIRTUAL_HEIGHT / PPM;
        Viewport viewport = new FitViewport(width, height, new OrthographicCamera());
        stage = new Stage(viewport, spriteBatch);

        // Extract the rectangle area from the TiledMap
        MapLayer targetLayer = tiledMap.getLayers().get("Score");
        for (RectangleMapObject object : targetLayer.getObjects().getByType(RectangleMapObject.class)) {
            this.rectangleArea = object.getRectangle();
        }

        create();

        // Create a table and set its position and size based on the rectangle
        Table table = new Table();
        table.setFillParent(false);

        // Set position and size based on rectangle area, converted to PPM
        table.setPosition(rectangleArea.x / PPM, rectangleArea.y / PPM);
        table.setSize(rectangleArea.width / PPM, rectangleArea.height / PPM);


        Label.LabelStyle labelStyle = new Label.LabelStyle(font, Color.BLUE);
        scoreLabel = new Label(String.format("%d", 0), labelStyle);

        // Create a score label
//        scoreLabel = new Label(String.format("%d", 0), new Label.LabelStyle(new BitmapFont(), Color.BLUE));

        // Add the score label to the table
        table.add(scoreLabel).expand().center();

        // Add the table to the stage
        stage.addActor(table);
    }

    public void create(){
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/light-pixel-7/light_pixel-7.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 16; // Specify the font size
        font = generator.generateFont(parameter);
        generator.dispose();
    }

    public Stage getStage(){
        return stage;
    }

    public void update(){
        scoreLabel.setText(String.format("%d", gameStateVariables.getScore()));
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
