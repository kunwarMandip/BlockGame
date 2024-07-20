//package com.mygdx.game.screens;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Screen;
//import com.badlogic.gdx.graphics.GL20;
//import com.badlogic.gdx.graphics.OrthographicCamera;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.scenes.scene2d.Stage;
//import com.badlogic.gdx.scenes.scene2d.ui.Button;
//import com.badlogic.gdx.utils.viewport.FitViewport;
//import com.badlogic.gdx.utils.viewport.Viewport;
//import com.mygdx.game.GlobalVariables;
//
//
//public class StartScreen implements Screen {
//
//    private final Stage stage;
//    private final Viewport viewport;
//
//    private Button startButton, closeButton;
//
//    public StartScreen(SpriteBatch spriteBatch){
//        viewport = new FitViewport(GlobalVariables.VIRTUAL_WIDTH / GlobalVariables.PPM,
//                            GlobalVariables.VIRTUAL_HEIGHT / GlobalVariables.PPM,
//                                 new OrthographicCamera());
//        stage= new Stage(viewport, spriteBatch);
//    }
//
//    private void setStartScreenActors(){
//
//        Texture buttonTextureUp = new Texture(Gdx.files.internal("characters/red.png"));
//        Texture buttonTextureDown = new Texture(Gdx.files.internal("characters/yellow.png"));
//
//        TextureRegionDrawable upDrawable = new TextureRegionDrawable(new TextureRegion(buttonTextureUp));
//        TextureRegionDrawable downDrawable = new TextureRegionDrawable(new TextureRegion(buttonTextureDown));
//
//    }
//
//    @Override
//    public void show() {
//
//    }
//
//    private void update(float update){
//
//    }
//
//    @Override
//    public void render(float delta) {
//        update(delta);
//
//        Gdx.gl.glClearColor(0,0, 0, 1);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        stage.act(delta);
//        stage.draw();
//    }
//
//    @Override
//    public void resize(int width, int height) {
//        viewport.update(width, height);
//    }
//
//    @Override
//    public void pause() {
//
//    }
//
//    @Override
//    public void resume() {
//
//    }
//
//    @Override
//    public void hide() {
//
//    }
//
//    @Override
//    public void dispose() {
//        stage.dispose();
//    }
//}
