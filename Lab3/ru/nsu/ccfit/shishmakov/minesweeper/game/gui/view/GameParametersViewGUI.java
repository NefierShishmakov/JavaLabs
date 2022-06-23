package ru.nsu.ccfit.shishmakov.minesweeper.game.gui.view;

import javafx.scene.image.Image;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import ru.nsu.ccfit.shishmakov.minesweeper.game.text.controller.GameParametersViewController;
import ru.nsu.ccfit.shishmakov.minesweeper.utils.button.GameButton;
import ru.nsu.ccfit.shishmakov.minesweeper.utils.constants.ButtonConstants;
import ru.nsu.ccfit.shishmakov.minesweeper.utils.constants.SlidersConstants;
import ru.nsu.ccfit.shishmakov.minesweeper.utils.constants.ViewConstants;
import ru.nsu.ccfit.shishmakov.minesweeper.utils.fieldbox.FieldBox;
import ru.nsu.ccfit.shishmakov.minesweeper.utils.namebox.NameBox;
import ru.nsu.ccfit.shishmakov.minesweeper.utils.slider.TextSlider;

import java.util.ArrayList;

public class GameParametersViewGUI extends ViewGUI {
    public GameParametersViewGUI(Stage primaryStage)
    {
        super(primaryStage);
        this.pane = new AnchorPane();
        setSceneForStage(this.pane);
        showStage();

        this.buttonsList = new ArrayList<>();
        this.sliders = new ArrayList<>();
        this.nameBox = new NameBox();

        this.setBackground();
        this.createComponents();
        this.addComponents();

        this.controller = new GameParametersViewController(this.nameBox.getNameTextField(), this.sliders);
    }

    private void createTextSliders()
    {
        TextSlider fieldWidthSlider = new TextSlider(SlidersConstants.FIELD_WIDTH_SLIDER_NAME,
                                                     SlidersConstants.FIELD_SIZE_MIN,
                                                     SlidersConstants.FIELD_SIZE_MAX);

        TextSlider fieldHeightSlider = new TextSlider(SlidersConstants.FIELD_HEIGHT_SLIDER_NAME,
                                                      SlidersConstants.FIELD_SIZE_MIN,
                                                      SlidersConstants.FIELD_SIZE_MAX);

        TextSlider fieldBombsNumSlider = new TextSlider(SlidersConstants.FIELD_BOMBS_NUM_SLIDER_NAME,
                                                        SlidersConstants.FIELD_BOMBS_NUM_MIN,
                                                        SlidersConstants.FIELD_BOMBS_NUM_MAX);

        this.sliders.add(fieldWidthSlider);
        this.sliders.add(fieldHeightSlider);
        this.sliders.add(fieldBombsNumSlider);
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

    private void createPlayButton()
    {
        GameButton playButton = new GameButton(ButtonConstants.PLAY_BUTTON_TEXT,
                                               ButtonConstants.PLAY_BUTTON_X_POS,
                                               ButtonConstants.PLAY_BUTTON_Y_POS);

        playButton.setOnAction(actionEvent -> {
            if (!this.controller.isNicknameTextFieldEmpty())
            {
                this.pane.getChildren().clear();
                new GameViewGUI(getPrimaryStage(), this.controller.getContext());
            }
        });

        this.buttonsList.add(playButton);
    }

    private void createButtons()
    {
        this.createBackButton();
        this.createPlayButton();
    }

    private void createComponents()
    {
        this.createButtons();
        this.createTextSliders();
    }

    private void addNameBox()
    {
        this.pane.getChildren().add(this.nameBox);
    }

    private void addButtons()
    {
        this.pane.getChildren().addAll(this.buttonsList);
    }

    private void addTextSliders()
    {
        HBox box = new HBox(30);

        for (int i = 0; i < this.sliders.size() - 1; ++i)
        {
            box.getChildren().add(this.sliders.get(i));
        }

        FieldBox fieldBox = new FieldBox(box, this.sliders.get(2));

        this.pane.getChildren().add(fieldBox);
    }

    private void addComponents()
    {
        this.addButtons();
        this.addNameBox();
        this.addTextSliders();
    }

    private final AnchorPane pane;
    private final ArrayList<GameButton> buttonsList;
    private final ArrayList<TextSlider> sliders;
    private final GameParametersViewController controller;
    private final NameBox nameBox;
}
