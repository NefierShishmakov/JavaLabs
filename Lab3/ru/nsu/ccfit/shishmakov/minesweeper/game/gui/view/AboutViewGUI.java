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

public class AboutViewGUI extends ViewGUI {
    public AboutViewGUI(Stage primaryStage) {
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
        Image image = new Image(ViewConstants.ABOUT_BACKGROUND_IMAGE);
        BackgroundImage backgroundImage = new BackgroundImage(image,
                                                              BackgroundRepeat.NO_REPEAT,
                                                              BackgroundRepeat.NO_REPEAT,
                                                              BackgroundPosition.DEFAULT,
                                                              BackgroundSize.DEFAULT);

        this.pane.setBackground(new Background(backgroundImage));
    }

    private void createBackButton()
    {
        GameButton backButton = new GameButton(ButtonConstants.BACK_BUTTON_TEXT,
                                               ButtonConstants.BACK_BUTTON_X_POS,
                                               ButtonConstants.BACK_BUTTON_Y_POS);

        backButton.setOnAction(actionEvent -> {
           this.pane.getChildren().clear();
           new MainMenuViewGUI(getPrimaryStage());
        });

        this.buttonsList.add(backButton);
    }

    private void createButtons()
    {
        this.createBackButton();
    }

    private void addButtons()
    {
        this.pane.getChildren().addAll(this.buttonsList);
    }

    private final AnchorPane pane;
    private final ArrayList<GameButton> buttonsList;
}
