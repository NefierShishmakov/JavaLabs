package ru.nsu.ccfit.shishmakov.utils.constants;

import java.util.Map;

public final class BackgroundConstants {
    private BackgroundConstants() {}

    public static final int FIRST_CMD_ARGUMENT = 0;
    public static final int NO_ARGS = 0;
    public static final int DEFAULT_ARGS_COUNT = 1;
    public static final String FIRST_CHARACTER_REGEX = "[^A-Za-z$_]+";
    public static final String VARIABLE_NAME_REGEX = "[^\\w$]+";
    public static final String INPUT_FILE_EXTENSION = ".txt";
    public static final Map<String, Integer> COMMANDS = Map.of("DEFINE", 3, "PUSH", 2, "POP", 1,
            "PRINT", 1, "SQRT", 1, "+", 1, "-", 1, "*", 1, "/", 1);
}
