package ru.nsu.ccfit.shishmakov.minesweeper.command;

import ru.nsu.ccfit.shishmakov.minesweeper.game.gui.statistic.Statistic;
import ru.nsu.ccfit.shishmakov.minesweeper.utils.background.Background;

import java.io.IOException;
import java.util.ArrayList;


public class HighScoresCommand implements Command {
    public HighScoresCommand()
    {
        this.statistic = new Statistic();
    }

    @Override
    public void execute() throws IOException {
        ArrayList<String[]> listOfPlayers = this.statistic.getListOfPlayersFromFile();

        for (String[] player: listOfPlayers)
        {
            System.out.println(Background.getFormattedString(player));
        }
    }

    private final Statistic statistic;
}
