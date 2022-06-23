package ru.nsu.ccfit.shishmakov.minesweeper.game.cell;

import javafx.scene.layout.StackPane;
import ru.nsu.ccfit.shishmakov.minesweeper.utils.constants.CellConstants;
import ru.nsu.ccfit.shishmakov.minesweeper.utils.constants.FieldConstants;

public class Cell extends StackPane {
    private static final int SHIFT_X = 12;
    private static final int SHIFT_Y = 1;

    private static final int START_BOMBS_NUM = 0;

    public Cell(int x, int y)
    {
        this.x = (x + SHIFT_X) * CellConstants.CELL_SIZE;
        this.y = (y + SHIFT_Y) * CellConstants.CELL_SIZE;
        this.setPrefWidth(CellConstants.CELL_SIZE);
        this.setPrefHeight(CellConstants.CELL_SIZE);
        this.setTranslateX(this.x);
        this.setTranslateY(this.y);
        this.setDisable(true);
        this.setStyle(CellConstants.DEFAULT_CELL_STYLE);
    }

    public void setDefaultView()
    {
        this.hasBomb = false;
        this.hasFlag = false;
        this.isReveled = false;
        this.bombIsPicked = false;
        this.bombsAroundNum = START_BOMBS_NUM;

        this.setCellAppearance(FieldConstants.CLOSED_CELL);
    }

    public void setBomb()
    {
        this.hasBomb = true;
    }

    public boolean isNotBlank()
    {
        return this.bombsAroundNum != START_BOMBS_NUM;
    }

    public void setCellAppearance(char newAppearance)
    {
        if (newAppearance >= '0' && newAppearance <= '9')
        {
            this.setStyle(CellConstants.MAP_OF_CELL_NUMBER_STYLES.get(newAppearance));
        }
        else
        {
            this.setStyle(CellConstants.MAP_OF_CELL_NOT_NUMBER_STYLES.get(newAppearance));
        }

        this.cellAppearance = newAppearance;
    }

    public char getCellAppearance()
    {
        if (this.isReveled)
        {
            return this.cellAppearance;
        }

        return FieldConstants.CLOSED_CELL;
    }

    public void setFlag()
    {
        this.hasFlag = true;
        this.setReveled();
        this.setCellAppearance(FieldConstants.SET_FLAG);
    }

    public boolean isFlag()
    {
        return this.hasFlag;
    }

    public void setBombIsPicked()
    {
        this.bombIsPicked = true;
    }

    public boolean isBombPicked()
    {
        return this.bombIsPicked;
    }

    public void unsetFlag()
    {
        this.hasFlag = false;
        this.isReveled = false;
        this.setCellAppearance(FieldConstants.CLOSED_CELL);
    }

    public void setBombsAroundNum(int newValue)
    {
        this.bombsAroundNum = newValue;
    }

    public int getBombsAroundNum()
    {
        return this.bombsAroundNum;
    }

    public void setReveled()
    {
        this.isReveled = true;
    }

    public boolean getIsReveled()
    {
        return this.isReveled;
    }

    public boolean isBomb()
    {
        return this.hasBomb;
    }


    private final int x;
    private final int y;

    private int bombsAroundNum = START_BOMBS_NUM;

    private boolean hasBomb = false;
    private boolean hasFlag = false;
    private boolean isReveled = false;
    private boolean bombIsPicked = false;

    private char cellAppearance = FieldConstants.CLOSED_CELL;
}
