package ru.nsu.ccfit.shishmakov.minesweeper.game.gui.main;

import javafx.application.Application;
import javafx.stage.Stage;
import ru.nsu.ccfit.shishmakov.minesweeper.game.gui.view.MainMenuViewGUI;
import ru.nsu.ccfit.shishmakov.minesweeper.game.gui.view.ViewGUI;


public class MainGUI extends Application {
    private static final String GAME_LABEL = "Minesweeper";

    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.setTitle(GAME_LABEL);
        ViewGUI view = new MainMenuViewGUI(primaryStage);
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}