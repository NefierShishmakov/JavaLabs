package ru.nsu.ccfit.shishmakov.main;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import ru.nsu.ccfit.shishmakov.utils.Background;
import ru.nsu.ccfit.shishmakov.executor.CalcExecutor;
import ru.nsu.ccfit.shishmakov.parser.InputFileParser;

public class Main {
    public static void main(String[] args)
    {
        InputStream stream = null;
        Scanner scanner = null;
        try
        {
            stream = Background.cmdAnalyzer(args);
            scanner = new Scanner(stream);
            InputFileParser parser = new InputFileParser(stream);
            CalcExecutor executor = new CalcExecutor(parser, scanner);
            executor.execute();

        } catch (Exception ex)
        {
            ex.printStackTrace();
        } finally
        {
            if (scanner != null)
            {
                scanner.close();
            }
            if (stream != null)
            {
                try
                {
                    stream.close();
                } catch (IOException ex)
                {
                    System.err.println(ex.getMessage());
                }
            }
        }
    }
}
