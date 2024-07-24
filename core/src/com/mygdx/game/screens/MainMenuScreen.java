package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.StaticVariables;

import static com.mygdx.game.StaticVariables.PPM;


public class MainMenuScreen implements Screen {

    private final SpriteBatch spriteBatch;
    private final Stage stage;
    private final Viewport viewport;

    private final Skin skin;
    private Button startButton, quitButton;

    public MainMenuScreen(){
        spriteBatch= new SpriteBatch();
        // Swapped width and height
        float width = StaticVariables.VIRTUAL_WIDTH / PPM;
        float height = StaticVariables.VIRTUAL_HEIGHT / PPM;
        viewport = new FitViewport(width, height, new OrthographicCamera());
        stage = new Stage(viewport, spriteBatch);
        Gdx.input.setInputProcessor(stage);

        skin = new Skin();
        skin.addRegions(new TextureAtlas(Gdx.files.internal("uiskin.atlas")));
        skin.load(Gdx.files.internal("uiskin.json"));

        // Create button
        TextButton button = new TextButton("Click me!", skin);
        button.setPosition(100/PPM, 100/PPM);
        stage.addActor(button);

        // Add a listener to the button
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Button clicked!");
            }
        });
    }


    @Override
    public void show() {

    }


    @Override
    public void render(float delta) {


        Gdx.gl.glClearColor(0,0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
