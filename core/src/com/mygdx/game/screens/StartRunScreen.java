package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.GunslingerGameClass;

/**
 * Created by wpower on 4/24/15.
 */
public class StartRunScreen implements Screen {

    final GunslingerGameClass gungame;

    private Stage stage = new Stage();
    private Table table = new Table();
    private TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("skins/mainmenuPack.pack"));
    private Skin skin = new Skin(Gdx.files.internal("skins/mainmenuSkin.json"), atlas);

    private TextButton buttonBack  = new TextButton("Back", skin);
    private TextButton buttonStart = new TextButton("Start", skin);
    private Label title = new Label("Start Run Screen", skin);
    private Image background = new Image( skin, "mainmenu");

    public StartRunScreen( GunslingerGameClass gun){
        gungame = gun;
    }

    @Override
    public void show() {
        buttonBack.addListener( new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game)Gdx.app.getApplicationListener()).setScreen(new MainMenuScreen(gungame));
            }
        });

        buttonStart.addListener( new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game)Gdx.app.getApplicationListener()).setScreen(new BattleScreen(gungame));
            }
        });

        background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.addActor(background);

        table.add(title).padBottom(100).row();
        table.add(buttonBack).size(250, 90).padBottom(20).row();
        table.add(buttonStart).size(250, 90).padBottom(20).row();

        table.setFillParent(true);
        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

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
        atlas.dispose();
        skin.dispose();
    }
}
