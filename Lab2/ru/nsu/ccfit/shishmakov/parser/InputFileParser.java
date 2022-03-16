package ru.nsu.ccfit.shishmakov.parser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import ru.nsu.ccfit.shishmakov.exceptions.CommandException;
import ru.nsu.ccfit.shishmakov.utils.background.Background;
import ru.nsu.ccfit.shishmakov.utils.constants.ErrorConstants;
import ru.nsu.ccfit.shishmakov.utils.constants.MainConstants;
import ru.nsu.ccfit.shishmakov.exceptions.WrongCommandException;
import ru.nsu.ccfit.shishmakov.exceptions.WrongCommandArgumentsException;

public class InputFileParser {
    private static final String END_OF_FILE = "EOF";
    private static final String DEFAULT_WORD_REGEX = "\\s";
    private static final char COMMENT_SYM = '#';

    public InputFileParser(InputStream stream)
    {
        this.stream = stream;
        this.isKeyboardInput = this.stream.equals(System.in);
    }

    public ArrayList<String[]> getCommandsList() throws CommandException
    {
        ArrayList<String[]> commandsList = new ArrayList<>();

        Scanner scanner = new Scanner(this.stream);

        while (scanner.hasNextLine())
        {
            String commandLine = scanner.nextLine();

            if (commandLine.isEmpty() || commandLine.charAt(MainConstants.START_INDEX) == COMMENT_SYM)
            {
                continue;
            }

            String[] parsedCommand = commandLine.split(DEFAULT_WORD_REGEX);
            if (this.isKeyboardInput)
            {
                if (parsedCommand.length == MainConstants.DEFAULT_ARGS_COUNT &&
                        parsedCommand[MainConstants.START_INDEX].equals(END_OF_FILE))
                {
                    break;
                }
            }

            String command = parsedCommand[MainConstants.START_INDEX];
            if (Background.isCommand(command))
            {
                if (!Background.correctArguments(parsedCommand))
                {
                    scanner.close();
                    throw new WrongCommandArgumentsException(ErrorConstants.WRONG_COMMAND_ARGS_ERROR + command +
                            ErrorConstants.ERROR_MESSAGE);
                }
            }
            else
            {
                scanner.close();
                throw new WrongCommandException(ErrorConstants.UNDEFINED_COMMAND_ERROR + command + ErrorConstants.ERROR_MESSAGE);
            }

            commandsList.add(parsedCommand);
        }

        scanner.close();
        return commandsList;
    }

    private final boolean isKeyboardInput;
    private final InputStream stream;
}
