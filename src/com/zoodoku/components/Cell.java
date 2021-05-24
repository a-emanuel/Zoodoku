package com.zoodoku.components;

import javafx.scene.shape.Rectangle;

public class Cell {
    //Each cell has a value between 0-9 (0 means incomplete) and some cells can be part from the solution (isHint = true)
    private int value;
    private boolean isHint;
    private Rectangle ui = new Rectangle();

    //Constructors
    public Cell(int value, boolean isHint) {
        this.value = value;
        this.isHint = isHint;
    }

    public Cell(int value, boolean isHint, Rectangle ui) {
        this.value = value;
        this.isHint = isHint;
        this.ui = ui;
    }

    //Getters and setters
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void incrValue() {
        value = (value +1) % 10;
    }

    public boolean isHint() {
        return isHint;
    }

    public void isHint(boolean hint) {
        isHint = hint;
    }

    public Rectangle getUi() {
        return ui;
    }

    public void setUi(Rectangle ui) {
        this.ui = ui;
    }
}

