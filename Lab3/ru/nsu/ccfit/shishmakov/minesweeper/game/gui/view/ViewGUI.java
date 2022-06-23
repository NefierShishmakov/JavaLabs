package ru.nsu.ccfit.shishmakov.minesweeper.game.gui.view;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ViewGUI {
    private static final int MAIN_VIEW_WIDTH = 1600;
    private static final int MAIN_VIEW_HEIGHT = 900;

    public ViewGUI(Stage primaryStage)
    {
        this.primaryStage = primaryStage;
    }

    public void showStage()
    {
        this.primaryStage.show();
    }

    public Stage getPrimaryStage()
    {
        return this.primaryStage;
    }

    public void setSceneForStage(AnchorPane pane)
    {
        Scene primaryScene = new Scene(pane, MAIN_VIEW_WIDTH, MAIN_VIEW_HEIGHT);
        this.primaryStage.setResizable(false);
        this.primaryStage.setScene(primaryScene);
    }

    private final Stage primaryStage;
}
