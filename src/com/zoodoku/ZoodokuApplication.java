package com.zoodoku;

import com.zoodoku.problem.ZoodokuGame;
import com.zoodoku.ui.GameInterface;
import javafx.application.Application;
import javafx.stage.Stage;

public class ZoodokuApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        GameInterface ui = new GameInterface(primaryStage, new ZoodokuGame());
        ui.InitializeInterface();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
