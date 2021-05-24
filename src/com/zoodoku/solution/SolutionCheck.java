package com.zoodoku.solution;

import com.zoodoku.components.Cell;
import com.zoodoku.components.Grid;

import java.util.ArrayList;

public class SolutionCheck {

    public GameState checkSolution(Grid grid) {
        //The solution is correct if it passes all tests
        if (!checkEmptyCells(grid))
            return GameState.INCOMPLETE;

        if (checkRows(grid) && checkColumns(grid) && checkSubgrid(grid))
            return GameState.SOLVED;

        return GameState.WRONG;
    }

    private boolean checkEmptyCells(Grid grid) {
        //Check for empty cells (value = 0)
        for (var cells : grid.getGrid())
            for (var cell : cells)
                if (cell.getValue() == 0)
                    return false;
        return true;
    }

    private boolean checkRows(Grid grid) {
        //Check each row for duplicate values
        for (int i = 0; i < Grid.GRID_SIZE; i++) {
            for (int j = 0; j < Grid.GRID_SIZE - 1; j++) {
                for (int k = j+1; k < Grid.GRID_SIZE; k++) {
                    if (grid.getGrid()[i][j].getValue() == grid.getGrid()[i][k].getValue())
                        return false;
                }
            }
        }
        return true;
    }

    private boolean checkColumns(Grid grid) {
        //Check each column for duplicate values
        for (int i = 0; i < Grid.GRID_SIZE; i++) {
            for (int j = 0; j < Grid.GRID_SIZE - 1; j++) {
                for (int k = j+1; k < Grid.GRID_SIZE; k++) {
                    if (grid.getGrid()[j][i].getValue() == grid.getGrid()[k][i].getValue())
                        return false;
                }
            }
        }
        return true;
    }

    private boolean checkSubgrid(Grid grid) {
        //Check each 3x3 square for duplicate values
        for (int i = 0; i < Grid.GRID_SIZE; i++) {
            ArrayList<Cell> cells = grid.getSubgrid().get(i);
            for (int j = 0; j < Grid.GRID_SIZE - 1; j++) {
                for (int k = j+1; k < Grid.GRID_SIZE; k++) {
                    if (cells.get(j).getValue() == cells.get(k).getValue())
                        return false;
                }
            }
        }
        return true;
    }
}