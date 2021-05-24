package com.zoodoku.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Grid {
    //The grid holds the value of each cell at a given moment
    public static final int GRID_SIZE = 9;
    private final Cell[][] grid = new Cell[GRID_SIZE][GRID_SIZE];
    private final HashMap<Integer, ArrayList<Cell>> subgrid = new HashMap<>();

    public Grid() {
        for (int i = 0; i < GRID_SIZE; i++) {
            subgrid.put(i, new ArrayList<>());
        }

        int shift = 0;
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                Cell cell = new Cell(0,false);
                grid[i][j] = cell;

                if (i >= 3)
                    shift = 6;
                if (i >= 6)
                    shift = 12;
                int index = i/3 + (j+shift)/3;
                subgrid.get(index).add(cell);
            }
        }
    }

    //Copy constructor
    public Grid(Grid that) {
        //Copy grid
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                this.grid[i][j] = new Cell(that.grid[i][j].getValue(), that.grid[i][j].isHint(), that.grid[i][j].getUi());
            }
        }

        //Copy subgrid
        for (Map.Entry<Integer, ArrayList<Cell>> entry : that.subgrid.entrySet()) {
            subgrid.put(entry.getKey(), new ArrayList<>(entry.getValue()));
        }
    }

    //Getters
    public Cell[][] getGrid() {
        return grid;
    }

    public HashMap<Integer, ArrayList<Cell>> getSubgrid() {
        return subgrid;
    }
}