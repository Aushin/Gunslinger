package com.mygdx.game.terrain;

import com.badlogic.gdx.graphics.g3d.Model;

/**
 * Created by wpower on 5/4/15.
 */
public class Column {

    private final int ZINCREMENT = 25;
    private int gridsidelength = 100;
    private int zheight;
    private int zheightPX;
    private Model model = new Model();

    //Column is a single cell in the x/y grid.

    //Stores a top face, a 'left' face and a 'right' face (from the views perspective)
    //To do this, a Model is used.  Its dimensions are determined by the z height of the top face,
    //and the bottom of the cell its in.  a nice rectangular prism.

    //Need to give it access to a tecture library.
    //Will make sure that a z=height is in an increment that we have textures for.  So we can tile
    //nicely.

    public Column( int z ){
        zheight = z;
        zheightPX = z*ZINCREMENT;

    }
}
