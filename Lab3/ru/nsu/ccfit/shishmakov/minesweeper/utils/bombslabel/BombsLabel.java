package ru.nsu.ccfit.shishmakov.minesweeper.utils.bombslabel;

import javafx.scene.control.Label;

public class BombsLabel extends Label {
    private static final String BOMBS_LABEL_TEXT = "Bombs: ";
    private static final String BOMBS_LABEL_STYLE = "-fx-text-fill: #F4CA16; -fx-font-size: 40; -fx-font-weight: bold;";
    private static final int X_POS = 1300;
    private static final int Y_POS = 80;

    public BombsLabel(int bombsNum)
    {
        this.bombsNum = bombsNum;
        this.decreasedBombsNum = this.bombsNum;
        this.setStyle(BOMBS_LABEL_STYLE);
        this.setText(BOMBS_LABEL_TEXT + bombsNum);
        this.setLayoutX(X_POS);
        this.setLayoutY(Y_POS);
    }

    public void increaseValue()
    {
        if (this.decreasedBombsNum + 1 <= this.bombsNum)
        {
            this.setText(BOMBS_LABEL_TEXT + (++this.decreasedBombsNum));
        }
    }

    public void decreaseValue()
    {
        this.setText(BOMBS_LABEL_TEXT + (--this.decreasedBombsNum));
    }

    public void setDefaultValue()
    {
        this.decreasedBombsNum = this.bombsNum;
        this.setText(BOMBS_LABEL_TEXT + bombsNum);
    }

    private final int bombsNum;
    private int decreasedBombsNum;
}
