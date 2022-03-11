package ru.nsu.ccfit.shishmakov.workclasses;

import ru.nsu.ccfit.shishmakov.additional.CONSTANTS;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Map;

public class StringParser {
    public StringParser(String regularExpression)
    {
        this.pattern = Pattern.compile(regularExpression, Pattern.UNICODE_CHARACTER_CLASS);
    }

    public Map<String, Integer> getWords(FileReader reader) throws IOException
    {
        Map<String, Integer> words = new HashMap<>();
        ArrayList<String> lines = reader.readFile();

        for (String curLine: lines)
        {
            Matcher matcher = this.pattern.matcher(curLine);
            while (matcher.find())
            {
                String word = curLine.substring(matcher.start(), matcher.end());
                if (words.containsKey(word))
                {
                    words.put(word, words.get(word) + CONSTANTS.INCREASER);
                } else {
                    words.put(word, CONSTANTS.DEFAULT_WORD_COUNT);
                }
            }
        }

        return words;
    }

    private final Pattern pattern;
}
