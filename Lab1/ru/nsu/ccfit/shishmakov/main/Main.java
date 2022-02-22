package ru.nsu.ccfit.shishmakov.main;

import java.util.Map;

import ru.nsu.ccfit.shishmakov.additional.Background;
import ru.nsu.ccfit.shishmakov.additional.CONSTANTS;
import ru.nsu.ccfit.shishmakov.workclasses.CsvWriter;
import ru.nsu.ccfit.shishmakov.workclasses.FileReader;
import ru.nsu.ccfit.shishmakov.workclasses.StringParser;

public class Main {
    public static void main(String[] args)
    {
        try {
            Background.checkCmdArgs(args);
            FileReader reader = new FileReader(args[CONSTANTS.FIRST_CMD_ARGUMENT]);
            StringParser parser = new StringParser(CONSTANTS.REGULAR_EXPRESSION);
            Map<String, Integer> words = Background.sortMap(parser.getWords(reader));

            CsvWriter writer = new CsvWriter(args[CONSTANTS.SECOND_CMD_ARGUMENT]);
            writer.makeCaptions(CONSTANTS.FIRST_COLUMN, CONSTANTS.SECOND_COLUMN, CONSTANTS.THIRD_COLUMN);
            writer.writeStatistic(words);
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
