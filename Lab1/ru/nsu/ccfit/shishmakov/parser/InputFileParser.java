package ru.nsu.ccfit.shishmakov.parser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import ru.nsu.ccfit.shishmakov.exceptions.CommandException;
import ru.nsu.ccfit.shishmakov.utils.Background;
import ru.nsu.ccfit.shishmakov.utils.CONSTANTS;
import ru.nsu.ccfit.shishmakov.exceptions.WrongCommandException;
import ru.nsu.ccfit.shishmakov.exceptions.WrongCommandArgumentsException;

public class InputFileParser {
    public InputFileParser(InputStream stream)
    {
        this.isKeyboardInput = stream.equals(System.in);
    }

    public ArrayList<String[]> getCommandsList(Scanner scanner) throws CommandException
    {
        ArrayList<String[]> commandsList = new ArrayList<>();

        while (scanner.hasNextLine())
        {
            String commandLine = scanner.nextLine();

            if (commandLine.isEmpty() || commandLine.charAt(CONSTANTS.START_INDEX) == CONSTANTS.COMMENT_SYM)
            {
                continue;
            }

            String[] parsedCommand = commandLine.split(CONSTANTS.DEFAULT_WORD_REGEX);
            if (this.isKeyboardInput)
            {
                if (parsedCommand.length == CONSTANTS.DEFAULT_ARGS_COUNT &&
                        parsedCommand[CONSTANTS.START_INDEX].equals(CONSTANTS.END_OF_FILE))
                {
                    break;
                }
            }

            String command = parsedCommand[CONSTANTS.START_INDEX];
            if (Background.isCommand(command))
            {
                if (!Background.correctArguments(parsedCommand))
                {
                    throw new WrongCommandArgumentsException(CONSTANTS.WRONG_COMMAND_ARGS_ERROR + command +
                            CONSTANTS.ERROR_MESSAGE);
                }
            }
            else
            {
                throw new WrongCommandException(CONSTANTS.UNDEFINED_COMMAND_ERROR + command + CONSTANTS.ERROR_MESSAGE);
            }

            commandsList.add(parsedCommand);
        }

        return commandsList;
    }

    private final boolean isKeyboardInput;
}
