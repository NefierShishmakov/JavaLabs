package ru.nsu.ccfit.shishmakov.minesweeper.command;

import java.util.Scanner;

import ru.nsu.ccfit.shishmakov.minesweeper.game.text.controller.Controller;
import ru.nsu.ccfit.shishmakov.minesweeper.utils.background.Background;
import ru.nsu.ccfit.shishmakov.minesweeper.utils.constants.FieldConstants;

public class NewGameCommand implements Command
{
    public NewGameCommand(Controller controller, Scanner scanner)
    {
        this.controller = controller;
        this.scanner = scanner;
    }

    @Override
    public void execute()
    {
        int fieldWidth = Background.getGameParameter(FieldConstants.WIDTH, this.scanner, FieldConstants.NO_FIELD_SQUARE);
        int fieldHeight = Background.getGameParameter(FieldConstants.HEIGHT, this.scanner, FieldConstants.NO_FIELD_SQUARE);
        int fieldBombs = Background.getGameParameter(FieldConstants.BOMBS, this.scanner, fieldHeight * fieldWidth);

        this.controller.setField(fieldWidth, fieldHeight, fieldBombs);
    }

    private final Controller controller;
    private Scanner scanner;
}
