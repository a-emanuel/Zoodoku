package com.zoodoku.problem;

import com.zoodoku.components.Grid;
import java.util.Random;


public class ZoodokuGame {

    private Grid solution;
    private Grid initialState;
    private Grid currentState;

    public ZoodokuGame() {
        newGame();
    }

    //Methods to generate a new game
    public void newGame() {
        generateSolvedGrid();
        generatePuzzle();
        currentState = new Grid(initialState);
    }

    private void generateSolvedGrid() {
        solution = new Grid();
        Random rand = new Random();

        //Generate the first row by giving each cell a different value
        for (int i = 0; i < Grid.GRID_SIZE; i++) {
            while (true) {
                int randValue = rand.nextInt(9) + 1;
                boolean valueExists = false;
                for (int j = 0; j < i; j++) {
                    if (randValue == solution.getGrid()[0][j].getValue())
                        valueExists = true;
                }
                if (!valueExists) {
                    solution.getGrid()[0][i].setValue(randValue);
                    break;
                }
            }
        }

        //Generate the next rows by shifting the previous row
        for (int i = 1; i < Grid.GRID_SIZE; i++) {
            int shift = 3;
            if (i == 3 || i == 6)
                shift = 1;

            for (int j = 0; j < Grid.GRID_SIZE; j++) {
                int lastRow = i-1;
                int shiftedColumn = (j + shift) % 9;
                int value = solution.getGrid()[lastRow][shiftedColumn].getValue();
                solution.getGrid()[i][j].setValue(value);
            }
        }
    }

    private void generatePuzzle() {
        //We get a solved sudoku and take out some cells to form a puzzle
        initialState = new Grid(solution);
        Random rand = new Random();
        for (int i = 0; i < Grid.GRID_SIZE; i++) {
            for (int j = 0; j < Grid.GRID_SIZE; j++) {
                int randValue = rand.nextInt(2);
                if (randValue == 0 ) {
                    initialState.getGrid()[i][j].setValue(0);
                } else {
                    initialState.getGrid()[i][j].isHint(true);
                    solution.getGrid()[i][j].isHint(true);
                }
            }
        }
    }

    //Getters and setters
    public Grid getCurrentState() {
        return currentState;
    }

    public void setCurrentState(Grid grid) {
        this.currentState = grid;
    }

    public Grid getSolution() {
        return solution;
    }

    public Grid getInitialState() {
        return initialState;
    }
}
