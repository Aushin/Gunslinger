package com.mygdx.game.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Plane;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;

/**
 * Created by wpower on 4/25/15.
 */
public class BattleStage extends Stage {

    private Camera cam;
    Texture deserttexture;
    Texture grasstexture;
    SpriteBatch batch = new SpriteBatch();
    final Sprite[][] sprites = new Sprite[10][10];
    final Matrix4 matrix = new Matrix4();

    public BattleStage(){
        super();
        cam = new OrthographicCamera(10, 10 * (Gdx.graphics.getHeight() / (float)Gdx.graphics.getWidth()));
        cam.position.set(5, 4, 10);
        cam.direction.set(-2, -1, -2);
        cam.near = 1;
        cam.far = 100;
        this.setViewport(new ScalingViewport(Scaling.stretch, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), cam) );

        //To rotate things in draw
        matrix.setToRotation(new Vector3(1, 0, 0), 90);

        //Creating sprites
        deserttexture = new Texture(Gdx.files.internal("raw/unpacked/desert.jpg"));
        grasstexture = new Texture(Gdx.files.internal("raw/unpacked/grass.jpg"));
        for(int z = 0; z < 10; z++) {
            for(int x = 0; x < 10; x++) {
                if( MathUtils.random() > (float)0.25 ){
                    sprites[x][z] = new Sprite(deserttexture);
                } else {
                    sprites[x][z] = new Sprite(grasstexture);
                }
                sprites[x][z].setPosition(x,z);
                sprites[x][z].setSize(1, 1);
            }
        }

    }

    final Plane xzPlane = new Plane(new Vector3(0, 1, 0), 0);
    final Vector3 intersection = new Vector3();
    Sprite lastSelectedTile = null;

    final Vector3 curr = new Vector3();
    final Vector3 last = new Vector3(-1, -1, -1);
    final Vector3 delta = new Vector3();

    private void checkTileTouched() {
        if(Gdx.input.justTouched()) {
            Ray pickRay = cam.getPickRay(Gdx.input.getX(), Gdx.input.getY());
            Intersector.intersectRayPlane(pickRay, xzPlane, intersection);
            int x = (int)intersection.x;
            int z = (int)intersection.z;
            if(x >= 0 && x < 10 && z >= 0 && z < 10) {
                if(lastSelectedTile != null) lastSelectedTile.setColor(1, 1, 1, 1);
                Sprite sprite = sprites[x][z];
                sprite.setColor(1, 0, 0, 1);
                lastSelectedTile = sprite;
            }
        }
    }

    @Override
    public boolean touchDragged (int x, int y, int pointer) {
        Ray pickRay = cam.getPickRay(x, y);
        Intersector.intersectRayPlane(pickRay, xzPlane, curr);

        if(!(last.x == -1 && last.y == -1 && last.z == -1)) {
            pickRay = cam.getPickRay(last.x, last.y);
            Intersector.intersectRayPlane(pickRay, xzPlane, delta);
            delta.sub(curr);
            cam.position.add(delta.x, delta.y, delta.z);
        }
        last.set(x, y, 0);
        return false;
    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button) {
        last.set(-1, -1, -1);
        return false;
    }

    @Override
    public void draw() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        cam.update();
        batch.setProjectionMatrix(cam.combined);
        batch.setTransformMatrix(matrix);

        batch.begin();
        for(int z = 0; z < 10; z++) {
            for(int x = 0; x < 10; x++) {
                sprites[x][z].draw(batch);
            }
        }
        batch.end();

        checkTileTouched();

        super.draw();
    }
}
