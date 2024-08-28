package com.mygdx.fallingblocks.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.fallingblocks.GameStateVariables;
import com.mygdx.fallingblocks.GlobalStaticVariables;

import static com.mygdx.fallingblocks.GlobalStaticVariables.PPM;

public class NewHud implements Disposable {

    private final Stage stage;
    private final GameStateVariables gameStateVariables;

    private final BitmapFont bitmapFont;
    public NewHud(GameStateVariables gameStateVariables, SpriteBatch spriteBatch) {
        this.gameStateVariables = gameStateVariables;
        float width = GlobalStaticVariables.VIRTUAL_WIDTH / PPM;  // Swapped width and height
        float height = GlobalStaticVariables.VIRTUAL_HEIGHT / PPM;
        Viewport viewport = new FitViewport(width, height, new OrthographicCamera());
        stage = new Stage(viewport, spriteBatch);

         bitmapFont= new BitmapFont();

    }

    public void draw(SpriteBatch spriteBatch){
        bitmapFont.draw(spriteBatch, "Score: " + gameStateVariables.getScore(), 10, Gdx.graphics.getHeight() - 10); // Draw score at top left corner
    }

    @Override
    public void dispose() {
        stage.dispose();
    }



}
