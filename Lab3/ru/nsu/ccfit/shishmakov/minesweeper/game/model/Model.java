package ru.nsu.ccfit.shishmakov.minesweeper.game.model;

import javafx.util.Pair;
import ru.nsu.ccfit.shishmakov.minesweeper.game.cell.Cell;
import ru.nsu.ccfit.shishmakov.minesweeper.utils.background.Background;
import ru.nsu.ccfit.shishmakov.minesweeper.utils.constants.CellConstants;
import ru.nsu.ccfit.shishmakov.minesweeper.utils.constants.FieldConstants;
import ru.nsu.ccfit.shishmakov.minesweeper.utils.constants.GameConstants;

import java.util.ArrayList;

public class Model {
    public Model()
    {
        bombs = new ArrayList<>();
        wrongMarkedCells = new ArrayList<>();
    }

    public void setFieldParameters(int fieldWidth, int fieldHeight, int bombsAmount)
    {
        this.fieldWidth = fieldWidth;
        this.fieldHeight = fieldHeight;
        this.bombsAmount = bombsAmount;
        this.activeCells = this.fieldHeight * this.fieldWidth - this.bombsAmount;
    }

    public void initField()
    {
        this.field = new Cell[this.fieldWidth][this.fieldHeight];

        for (int x = 0; x < this.fieldWidth; ++x)
        {
            for (int y = 0; y < this.fieldHeight; ++y)
            {
                this.field[x][y] = new Cell(x, y);
            }
        }
    }

    public void resetFieldAndFieldParameters()
    {
        for (int x = 0; x < this.fieldWidth; ++x)
        {
            for (int y = 0; y < this.fieldHeight; ++y)
            {
                this.field[x][y].setDefaultView();
            }
        }

        this.bombs.clear();
        this.wrongMarkedCells.clear();
        this.activeCells = this.fieldHeight * this.fieldWidth - this.bombsAmount;
    }

    public void placeBombs(ArrayList<Pair<Integer, Integer>> pairs)
    {
        for (int i = 0; i < this.bombsAmount; ++i)
        {
            Pair<Integer, Integer> cell = pairs.get(Background.getRandomIndex(pairs.size() - 1));
            int cellX = cell.getKey();
            int cellY = cell.getValue();
            this.field[cellX][cellY].setBomb();
            this.bombs.add(this.field[cellX][cellY]);
            pairs.remove(cell);
        }
    }

    public int doOperation(char operation, int x, int y)
    {
        Cell cell = this.field[x][y];

        switch (operation) {
            case FieldConstants.SET_FLAG -> {
                if (!cell.getIsReveled())
                {
                    cell.setFlag();
                    if (!cell.isBomb() && !this.wrongMarkedCells.contains(cell))
                    {
                        this.wrongMarkedCells.add(cell);
                    }
                }
            }
            case FieldConstants.UNSET_FLAG -> {
                if (cell.isFlag())
                {
                    cell.unsetFlag();
                    this.wrongMarkedCells.remove(cell);
                }
            }
            case FieldConstants.REVEAL_CELL -> {
                if (cell.isBomb() && !cell.isFlag())
                {
                    cell.setBombIsPicked();
                    this.setBombsAndWrongMarkedCellsAppearance();
                    return GameConstants.GAME_OVER;
                }

                this.revealNeighboringCells(x, y);
            }
        }

        if (this.activeCells == CellConstants.ACTIVE_CELLS_VICTORY_VALUE)
        {
            return GameConstants.VICTORY;
        }

        return GameConstants.CONTINUE;
    }

    public boolean isFirstClicked()
    {
        return this.isFirstClick;
    }

    public void unsetFirstClick()
    {
        this.isFirstClick = false;
    }

    public void setFirstClick()
    {
        this.isFirstClick = true;
    }

    public int getFieldWidth()
    {
        return this.fieldWidth;
    }

    public int getFieldHeight()
    {
        return this.fieldHeight;
    }

    public int[] getArrayOfFieldParameters()
    {
        return new int[]{this.fieldWidth, this.fieldHeight};
    }

    public void setBombsAndWrongMarkedCellsAppearance()
    {
        for (Cell bomb: bombs)
        {
            if (!bomb.isFlag())
            {
                if (bomb.isBombPicked())
                {
                    bomb.setCellAppearance(FieldConstants.BOMB_PICKED);
                }
                else
                {
                    bomb.setCellAppearance(FieldConstants.BOMB_CELL);
                }

                bomb.setReveled();
            }
        }

        for (Cell wrongMarkedCell: wrongMarkedCells)
        {
            wrongMarkedCell.setCellAppearance(FieldConstants.WRONG_MARKED_CELL);
            wrongMarkedCell.setReveled();
        }
    }

    public void setNumbers(Cell currentCell, int cellX, int cellY)
    {
        int bombsAroundNum = 0;

        for (int deltaX = -1; deltaX <= 1; ++deltaX)
        {
            for (int deltaY = -1; deltaY <= 1; ++deltaY)
            {
                int xIdx = cellX + deltaX;
                int yIdx = cellY + deltaY;

                if (Background.indicesCorrect(xIdx, yIdx, this.fieldWidth, this.fieldHeight) && this.field[xIdx][yIdx].isBomb())
                {
                    ++bombsAroundNum;
                }
            }
        }

        currentCell.setBombsAroundNum(bombsAroundNum);
    }

    public void revealNeighboringCells(int startX, int startY)
    {
        Cell currentCell = field[startX][startY];

        if (currentCell.isFlag() || currentCell.isBomb() || currentCell.getIsReveled())
        {
            return;
        }

        this.setNumbers(currentCell, startX, startY);
        currentCell.setCellAppearance(Character.forDigit(currentCell.getBombsAroundNum(), 10));
        currentCell.setReveled();

        --this.activeCells;

        if (currentCell.isNotBlank())
        {
            return;
        }

        for (int deltaX = -1; deltaX <= 1; ++deltaX)
        {
            for (int deltaY = -1; deltaY <= 1; ++deltaY)
            {
                int xIdx = startX + deltaX;
                int yIdx = startY + deltaY;

                if (Background.indicesCorrect(xIdx, yIdx, this.fieldWidth, this.fieldHeight))
                {
                    revealNeighboringCells(xIdx, yIdx);
                }
            }
        }

    }

    public Cell[][] getField()
    {
        return this.field;
    }

    private Cell[][] field;
    private final ArrayList<Cell> bombs;
    private final ArrayList<Cell> wrongMarkedCells;

    private int fieldWidth;
    private int fieldHeight;
    private int bombsAmount;
    private int activeCells;

    private boolean isFirstClick = true;
}
