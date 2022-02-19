package ru.nsu.ccfit.shishmakov.workclasses;

import ru.nsu.ccfit.shishmakov.additional.Background;
import ru.nsu.ccfit.shishmakov.additional.CONSTANTS;

import java.io.IOException;
import java.util.Map;
import java.io.FileWriter;

public class CsvWriter {
    public CsvWriter(String fileName) throws IOException
    {
        this.outFileWriter = new FileWriter(fileName);
    }

    public void makeCaptions(String firstColumn, String secondColumn, String thirdColumn) throws IOException
    {
        String[] data = new String[] {firstColumn, this.delimiter, secondColumn, this.delimiter,
                thirdColumn, CONSTANTS.END_OF_LINE};
        StringBuilder outputLine = new StringBuilder();
        for (String line: data)
        {
            outputLine.append(line);
        }

        this.outFileWriter.write(outputLine.toString());
    }

    public void writeStatistic(Map<String, Integer> words) throws IOException
    {
        final int wordsCount = Background.countWords(words);
        for (Map.Entry<String, Integer> pair: words.entrySet())
        {
            double percentage = Background.getWordPercentage(pair.getValue(), wordsCount);
            this.outFileWriter.write(Background.getOutputLine(pair.getKey(), pair.getValue(),
                    percentage, this.delimiter));
        }

        this.outFileWriter.close();
    }

    private final String delimiter = CONSTANTS.CSV_DELIMITER;
    private final FileWriter outFileWriter;
}
