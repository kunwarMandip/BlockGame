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

public class LevelChooserScreen implements Screen {

    private final FallingBlocks fallingBlocks;
    private final SpriteBatch spriteBatch;

    private Viewport viewport;
    private Stage stage;
    private Skin skin;
    private Table table;

    public LevelChooserScreen(FallingBlocks fallingBlocks, SpriteBatch spriteBatch){
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

    private void setTable(){
        table= new Table();
        table.center();
        table.setFillParent(true);
        table.setDebug(true);
    }

    private void setLayout(){
        setTable();
        for(int i =1; i<20; i++){
            makeButton(i);
        }

        stage.addActor(table);
    }

    private void makeButton(int i){
        TextButton button= new TextButton("Level: "+ i, skin);
        button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                System.out.println("Button: "+ i);
            }
        });

        table.add(button).width(150).padRight(10).padBottom(20);
        if(i%2==0){
            table.row();
        }
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
        viewport.apply();
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

    }
}
