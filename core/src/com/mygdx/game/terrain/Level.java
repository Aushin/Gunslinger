package com.mygdx.game.terrain;


import com.badlogic.gdx.graphics.g3d.Model;
import java.util.List;

/**
 * Created by wpower on 4/25/15.
 */
public class Level {

    private int WIDTH;
    private int LENGTH;

    private Model level;
    private int[][] grid;


    private Level( int w, int l ) {
        WIDTH = w;
        LENGTH = l;

        level = new Model();
        grid = new int[WIDTH][LENGTH];

    }

    public void addCell( int x, int y, int z, String top, List<String> left, List<String> right ){

        //x, y are the position in terms of grid cells.
        //z is the z-height in terms of z-increment, which is a 1/4 of a grid side length.
        //top is the name of the top texture in the tecture atlas
        //left and right are lists of names of textures to be used in the material on those sides?

    }
}
