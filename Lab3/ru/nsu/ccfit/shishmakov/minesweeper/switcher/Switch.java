package ru.nsu.ccfit.shishmakov.minesweeper.switcher;

import ru.nsu.ccfit.shishmakov.minesweeper.command.*;
import ru.nsu.ccfit.shishmakov.minesweeper.game.text.controller.Controller;
import ru.nsu.ccfit.shishmakov.minesweeper.utils.constants.CommandConstants;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Switch {
    private final HashMap<String, Command> commands = new HashMap<>();

    public Switch(Controller controller, Scanner scanner)
    {
        commands.put(CommandConstants.NEW_GAME_COMMAND_NAME, new NewGameCommand(controller, scanner));
        commands.put(CommandConstants.EXIT_GAME_COMMAND_NAME, new ExitCommand());
        commands.put(CommandConstants.ABOUT_GAME_COMMAND_NAME, new AboutCommand());
        commands.put(CommandConstants.HIGH_SCORES_GAME_COMMAND_NAME, new HighScoresCommand());
    }

    public void execute(String strCommand) throws IOException
    {
        Command command = this.commands.get(strCommand);
        if (command == null)
        {
            System.out.println(CommandConstants.WRONG_COMMAND_ERROR_MESSAGE);
        }
        else
        {
            command.execute();
        }
    }
}
