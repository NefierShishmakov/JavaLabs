package ru.nsu.ccfit.shishmakov.main;

import java.io.InputStream;

import ru.nsu.ccfit.shishmakov.utils.background.Background;
import ru.nsu.ccfit.shishmakov.executor.CalcExecutor;
import ru.nsu.ccfit.shishmakov.parser.InputFileParser;

public class Main {
    public static void main(String[] args) {
        try (InputStream stream = Background.cmdAnalyzer(args)) {
            InputFileParser parser = new InputFileParser(stream);
            CalcExecutor executor = new CalcExecutor(parser);
            executor.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
