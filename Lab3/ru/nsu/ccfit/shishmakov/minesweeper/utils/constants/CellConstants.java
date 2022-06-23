package ru.nsu.ccfit.shishmakov.minesweeper.utils.constants;

import java.util.Map;

public final class CellConstants {
    private CellConstants() {}

    public static final int CELL_SIZE = 40;

    public static final int ACTIVE_CELLS_VICTORY_VALUE = 0;

    public static final String DEFAULT_CELL_STYLE = "-fx-background-image: url('closed_cell.png');";

    public static final Map<Character, String>  MAP_OF_CELL_NUMBER_STYLES = Map.of('0', "-fx-background-image: url('cell_0.png');",
            '1', "-fx-background-image: url('cell_1.png');", '2', "-fx-background-image: url('cell_2.png');",
            '3', "-fx-background-image: url('cell_3.png');", '4', "-fx-background-image: url('cell_4.png');",
            '5', "-fx-background-image: url('cell_5.png');", '6', "-fx-background-image: url('cell_6.png');",
            '7', "-fx-background-image: url('cell_7.png');", '8', "-fx-background-image: url('cell_8.png');");

    public static final Map<Character, String> MAP_OF_CELL_NOT_NUMBER_STYLES = Map.of('F', "-fx-background-image: url('flag_cell.png');",
            'E', "-fx-background-image: url('flag_wrong_cell.png');", 'X', "-fx-background-image: url('cell_bomb.png');",
            '#', "-fx-background-image: url('closed_cell.png');", 'B', "-fx-background-image: url('bomb_red_cell.png')");
}
