package ru.nsu.ccfit.shishmakov.minesweeper.utils.statisticbox;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import ru.nsu.ccfit.shishmakov.minesweeper.game.gui.statistic.Statistic;
import ru.nsu.ccfit.shishmakov.minesweeper.utils.background.Background;

import java.util.ArrayList;

public class StatisticBox extends VBox {
    private static final int X_POS = 700;
    private static final int Y_POS = 100;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;

    private static final String CAPTION = "Top Players";

    private static final String CAPTION_STYLE = "-fx-font-size: 40; -fx-text-fill: #E30613; -fx-font-weight: bold;";
    private static final String STATISTIC_BOX_STYLE = "-fx-background-image: url('statistic_box_background.png')";
    private static final String PLAYERS_STYLE = "-fx-font-size: 20; -fx-font-weight: bold;";

    private static final int DEFAULT_SPACING = 15;

    public StatisticBox(Statistic statistic)
    {
        ArrayList<String[]> listOfPlayers = statistic.getListOfPlayersFromFile();

        this.setLayoutX(X_POS);
        this.setLayoutY(Y_POS);
        this.setPrefSize(WIDTH, HEIGHT);
        this.setSpacing(DEFAULT_SPACING);
        this.setStyle(STATISTIC_BOX_STYLE);

        Label caption = new Label(CAPTION);
        caption.setStyle(CAPTION_STYLE);
        caption.setAlignment(Pos.BASELINE_CENTER);

        Label[] players = this.getPlayers(listOfPlayers);

        this.getChildren().add(caption);
        this.getChildren().addAll(players);
    }

    private Label[] getPlayers(ArrayList<String[]> listOfPlayers)
    {
        Label[] players = new Label[listOfPlayers.size()];

        for (int i = 0; i < players.length; ++i)
        {
            players[i] = new Label(Background.getFormattedString(listOfPlayers.get(i)));
            players[i].setStyle(PLAYERS_STYLE);
        }

        return players;
    }
}
