package ru.nsu.ccfit.shishmakov.minesweeper.game.gui.view;

import javafx.scene.image.Image;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Background;
import javafx.stage.Stage;
import ru.nsu.ccfit.shishmakov.minesweeper.utils.button.GameButton;
import ru.nsu.ccfit.shishmakov.minesweeper.utils.constants.ButtonConstants;
import ru.nsu.ccfit.shishmakov.minesweeper.utils.constants.ViewConstants;

import java.util.ArrayList;

public class MainMenuViewGUI extends ViewGUI {
    public MainMenuViewGUI(Stage primaryStage)
    {
        super(primaryStage);
        pane = new AnchorPane();
        setSceneForStage(pane);
        showStage();

        this.buttonsList = new ArrayList<>();

        this.setBackground();
        this.createButtons();
        this.addButtons();
    }

    private void setBackground()
    {
        Image image = new Image(ViewConstants.MAIN_BACKGROUND_IMAGE);
        BackgroundImage backgroundImage = new BackgroundImage(image,
                                                              BackgroundRepeat.NO_REPEAT,
                                                              BackgroundRepeat.NO_REPEAT,
                                                              BackgroundPosition.DEFAULT,
                                                              BackgroundSize.DEFAULT);

        this.pane.setBackground(new Background(backgroundImage));
    }

    private void createGameButton()
    {
        GameButton newGameButton = new GameButton(ButtonConstants.NEW_GAME_BUTTON_TEXT,
                                                  ButtonConstants.NEW_GAME_BUTTON_X_POS,
                                                  ButtonConstants.NEW_GAME_BUTTON_Y_POS);

        newGameButton.setOnAction(actionEvent -> {
            this.pane.getChildren().clear();
            new GameParametersViewGUI(getPrimaryStage());
        });

        this.buttonsList.add(newGameButton);
    }

    private void createHighScoresButton()
    {
        GameButton highScoresButton = new GameButton(ButtonConstants.HIGH_SCORES_BUTTON_TEXT,
                                                     ButtonConstants.HIGH_SCORES_BUTTON_X_POS,
                                                     ButtonConstants.HIGH_SCORES_BUTTON_Y_POS);

        highScoresButton.setOnAction(actionEvent -> {
            this.pane.getChildren().clear();
            new HighScoresViewGUI(getPrimaryStage());
        });

        this.buttonsList.add(highScoresButton);
    }

    private void createAboutButton()
    {
        GameButton aboutButton = new GameButton(ButtonConstants.ABOUT_BUTTON_TEXT,
                                                ButtonConstants.ABOUT_BUTTON_X_POS,
                                                ButtonConstants.ABOUT_BUTTON_Y_POS);

        aboutButton.setOnAction(actionEvent -> {
            this.pane.getChildren().clear();
            new AboutViewGUI(getPrimaryStage());
        });

        this.buttonsList.add(aboutButton);
    }

    private void createExitButton()
    {
        GameButton exitButton = new GameButton(ButtonConstants.EXIT_BUTTON_TEXT,
                                               ButtonConstants.EXIT_BUTTON_X_POS,
                                               ButtonConstants.EXIT_BUTTON_Y_POS);

        exitButton.setOnAction(actionEvent -> {
            this.pane.getChildren().clear();
            System.exit(0);
        });

        this.buttonsList.add(exitButton);
    }

    private void createButtons()
    {
        this.createGameButton();
        this.createHighScoresButton();
        this.createAboutButton();
        this.createExitButton();
    }

    private void addButtons()
    {
        this.pane.getChildren().addAll(this.buttonsList);
    }

    private final AnchorPane pane;
    private final ArrayList<GameButton> buttonsList;
}
