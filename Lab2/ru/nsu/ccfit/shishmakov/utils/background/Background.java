package ru.nsu.ccfit.shishmakov.utils.background;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import javax.lang.model.SourceVersion;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import ru.nsu.ccfit.shishmakov.exceptions.CmdArgsException;
import ru.nsu.ccfit.shishmakov.utils.constants.BackgroundConstants;
import ru.nsu.ccfit.shishmakov.utils.constants.ErrorConstants;
import ru.nsu.ccfit.shishmakov.utils.constants.MainConstants;

public class Background {
    private Background() {}

    public static InputStream cmdAnalyzer(String[] args) throws CmdArgsException, FileNotFoundException
    {
        InputStream resultStream;
        if (args.length == BackgroundConstants.NO_ARGS)
        {
            resultStream = System.in;
        }
        else if (args.length == BackgroundConstants.DEFAULT_ARGS_COUNT)
        {
            String fileName = args[BackgroundConstants.FIRST_CMD_ARGUMENT];
            if (fileName.lastIndexOf(BackgroundConstants.INPUT_FILE_EXTENSION) == MainConstants.ERROR_INDEX)
            {
                throw new CmdArgsException(ErrorConstants.FILE_EXTENSION_ERROR + ErrorConstants.ERROR_MESSAGE);
            }

            try {
                resultStream = new FileInputStream(fileName);
            } catch (FileNotFoundException ex)
            {
                throw new FileNotFoundException(ErrorConstants.OPEN_FILE_ERROR + fileName + ErrorConstants.ERROR_MESSAGE);
            }
        }
        else
        {
            throw new CmdArgsException(ErrorConstants.CMD_ARGS_COUNT_ERROR + ErrorConstants.ERROR_MESSAGE);
        }

        return resultStream;
    }

    public static boolean isCommand(String command)
    {
        return BackgroundConstants.COMMANDS.containsKey(command);
    }

    public static boolean correctArguments(String[] parsed_command)
    {
        return parsed_command.length == BackgroundConstants.COMMANDS.get(parsed_command[MainConstants.START_INDEX]);
    }

    public static boolean isVariableNameCorrect(String varName)
    {
        Pattern pattern = Pattern.compile(BackgroundConstants.VARIABLE_NAME_REGEX);
        Matcher matcher = pattern.matcher(varName.substring(1));

        return !(SourceVersion.isKeyword(varName) || varName.substring(0, 1).matches(BackgroundConstants.FIRST_CHARACTER_REGEX)
                || matcher.find());
    }

    public static boolean isVariable(String varName)
    {
        try {
            Double.parseDouble(varName);
            return false;
        } catch (NumberFormatException ex)
        {
            return true;
        }
    }
}
