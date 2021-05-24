package com.zoodoku.ui;

import com.zoodoku.components.Grid;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

public class Styles {
    //UI properties

    //Size
    public static final int GAME_WIDTH = 700; //px
    public static final int GAME_HEIGHT = 700;
    public static final int GRID_SIZE = 585;
    public static final int CELL_SIZE = GRID_SIZE/ Grid.GRID_SIZE;
    public static final double GRID_PADDING = (int)((GAME_WIDTH-GRID_SIZE)/2);

    //Colors
    public static final Color TEXT_COLOR = Color.rgb(39, 39, 52);
    public static final Color BG_COLOR = Color.rgb(35, 35, 46);
    public static final Color GRID_COLOR = Color.hsb(0, 0, 0.93);

    public static final Color TEXT_INCOMPLETE = Color.hsb(32, 0.5, 1);
    public static final Color TEXT_WRONG = Color.hsb(354, 0.5, 1);
    public static final Color TEXT_SOLVED = Color.hsb(133, 0.5, 1);

    //Icons for each cell possible value (1-9)
    static ImagePattern animal1 = new ImagePattern(new Image("Resources/Bear.png"));
    static ImagePattern animal2 = new ImagePattern(new Image("Resources/Bumblebee.png"));
    static ImagePattern animal3 = new ImagePattern(new Image("Resources/Butterfly.png"));
    static ImagePattern animal4 = new ImagePattern(new Image("Resources/Clown Fish.png"));
    static ImagePattern animal5 = new ImagePattern(new Image("Resources/Crab.png"));
    static ImagePattern animal6 = new ImagePattern(new Image("Resources/Deer.png"));
    static ImagePattern animal7 = new ImagePattern(new Image("Resources/Dragonfly.png"));
    static ImagePattern animal8 = new ImagePattern(new Image("Resources/Giraffe.png"));
    static ImagePattern animal9 = new ImagePattern(new Image("Resources/Whale.png"));

    public static final ImagePattern[] ANIMAL_ICONS = {animal1, animal2, animal3, animal4, animal5, animal6, animal7, animal8, animal9};
}
