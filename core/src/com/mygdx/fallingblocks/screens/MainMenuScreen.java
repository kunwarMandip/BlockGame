package com.mygdx.fallingblocks.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.fallingblocks.FallingBlocks;
import com.mygdx.fallingblocks.GlobalStaticVariables;

public class MainMenuScreen implements Screen {

    private final FallingBlocks fallingBlocks;

    private Stage stage;
    private Viewport viewport;

    private Skin skin;

    public MainMenuScreen(FallingBlocks fallingBlocks){
        this.fallingBlocks=fallingBlocks;
    }

    @Override
    public void show() {
        setViewport();
        setStage();
        setSkin();
        setLayout();
    }

    private void setViewport(){
        float width = GlobalStaticVariables.VIRTUAL_WIDTH;
        float height = GlobalStaticVariables.VIRTUAL_HEIGHT;
        this.viewport = new FitViewport(width, height, new OrthographicCamera());
    }

    private void setStage(){
        SpriteBatch spriteBatch = new SpriteBatch();
        this.stage = new Stage(viewport, spriteBatch);
        Gdx.input.setInputProcessor(stage);
    }

    private void setSkin(){
        this.skin = new Skin();
        skin.addRegions(new TextureAtlas(Gdx.files.internal("uiskin.atlas")));
        skin.load(Gdx.files.internal("uiskin.json"));
    }

    private void setLayout(){
        TextButton start = new TextButton("Start Game", skin);
        TextButton leave = new TextButton("LeaderBoards", skin);

        start.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                fallingBlocks.setScreen(new GameScreen(fallingBlocks));
            }
        });


        Table table= new Table();
        table.setFillParent(true);
        table.align(Align.center);
        table.setDebug(true);

        table.add(start).width(500).height(200).fill().padBottom(50).row();
        table.add(leave).width(500).height(200).fill().row();
        stage.addActor(table);
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
        stage.getViewport().update(width, height, true);
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
