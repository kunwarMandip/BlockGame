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
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.fallingblocks.FallingBlocks;

import static com.mygdx.fallingblocks.GlobalStaticVariables.VIRTUAL_HEIGHT;
import static com.mygdx.fallingblocks.GlobalStaticVariables.VIRTUAL_WIDTH;


public class MainMenuScreen implements Screen {

    private final FallingBlocks fallingBlocks;
    private final SpriteBatch spriteBatch;

    private Viewport viewport;
    private Stage stage;
    private Skin skin;
    private Table table;

    private TextButton startButton;
    private TextButton leaderBoardButton;

    public MainMenuScreen(FallingBlocks fallingBlocks, SpriteBatch spriteBatch){
        this.fallingBlocks=fallingBlocks;
        this.spriteBatch=spriteBatch;
    }

    @Override
    public void show() {
        OrthographicCamera orthographicCamera = new OrthographicCamera();
        this.viewport= new FitViewport(VIRTUAL_WIDTH/2f, VIRTUAL_HEIGHT/2f, orthographicCamera);
        this.stage= new Stage(viewport, spriteBatch);
        this.fallingBlocks.getInputListenersManager().addInputListener(stage);

        setSkin();
        setLayout();
    }

    private void setSkin(){
        skin = new Skin();
        skin.addRegions(new TextureAtlas(Gdx.files.internal("flat-earth/skin/flat-earth-ui.atlas")));
        skin.load(Gdx.files.internal("flat-earth/skin/flat-earth-ui.json"));
    }

    private void setLayout(){
        setTable();
        setStartButton();
        setLeaderBoardButton();

        table.add(startButton).width(200).padBottom(20);
        table.row();
        table.add(leaderBoardButton).width(200);
        stage.addActor(table);
    }

    private void setTable(){
        table= new Table();
        table.setFillParent(true);
        table.setDebug(true);
    }

    private void setStartButton(){
        startButton= new TextButton("Start Game", skin);
        startButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                dispose();
                fallingBlocks.setScreen(fallingBlocks.getGameScreen());
            }
        });
    }

    private void setLeaderBoardButton(){
        leaderBoardButton= new TextButton("LeaderBoards", skin);
        leaderBoardButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                System.out.println("Functionality Not available yet");
            }
        });
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
        viewport.update(width, height, true);
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
        fallingBlocks.getInputListenersManager().removeInputProcessor(stage);
        stage.dispose();
        skin.dispose();
    }
}
