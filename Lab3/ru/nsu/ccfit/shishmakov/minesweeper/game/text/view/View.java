package ru.nsu.ccfit.shishmakov.minesweeper.game.text.view;

import ru.nsu.ccfit.shishmakov.minesweeper.game.cell.Cell;
import ru.nsu.ccfit.shishmakov.minesweeper.game.model.Model;
import ru.nsu.ccfit.shishmakov.minesweeper.game.text.controller.Controller;
import ru.nsu.ccfit.shishmakov.minesweeper.switcher.Switch;
import ru.nsu.ccfit.shishmakov.minesweeper.utils.constants.CommandConstants;
import ru.nsu.ccfit.shishmakov.minesweeper.utils.constants.ControllerConstants;
import ru.nsu.ccfit.shishmakov.minesweeper.utils.constants.FieldConstants;
import ru.nsu.ccfit.shishmakov.minesweeper.utils.constants.GameConstants;

import java.io.IOException;
import java.util.Scanner;

public class View {
    public View()
    {
        this.model = new Model();
        this.controller = new Controller(this.model);
        this.scanner = new Scanner(System.in);
        this.switcher = new Switch(this.controller, this.scanner);
    }

    public void startGame()
    {
        while (true)
        {
            System.out.print("Enter the command: ");
            String command = this.scanner.nextLine();

            try
            {
                if (command.equals(CommandConstants.EXIT_GAME_COMMAND_NAME))
                {
                    this.scanner.close();
                }

                this.switcher.execute(command);
            } catch (IOException ex)
            {
                ex.printStackTrace();
                break;
            }

            if (command.equals(CommandConstants.NEW_GAME_COMMAND_NAME))
            {
                break;
            }

        }
    }

    public void runGame()
    {
        while (true)
        {
            System.out.println("Enter two coordinates (x and y): ");

            String strCoordinates = this.scanner.nextLine();
            String[] coords = strCoordinates.split(" ");

            if (!this.controller.checkCoordinatesCorrectness(coords))
            {
                continue;
            }

            char operation = 0;

            while (operation != FieldConstants.REVEAL_CELL && operation != FieldConstants.SET_FLAG
                    && operation != FieldConstants.UNSET_FLAG)
            {
                System.out.println("Enter operation - \"R\" or \"F\" or \"U\" " +
                        "(R - reveal cell, F - set flag, U - unset flag): ");
                operation = this.scanner.nextLine().charAt(0);

                if (!this.controller.checkOperationCorrectness(operation))
                {
                    System.out.println(ControllerConstants.INVALID_OPERATION_ERROR_MESSAGE);
                }
            }

            int gameStatus = this.controller.handleOperation(operation, Integer.parseInt(coords[0]), Integer.parseInt(coords[1]));

            if (gameStatus != GameConstants.WRONG_FIRST_OPERATION)
            {
                this.printField();
            }

            if (gameStatus == GameConstants.VICTORY)
            {
                System.out.println(GameConstants.VICTORY_MESSAGE);
                break;
            }
            else if (gameStatus == GameConstants.GAME_OVER)
            {
                System.out.println(GameConstants.LOSE_MESSAGE);
                break;
            }
        }

        this.scanner.close();
    }

    public void printField()
    {
        Cell[][] field = model.getField();
        int fieldWidth = model.getFieldWidth();
        int fieldHeight = model.getFieldHeight();

        for (int x = 0; x < fieldWidth; ++x)
        {
            for (int y = 0; y < fieldHeight; ++y)
            {
                System.out.print(field[x][y].getCellAppearance());
            }
            System.out.println();
        }
    }

    private final Controller controller;
    private final Model model;
    private final Switch switcher;
    private Scanner scanner;
}
