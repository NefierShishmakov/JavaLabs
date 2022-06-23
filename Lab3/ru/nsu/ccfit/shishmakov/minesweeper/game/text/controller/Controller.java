package ru.nsu.ccfit.shishmakov.minesweeper.game.text.controller;

import ru.nsu.ccfit.shishmakov.minesweeper.game.cell.Cell;
import ru.nsu.ccfit.shishmakov.minesweeper.game.model.Model;
import ru.nsu.ccfit.shishmakov.minesweeper.utils.background.Background;
import ru.nsu.ccfit.shishmakov.minesweeper.utils.constants.ControllerConstants;
import ru.nsu.ccfit.shishmakov.minesweeper.utils.constants.FieldConstants;
import ru.nsu.ccfit.shishmakov.minesweeper.utils.constants.GameConstants;

import java.util.Arrays;

public class Controller {

    public Controller(Model model)
    {
        this.model = model;
    }

    public void setField(int fieldWidth, int fieldHeight, int bombsAmount)
    {
        model.setFieldParameters(fieldWidth, fieldHeight, bombsAmount);
        model.initField();
    }

    public void resetModel()
    {
        this.model.setFirstClick();
        this.model.resetFieldAndFieldParameters();
    }

    public int getChosenCellXPos()
    {
        return this.chosenCellXPos;
    }

    public int getChosenCellYPos()
    {
        return this.chosenCellYPos;
    }

    public int handleOperation(char operation, int x, int y)
    {
        if (model.isFirstClicked())
        {
            if (operation == FieldConstants.SET_FLAG || operation == FieldConstants.UNSET_FLAG)
            {
                System.out.println(ControllerConstants.FIRST_OPERATION_ERROR_MESSAGE);
                return GameConstants.WRONG_FIRST_OPERATION;
            }

            model.unsetFirstClick();
            model.placeBombs(Background.getPairs(x, y, model.getFieldWidth(), model.getFieldHeight()));
        }

        return model.doOperation(operation, x, y);
    }

    public Cell getCell()
    {
        Cell[][] field = this.model.getField();

        if (this.chosenCellYPos == -1 && this.chosenCellXPos == -1)
        {
            return null;
        }

        return field[this.chosenCellXPos][this.chosenCellYPos];
    }

    public boolean isMousePositionCorrect(double xPos, double yPos)
    {
        Cell[][] field = this.model.getField();
        int fieldWidth = this.model.getFieldWidth();
        int fieldHeight = this.model.getFieldHeight();

        for (int x = 0; x < fieldWidth; ++x)
        {
            for (int y = 0; y < fieldHeight; ++y)
            {
                Cell cell = field[x][y];

                if (cell.getBoundsInParent().contains(xPos, yPos))
                {
                    this.chosenCellXPos = x;
                    this.chosenCellYPos = y;
                    return true;
                }
            }
        }

        return false;
    }

    public boolean checkCoordinatesCorrectness(String[] coords)
    {
        if (coords.length == ControllerConstants.IMPOSSIBLE_COORDINATES_LENGTH)
        {
            System.out.println(ControllerConstants.COORDINATES_NUM_ERROR_MESSAGE);
            return false;
        }

        try
        {
            int x = Integer.parseInt(coords[0]);
            int y = Integer.parseInt(coords[1]);
        } catch (NumberFormatException ex)
        {
            System.out.println(ControllerConstants.COORDINATE_TYPE_ERROR_MESSAGE);
            return false;
        }

        int[] realCoords = Arrays.stream(coords).mapToInt(Integer::parseInt).toArray();

        String[] values = FieldConstants.VALUES;
        int[] fieldParams = model.getArrayOfFieldParameters();

        for (int i = 0; i < coords.length; ++i)
        {
            if (!(realCoords[i] > - 1 && realCoords[i] < fieldParams[i]))
            {
                System.out.println(values[i] + FieldConstants.START_COORDINATE + " to " + (fieldParams[i] - 1));
                return false;
            }
        }

        return true;
    }

    public boolean checkOperationCorrectness(char operation)
    {
        return (operation == FieldConstants.REVEAL_CELL || operation == FieldConstants.SET_FLAG
                || operation == FieldConstants.UNSET_FLAG);
    }

    private final Model model;

    private int chosenCellXPos = -1;
    private int chosenCellYPos = -1;
}
