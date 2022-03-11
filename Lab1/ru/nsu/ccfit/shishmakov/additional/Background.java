package ru.nsu.ccfit.shishmakov.additional;

import ru.nsu.ccfit.shishmakov.exceptions.CmdArgsException;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;

public class Background {
    private Background() {}

    public static Map<String, Integer> sortMap(Map<String, Integer> words)
    {
        List<Map.Entry<String, Integer>> list = new ArrayList<>(words.entrySet());
        list.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        Map<String, Integer> resultMap = new LinkedHashMap<>();

        for (Map.Entry<String, Integer> entry: list)
        {
            resultMap.put(entry.getKey(), entry.getValue());
        }

        return resultMap;
    }

    public static int countWords(Map<String, Integer> words)
    {
        return words.values().stream().reduce(CONSTANTS.START_VALUE, Integer::sum);
    }

    public static double getWordPercentage(int wordFrequency, int wordsCount)
    {
        return ((double)wordFrequency / wordsCount) * 100.0;
    }

    public static String getOutputLine(String word, int freq, double percentage, String delimiter)
    {
        String[] data = new String[] {word, delimiter, Integer.toString(freq), delimiter,
                Double.toString(percentage), CONSTANTS.END_OF_LINE};
        StringBuilder outputLine = new StringBuilder();

        for (String line: data)
        {
            outputLine.append(line);
        }

        return outputLine.toString();
    }

    public static void checkCmdArgs(String[] args) throws CmdArgsException
    {
        if (args.length != CONSTANTS.CMD_ARGUMENTS_COUNT)
        {
            throw new CmdArgsException(CONSTANTS.ERROR_MESSAGE + CONSTANTS.CMD_ARGS_ERROR);
        }

        String inputFile = args[CONSTANTS.FIRST_CMD_ARGUMENT];
        if (inputFile.lastIndexOf(CONSTANTS.INPUT_FILE_EXTENSION) == CONSTANTS.ERROR_INDEX)
        {
            throw new CmdArgsException(CONSTANTS.ERROR_MESSAGE + CONSTANTS.FILE_EXTENSION_ERROR +
                    CONSTANTS.INPUT_FILE_EXTENSION + CONSTANTS.ADDITIONAL_INFO + inputFile);
        }

        String outputFile = args[CONSTANTS.SECOND_CMD_ARGUMENT];
        if (outputFile.lastIndexOf(CONSTANTS.OUTPUT_FILE_EXTENSION) == CONSTANTS.ERROR_INDEX)
        {
            throw new CmdArgsException(CONSTANTS.ERROR_MESSAGE + CONSTANTS.FILE_EXTENSION_ERROR +
                    CONSTANTS.OUTPUT_FILE_EXTENSION + CONSTANTS.ADDITIONAL_INFO + outputFile);
        }
    }
}
