package ru.nsu.ccfit.shishmakov.minesweeper.utils.context;

import javafx.util.Pair;
import java.util.ArrayList;

public class Context {
    public Context(String playerName, ArrayList<Integer> fieldParameters)
    {
        this.playerName = playerName;
        this.fieldParameters = fieldParameters;
    }

    public Pair<String, ArrayList<Integer>> getData()
    {
        return new Pair<>(this.playerName, this.fieldParameters);
    }

    public ArrayList<Integer> getFieldParameters()
    {
        return fieldParameters;
    }

    private final String playerName;
    private final ArrayList<Integer> fieldParameters;
}
