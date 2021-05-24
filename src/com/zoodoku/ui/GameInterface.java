package com.zoodoku.ui;

import com.zoodoku.components.Cell;
import com.zoodoku.components.Grid;
import com.zoodoku.problem.ZoodokuGame;
import com.zoodoku.solution.GameState;
import com.zoodoku.solution.SolutionCheck;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;


public class GameInterface {
    //The board holds the grid background, grid lines and the cells, while the root contains all elements
    private final Stage stage;
    private final Group board;
    private final Group root;
    private final ZoodokuGame game;

    public GameInterface(Stage stage, ZoodokuGame game) {
        this.stage = stage;
        this.root = new Group();
        this.board = new Group();
        root.getChildren().add(board);
        this.game = game;
    }

    public void InitializeInterface() {
        drawBackground();
        drawGridBackground();
        drawCells();
        drawGridLines();
        drawButtons();

        stage.setTitle("Zoodoku");
        stage.getIcons().add(new Image("Resources/icon.png"));
        stage.setResizable(false);
        stage.show();
    }

    private void drawBackground() {
        Scene scene = new Scene(root, Styles.GAME_WIDTH, Styles.GAME_HEIGHT);
        scene.setFill(Styles.BG_COLOR);
        stage.setScene(scene);
    }

    private void drawGridBackground() {
        Rectangle grid = new Rectangle();
        grid.setX(Styles.GRID_PADDING);
        grid.setY(Styles.GRID_PADDING);
        grid.setWidth(Styles.GRID_SIZE);
        grid.setHeight(Styles.GRID_SIZE);
        grid.setFill(Styles.GRID_COLOR);
        this.board.getChildren().add(grid);
    }

    private void drawGridLines() {

        for (int i = 1; i < 9; i++) {

            double lineThickness = 1.5;
            if (i == 3 || i == 6)
                lineThickness = 4;

            //Draw the horizontal lines
            Line horizontalLine = new Line();
            horizontalLine.setStartX(Styles.GRID_PADDING);
            horizontalLine.setEndX(Styles.GRID_PADDING + Styles.GRID_SIZE);
            horizontalLine.setStartY(Styles.GRID_PADDING + Styles.CELL_SIZE * i);
            horizontalLine.setEndY(Styles.GRID_PADDING + Styles.CELL_SIZE * i);
            horizontalLine.setStrokeWidth(lineThickness);
            horizontalLine.setStroke(Styles.TEXT_COLOR);
            horizontalLine.setStrokeType(StrokeType.CENTERED);

            //Draw the vertical lines
            Line verticalLine = new Line();
            verticalLine.setStartY(Styles.GRID_PADDING);
            verticalLine.setEndY(Styles.GRID_PADDING + Styles.GRID_SIZE);
            verticalLine.setStartX(Styles.GRID_PADDING + Styles.CELL_SIZE * i);
            verticalLine.setEndX(Styles.GRID_PADDING + Styles.CELL_SIZE * i);
            verticalLine.setStrokeWidth(lineThickness);
            verticalLine.setStroke(Styles.TEXT_COLOR);
            verticalLine.setStrokeType(StrokeType.CENTERED);

            board.getChildren().addAll(horizontalLine, verticalLine);
        }
    }

    private void drawCells() {
        //Each cell is represented by a rectangle
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Cell cell = game.getCurrentState().getGrid()[i][j];

                Rectangle cellUi = new Rectangle();
                cellUi.setWidth(Styles.CELL_SIZE);
                cellUi.setHeight(Styles.CELL_SIZE);
                cellUi.setX(Styles.GRID_PADDING + Styles.CELL_SIZE * j);
                cellUi.setY(Styles.GRID_PADDING + Styles.CELL_SIZE * i);

                //If the cell is part of the solution we can't change its value
                if (!cell.isHint()) {
                    //Increment cell value when cell is pressed
                    cellUi.setOnMouseClicked(mouseEvent -> {
                        cell.incrValue();
                        refreshInterface();
                    });

                    //Set the cell icon based on cell value
                    if (cell.getValue() == 0) {
                        cellUi.setFill(Color.hsb(0,0,0,0));
                    } else {
                        cellUi.setFill(Styles.ANIMAL_ICONS[cell.getValue() - 1]);
                    }
                } else {
                    cellUi.setFill(Styles.ANIMAL_ICONS[cell.getValue() - 1]);
                }

                cell.setUi(cellUi);
                board.getChildren().add(cellUi);
            }
        }
    }

    private void drawButtons() {
        Button checkBtn = new Button("Check");
        checkBtn.setOnAction(actionEvent -> checkSolution());

        Button resetBtn = new Button("Reset");
        resetBtn.setOnAction(actionEvent -> resetGame());

        Button showSolutionBtn = new Button("Show Solution");
        showSolutionBtn.setOnAction(actionEvent -> showSolution());

        Button newGameBtn = new Button("New Game");
        newGameBtn.setOnAction(actionEvent -> newGame());

        FlowPane pane = new FlowPane();
        pane.getChildren().addAll(checkBtn, resetBtn, showSolutionBtn, newGameBtn);
        this.root.getChildren().add(pane);
    }

    private void refreshInterface() {
        //Update the grid
        board.getChildren().clear();
        drawGridBackground();
        drawCells();
        drawGridLines();
    }

    private void checkSolution() {
        //Check if the current grid state is a valid solution
        SolutionCheck solutionCheck = new SolutionCheck();
        GameState gameState = solutionCheck.checkSolution(game.getCurrentState());

        if (gameState == GameState.INCOMPLETE) {
            statusPopup(GameState.INCOMPLETE);
        }

        if (gameState == GameState.WRONG) {
            statusPopup(GameState.WRONG);
        }

        if (gameState == GameState.SOLVED) {
            statusPopup(GameState.SOLVED);
        }
    }

    private void statusPopup(GameState state) {
        //Show a custom pop-up based on grid status
        double width = Styles.GRID_SIZE - 50;
        double height = Styles.GRID_SIZE - 450;

        final Popup popup = new Popup();
        popup.setWidth(width);
        popup.setHeight(height);

        Rectangle window = new Rectangle();
        window.setWidth(width);
        window.setHeight(height);
        window.setFill(Styles.BG_COLOR);
        window.setOnMouseClicked(mouseEvent -> popup.hide());

        Text message = new Text();
        message.setFont(Font.font("Ariel", 45));

        if (state == GameState.INCOMPLETE) {
            message.setText("INCOMPLETE");
            message.setFill(Styles.TEXT_INCOMPLETE);
        }

        if (state == GameState.WRONG) {
            message.setText("WRONG");
            message.setFill(Styles.TEXT_WRONG);
        }

        if (state == GameState.SOLVED) {
            message.setText("CONGRATULATIONS");
            message.setFill(Styles.TEXT_SOLVED);
        }

        StackPane pane = new StackPane();
        pane.setAlignment(Pos.CENTER);
        pane.getChildren().addAll(window, message);
        StackPane.setAlignment(message, Pos.CENTER);

        popup.getContent().add(pane);
        popup.show(stage);
    }

    private void showSolution() {
        //View a possible solution
        game.setCurrentState(new Grid(game.getSolution()));
        refreshInterface();
    }

    private void resetGame() {
        //Back to the initial unsolved state
        game.setCurrentState(new Grid(game.getInitialState()));
        refreshInterface();
    }

    private void newGame() {
        //Generate new puzzle and update the ui
        game.newGame();
        refreshInterface();
    }
}