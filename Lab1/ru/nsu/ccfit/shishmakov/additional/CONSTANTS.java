package ru.nsu.ccfit.shishmakov.additional;

public class CONSTANTS {
    private CONSTANTS() {}

    public final static String REGULAR_EXPRESSION = "[A-Za-z0-9\\u0410-\\u042F\\u0430-\\u044F]+";
    public final static String FIRST_COLUMN = "Word";
    public final static String SECOND_COLUMN = "Frequency";
    public final static String THIRD_COLUMN = "Percentage";
    public final static String FILE_ERROR = "Can't open this file: ";
    public final static String CMD_ARGS_ERROR = "Wrong cmd arguments!!!!";
    public final static String FILE_EXTENSION_ERROR = "Wrong file extension! Must be ";
    public final static String ERROR_MESSAGE = "\n--------------ERROR--------------";
    public final static String ADDITIONAL_INFO = " File: ";
    public final static String INPUT_FILE_EXTENSION = ".txt";
    public final static String OUTPUT_FILE_EXTENSION = ".csv";
    public final static int FIRST_CMD_ARGUMENT = 0;
    public final static int SECOND_CMD_ARGUMENT = 1;
    public final static int CMD_ARGUMENTS_COUNT = 2;
    public final static int DEFAULT_WORD_COUNT = 1;
    public final static int START_VALUE = 0;
    public final static int ERROR_INDEX = -1;
    public final static String CSV_DELIMITER = ",";
    public final static String END_OF_LINE = "\n";
}
