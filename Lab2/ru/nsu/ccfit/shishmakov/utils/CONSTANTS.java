package ru.nsu.ccfit.shishmakov.utils;

import java.util.Map;

public class CONSTANTS {
    private CONSTANTS() {}

    public static final int FIRST_CMD_ARGUMENT = 0;
    public static final int NO_ARGS = 0;
    public static final int DEFAULT_ARGS_COUNT = 1;
    public static final int ERROR_INDEX = -1;
    public static final int START_INDEX = 0;

    public static final int FIRST_COMMAND_ARG = 1;
    public static final int SECOND_COMMAND_ARG = 2;

    public static final double ZERO_DOUBLE = 0.0;

    public static final String INPUT_FILE_EXTENSION = ".txt";

    public static final String CONFIG_FILE_PATH = "config.properties";
    
    public static final String WORD_REGEX = "\\s";

    public static final String END_OF_FILE = "EOF";

    public static final String FIRST_CHARACTER_REGEX = "[^A-Za-z$_]+";
    public static final String VARIABLE_NAME_REGEX = "[^\\w$]+";

    public static final char COMMENT_SYM = '#';

    public static final Map<String, Integer> COMMANDS = Map.of("DEFINE", 3, "PUSH", 2, "POP", 1,
            "PRINT", 1, "SQRT", 1, "+", 1, "-", 1, "*", 1, "/", 1);

    ///////////////Error messages////////////////
    public static final String ERROR_MESSAGE = ".\nError!";
    public static final String CMD_ARGS_COUNT_ERROR = "Too many args";
    public static final String FILE_EXTENSION_ERROR = "Input file has wrong extension. Must be \".txt\"";
    public static final String OPEN_FILE_ERROR = "Can't open this file: ";
    public static final String UNDEFINED_COMMAND_ERROR = "This command is undefined: ";
    public static final String WRONG_COMMAND_ARGS_ERROR = "This command has wrong args: ";
    public static final String VARIABLE_SYNTAX_ERROR = "Variable has wrong syntax: ";
    public static final String VARIABLE_DATATYPE_ERROR = "Variable has wrong datatype: ";
    public static final String VARIABLE_EXISTENCE_ERROR = "Variable doesn't exist: ";
    public static final String DIVISION_BY_ZERO_ERROR = "Division by zero";

    public static final String START_LOGGER = " started.";
    public static final String END_LOGGER = " ended.";
}
