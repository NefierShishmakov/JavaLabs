package ru.nsu.ccfit.shishmakov.minesweeper.utils.button;

import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;

public class GameButton extends Button {
    private static final int BUTTON_WIDTH = 280;
    private static final int BUTTON_HEIGHT = 115;
    private static final int Y_SHIFT = 5;

    private static final String DEFAULT_STYLE = "-fx-background-color: transparent; -fx-background-image: url('default_button_style.png'); " +
            "-fx-text-fill: #E30613; -fx-font-size: 40; ";
    private static final String PRESSED_STYLE = "-fx-background-color: transparent; -fx-background-image: url('pressed_button_style.png'); " +
            "-fx-text-fill: #4DEEFF; -fx-font-size: 40; ";
    private static final String RELEASED_STYLE = "-fx-background-color: transparent; -fx-background-image: url('default_button_style.png'); " +
            "-fx-text-fill: #E30613; -fx-font-size: 40; ";
    private static final String ENTERED_STYLE = "-fx-background-color: transparent; -fx-background-image: url('default_button_style.png'); " +
            "-fx-text-fill: #4DEEFF; -fx-font-size: 40; ";

    public GameButton(String buttonText, int xPosition, int yPosition)
    {
        this.setText(buttonText);
        this.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        this.setStyle(DEFAULT_STYLE);
        this.setLayoutX(xPosition);
        this.setLayoutY(yPosition);

        this.initButtonActions();
    }

    public void setPressedStyle()
    {
        this.setStyle(PRESSED_STYLE);
        this.setLayoutY(this.getLayoutY() + Y_SHIFT);
    }

    public void setReleasedStyle()
    {
        this.setStyle(RELEASED_STYLE);
        this.setLayoutY(this.getLayoutY() - Y_SHIFT);
    }

    public void setEnteredStyle()
    {
        this.setStyle(ENTERED_STYLE);
    }

    public void setExitedStyle()
    {
        this.setStyle(DEFAULT_STYLE);
    }


    public void initButtonActions()
    {
        this.setOnMousePressed(mouseEvent -> {
            if (mouseEvent.getButton().equals(MouseButton.PRIMARY))
            {
                this.setPressedStyle();
            }
        });

        this.setOnMouseReleased(mouseEvent -> {
            if (mouseEvent.getButton().equals(MouseButton.PRIMARY))
            {
                this.setReleasedStyle();
            }
        });

        this.setOnMouseEntered(mouseEvent -> this.setEnteredStyle());

        this.setOnMouseExited(mouseEvent -> this.setExitedStyle());
    }
}
