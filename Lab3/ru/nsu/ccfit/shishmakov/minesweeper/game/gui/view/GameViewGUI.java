package ru.nsu.ccfit.shishmakov.minesweeper.game.gui.view;

import javafx.scene.image.Image;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Background;
import javafx.stage.Stage;
import ru.nsu.ccfit.shishmakov.minesweeper.game.cell.Cell;
import ru.nsu.ccfit.shishmakov.minesweeper.game.gui.statistic.Statistic;
import ru.nsu.ccfit.shishmakov.minesweeper.game.model.Model;
import ru.nsu.ccfit.shishmakov.minesweeper.game.text.controller.Controller;
import ru.nsu.ccfit.shishmakov.minesweeper.utils.alertbox.GameResultsAlertBox;
import ru.nsu.ccfit.shishmakov.minesweeper.utils.bombslabel.BombsLabel;
import ru.nsu.ccfit.shishmakov.minesweeper.utils.button.GameButton;
import ru.nsu.ccfit.shishmakov.minesweeper.utils.constants.ButtonConstants;
import ru.nsu.ccfit.shishmakov.minesweeper.utils.constants.FieldConstants;
import ru.nsu.ccfit.shishmakov.minesweeper.utils.constants.GameConstants;
import ru.nsu.ccfit.shishmakov.minesweeper.utils.constants.ViewConstants;
import ru.nsu.ccfit.shishmakov.minesweeper.utils.context.Context;

import java.util.ArrayList;

public class GameViewGUI extends ViewGUI {
    public GameViewGUI(Stage primaryStage, Context context)
    {
        super(primaryStage);
        this.pane = new AnchorPane();
        setSceneForStage(this.pane);
        showStage();

        this.buttonsList = new ArrayList<>();
        this.context = context;

        this.model = new Model();
        this.controller = new Controller(this.model);
        this.statistic = new Statistic();

        this.createButtons();
        this.setBackground();
        this.addButtons();

        this.createField();
        this.createBombsLabel(context.getFieldParameters().get(2));
        this.handleMouseClick();
    }

    private void handleMouseClick()
    {
        this.pane.setOnMouseClicked(mouseEvent -> {
            double xPos = mouseEvent.getSceneX();
            double yPos = mouseEvent.getSceneY();

            if (this.isPlayable)
            {
                if (this.controller.isMousePositionCorrect(xPos, yPos))
                {
                    char operation = 0;

                    switch (mouseEvent.getButton())
                    {
                        case PRIMARY -> operation = FieldConstants.REVEAL_CELL;
                        case SECONDARY -> {
                            operation = FieldConstants.SET_FLAG;
                            Cell cell = this.controller.getCell();
                            this.decreaseBombsLabelValue(cell);
                        }
                        case MIDDLE -> {
                            operation = FieldConstants.UNSET_FLAG;
                            Cell cell = this.controller.getCell();
                            this.increaseBombsLabelValue(cell);
                        }
                    }

                    int res = this.controller.handleOperation(operation, this.controller.getChosenCellXPos(),
                            this.controller.getChosenCellYPos());

                    if (res == GameConstants.VICTORY || res == GameConstants.GAME_OVER)
                    {
                        this.showGameResults(res);
                        this.isPlayable = !this.isPlayable;
                    }
                }
            }
        });
    }

    private void resetWindow()
    {
        this.isPlayable = true;
        this.controller.resetModel();
        this.bombsLabel.setDefaultValue();
    }

    private void showGameResults(int res)
    {
        switch (res)
        {
            case GameConstants.VICTORY -> {
                this.statistic.addNewPlayer(this.context.getData());
                GameResultsAlertBox.display(GameConstants.VICTORY_MESSAGE);
            }
            case GameConstants.GAME_OVER -> GameResultsAlertBox.display(GameConstants.LOSE_MESSAGE);
        }
    }

    private void createField()
    {
        this.setFieldParameters();
        Cell[][] field = this.model.getField();

        int fieldWidth = this.model.getFieldWidth();
        int fieldHeight = this.model.getFieldHeight();

        for (int x = 0; x < fieldWidth; ++x)
        {
            for (int y = 0; y < fieldHeight; ++y)
            {
                this.pane.getChildren().add(field[x][y]);
            }
        }
    }

    private void createBackButton()
    {
        final int SHIFT = 170;

        GameButton backButton = new GameButton(ButtonConstants.BACK_BUTTON_TEXT,
                ButtonConstants.BACK_BUTTON_X_POS - SHIFT,
                ButtonConstants.BACK_BUTTON_Y_POS);

        backButton.setOnAction(actionEvent -> {
            this.pane.getChildren().clear();
            new GameParametersViewGUI(getPrimaryStage());
        });

        this.buttonsList.add(backButton);
    }

    private void createResetButton()
    {
        GameButton resetButton = new GameButton(ButtonConstants.RESET_BUTTON_TEXT,
                ButtonConstants.RESET_BUTTON_X_POS,
                ButtonConstants.RESET_BUTTON_Y_POS);

        resetButton.setOnAction(actionEvent -> {
            this.resetWindow();
        });

        this.buttonsList.add(resetButton);
    }

    private void createBombsLabel(int bombsNum)
    {
        this.bombsLabel = new BombsLabel(bombsNum);
        this.pane.getChildren().add(this.bombsLabel);
    }

    private void increaseBombsLabelValue(Cell cell)
    {
        if (cell != null && !model.isFirstClicked())
        {

            if (cell.isFlag())
            {
                this.bombsLabel.increaseValue();
            }
        }
    }

    private void decreaseBombsLabelValue(Cell cell)
    {
        if (cell != null && !model.isFirstClicked())
        {
            if (!cell.getIsReveled() && !cell.isFlag())
            {
                this.bombsLabel.decreaseValue();
            }
        }
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

    private void setFieldParameters()
    {
        ArrayList<Integer> parameters = this.context.getFieldParameters();
        controller.setField(parameters.get(FieldConstants.FIELD_WIDTH_INDEX), parameters.get(FieldConstants.FIELD_HEIGHT_INDEX),
                parameters.get(FieldConstants.FIELD_BOMBS_NUM_INDEX));
    }

    private void createButtons()
    {
        this.createBackButton();
        this.createResetButton();
    }

    private void addButtons()
    {
        this.pane.getChildren().addAll(this.buttonsList);
    }

    private final AnchorPane pane;
    private final ArrayList<GameButton> buttonsList;
    private final Controller controller;
    private final Model model;
    private final Context context;
    private final Statistic statistic;
    private BombsLabel bombsLabel;
    private boolean isPlayable = true;
}
