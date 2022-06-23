package ru.nsu.ccfit.shishmakov.minesweeper.utils.constants;

import java.util.Map;

public final class FieldConstants {
    private FieldConstants() {}

    public static final int DEFAULT_FIELD_WIDTH = 9;
    public static final int DEFAULT_FIELD_HEIGHT = 9;
    public static final int DEFAULT_BOMBS_NUM = 10;

    public static final int MAX_FIELD_WIDTH = 16;
    public static final int MAX_FIELD_HEIGHT = 16;

    public static final int START_COORDINATE = 0;

    public static final Map<String, Integer> DEFAULT_PARAMETERS = Map.of("width", DEFAULT_FIELD_WIDTH,
            "height", DEFAULT_FIELD_HEIGHT, "bombs", DEFAULT_BOMBS_NUM);

    public static final Map<String, Integer> MAX_PARAMETERS = Map.of("width", MAX_FIELD_WIDTH,
            "height", MAX_FIELD_HEIGHT);

    public static final Map<String, Integer[]> FIELD_PARAMS_RANGES = Map.of("width", new Integer[]{DEFAULT_FIELD_WIDTH, MAX_FIELD_WIDTH},
            "height", new Integer[]{DEFAULT_FIELD_HEIGHT, MAX_FIELD_HEIGHT}, "bombs", new Integer[]{DEFAULT_BOMBS_NUM});



    public static final String[] VALUES = {"X must be from ", "Y must be from "};

    public static final char CLOSED_CELL = '#';
    public static final char BOMB_CELL = 'X';
    public static final char WRONG_MARKED_CELL = 'E';
    public static final char UNSET_FLAG = 'U';
    public static final char SET_FLAG = 'F';
    public static final char REVEAL_CELL = 'R';
    public static final char BOMB_PICKED = 'B';

    public static final int FIELD_WIDTH_INDEX = 0;
    public static final int FIELD_HEIGHT_INDEX = 1;
    public static final int FIELD_BOMBS_NUM_INDEX = 2;

    public static final int NO_FIELD_SQUARE = -1;

    public static final String WIDTH = "width";
    public static final String HEIGHT = "height";
    public static final String BOMBS= "bombs";
    public static final String DEFAULT = "Default";
}
