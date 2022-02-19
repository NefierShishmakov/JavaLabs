package ru.nsu.ccfit.shishmakov.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import javax.lang.model.SourceVersion;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import ru.nsu.ccfit.shishmakov.exceptions.CmdArgsException;

public class Background {
    private Background() {}

    public static InputStream cmdAnalyzer(String[] args) throws CmdArgsException, FileNotFoundException
    {
        InputStream resultStream;
        if (args.length == CONSTANTS.NO_ARGS)
        {
            resultStream = System.in;
        }
        else if (args.length == CONSTANTS.DEFAULT_ARGS_COUNT)
        {
            String fileName = args[CONSTANTS.FIRST_CMD_ARGUMENT];
            if (fileName.lastIndexOf(CONSTANTS.INPUT_FILE_EXTENSION) == CONSTANTS.ERROR_INDEX)
            {
                throw new CmdArgsException(CONSTANTS.FILE_EXTENSION_ERROR + CONSTANTS.ERROR_MESSAGE);
            }

            try {
                resultStream = new FileInputStream(fileName);
            } catch (FileNotFoundException ex)
            {
                throw new FileNotFoundException(CONSTANTS.OPEN_FILE_ERROR + fileName + CONSTANTS.ERROR_MESSAGE);
            }
        }
        else
        {
            throw new CmdArgsException(CONSTANTS.CMD_ARGS_COUNT_ERROR + CONSTANTS.ERROR_MESSAGE);
        }

        return resultStream;
    }

    public static boolean isCommand(String command)
    {
        return CONSTANTS.COMMANDS.containsKey(command);
    }

    public static boolean correctArguments(String[] parsed_command)
    {
        return parsed_command.length == CONSTANTS.COMMANDS.get(parsed_command[CONSTANTS.START_INDEX]);
    }

    public static boolean isVariableNameCorrect(String varName)
    {
        Pattern pattern = Pattern.compile(CONSTANTS.VARIABLE_NAME_REGEX);
        Matcher matcher = pattern.matcher(varName.substring(1));

        return !(SourceVersion.isKeyword(varName) || varName.substring(0, 1).matches(CONSTANTS.FIRST_CHARACTER_REGEX) ||
                matcher.find());
    }
}
