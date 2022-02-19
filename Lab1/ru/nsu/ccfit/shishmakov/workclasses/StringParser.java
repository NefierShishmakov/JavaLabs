package ru.nsu.ccfit.shishmakov.workclasses;

import java.io.IOException;
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
        String curLine;
        while ((curLine = reader.readLine()) != null)
        {
            Matcher matcher = this.pattern.matcher(curLine);
            while (matcher.find())
            {
                String word = curLine.substring(matcher.start(), matcher.end());
                if (words.containsKey(word))
                {
                    int curValue = words.get(word);
                    words.put(word, ++curValue);
                } else {
                    words.put(word, 1);
                }
            }
        }

        return words;
    }

    private final Pattern pattern;
}
