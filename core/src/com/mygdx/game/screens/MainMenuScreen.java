/**
 * Created by wpower on 4/23/15.
 */
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

public class MainMenuScreen implements Screen {

    final GunslingerGameClass gungame;

    private Stage stage = new Stage();
    private Table table = new Table();
    private TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("skins/mainmenuPack.pack"));
    private Skin skin = new Skin(Gdx.files.internal("skins/mainmenuSkin.json"), atlas);

    private TextButton buttonRun  = new TextButton("Run", skin);
    private TextButton buttonArm  = new TextButton("Armory", skin);
    private TextButton buttonStat = new TextButton("Stats", skin);
    private TextButton buttonExit = new TextButton("Exit", skin);
    private Label title = new Label("Gunslinger", skin);

    private Image background = new Image( skin, "mainmenu");

    public MainMenuScreen(final GunslingerGameClass gun) {
        gungame = gun;
    }

    @Override
    public void show() {
        //First the callbacks for the buttons
        buttonRun.addListener( new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game)Gdx.app.getApplicationListener()).setScreen(new StartRunScreen(gungame));
            }
        });

        buttonArm.addListener( new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game)Gdx.app.getApplicationListener()).setScreen(new ArmoryScreen(gungame));
            }
        });

        buttonStat.addListener( new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game)Gdx.app.getApplicationListener()).setScreen(new StatScreen(gungame));
            }
        });

        buttonExit.addListener( new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        //Then we add the actors to the stage (buttons in a table)
        background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.addActor(background);

        table.add(title).padBottom(100).row();
        table.add(buttonRun).size(250, 90).padBottom(20).row();
        table.add(buttonArm).size(250, 90).padBottom(20).row();
        table.add(buttonStat).size(250, 90).padBottom(20).row();
        table.add(buttonExit).size(250, 90).padBottom(20).row();

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
