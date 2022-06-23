package ru.nsu.ccfit.shishmakov.minesweeper.game.gui.statistic;

import javafx.util.Pair;
import ru.nsu.ccfit.shishmakov.minesweeper.utils.constants.FieldConstants;
import ru.nsu.ccfit.shishmakov.minesweeper.utils.constants.StatisticConstants;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Statistic
{
    private static final String DEFAULT_PATH_NAME = "src/main/resources/statistic.csv";
    private static final String SPLIT_REGEX = ";";
    private static final String FIELD_PARAMETERS_DIVIDER = "X";
    private static final String BOMBS_DIVIDER = " ";
    private static final int SIZE_OF_ARR = 4;

    public void addNewPlayer(Pair<String, ArrayList<Integer>> data)
    {
        if (this.listOfPlayers.isEmpty())
        {
            this.listOfPlayers = this.getListOfPlayersFromFile();
        }

        boolean writeToFile = false;

        Pair<Integer, String[]> playerInList = this.getPlayerInList(data.getKey(), this.listOfPlayers);

        if (playerInList != null)
        {
            if (this.isNewStatisticBetter(playerInList.getValue(), data.getValue()))
            {
                writeToFile = true;
                this.listOfPlayers.remove(playerInList.getKey().intValue());
            }
        }
        else
        {
            writeToFile = true;
        }

        if (writeToFile)
        {
            String[] newPlayer = this.getNewPlayerStringArr(data, this.listOfPlayers.size() + 1);

            this.listOfPlayers.add(newPlayer);

            try (FileWriter writer = new FileWriter(DEFAULT_PATH_NAME))
            {
                for (String[] player: this.listOfPlayers)
                {
                    String line = this.getCsvFormatString(player);
                    System.out.println(line);
                    writer.write(line);
                }

                writer.flush();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<String[]> getListOfPlayersFromFile()
    {
        if (!this.listOfPlayers.isEmpty())
        {
            return this.listOfPlayers;
        }

        ArrayList<String[]> newListOfPlayers = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(DEFAULT_PATH_NAME)))
        {
            String line = "";

            while ((line = reader.readLine()) != null)
            {
                if (!line.isEmpty())
                {
                    newListOfPlayers.add(line.split(SPLIT_REGEX));
                }
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return newListOfPlayers;
    }

    private Pair<Integer, String[]> getPlayerInList(String newPlayer, ArrayList<String[]> listOfPlayers)
    {
        for (int i = 0; i < listOfPlayers.size(); ++i)
        {
            if (newPlayer.equals(listOfPlayers.get(i)[StatisticConstants.PLAYER_NAME_INDEX]))
            {
                return new Pair<>(i, listOfPlayers.get(i));
            }
        }

        return null;
    }

    private String[] getNewPlayerStringArr(Pair<String, ArrayList<Integer>> data, int numberInList)
    {
        String[] arr = new String[SIZE_OF_ARR];

        arr[StatisticConstants.PLAYER_NUMBER_INDEX] = Integer.toString(numberInList);
        arr[StatisticConstants.PLAYER_NAME_INDEX] = data.getKey();
        arr[StatisticConstants.FIELD_PARAMETERS_INDEX] =
                data.getValue().get(FieldConstants.FIELD_WIDTH_INDEX) + FIELD_PARAMETERS_DIVIDER +
                data.getValue().get(FieldConstants.FIELD_HEIGHT_INDEX);
        arr[StatisticConstants.FIELD_BOMBS_NUM_INDEX] = data.getValue().get(FieldConstants.FIELD_BOMBS_NUM_INDEX) + " mines";

        return arr;
    }

    private String getCsvFormatString(String[] player)
    {
        String[] data = new String[] {player[StatisticConstants.PLAYER_NUMBER_INDEX], SPLIT_REGEX,
                player[StatisticConstants.PLAYER_NAME_INDEX], SPLIT_REGEX, player[StatisticConstants.FIELD_PARAMETERS_INDEX],
                SPLIT_REGEX, player[StatisticConstants.FIELD_BOMBS_NUM_INDEX]};

        StringBuilder builder = new StringBuilder();

        for (String line: data)
        {
            builder.append(line);
        }

        builder.append("\n");

        return builder.toString();
    }

    private boolean isNewStatisticBetter(String[] oldData, ArrayList<Integer> newData)
    {
        int[] fieldParameters = Stream.of(oldData[StatisticConstants.FIELD_PARAMETERS_INDEX].split(FIELD_PARAMETERS_DIVIDER))
                .mapToInt(Integer::parseInt).toArray();

        int oldMinesNum = Integer.parseInt(oldData[StatisticConstants.FIELD_BOMBS_NUM_INDEX].split(BOMBS_DIVIDER)[0]);

        return (newData.get(FieldConstants.FIELD_BOMBS_NUM_INDEX) > oldMinesNum ||
                newData.get(FieldConstants.FIELD_WIDTH_INDEX) * newData.get(FieldConstants.FIELD_HEIGHT_INDEX) >
                        fieldParameters[FieldConstants.FIELD_WIDTH_INDEX] * fieldParameters[FieldConstants.FIELD_HEIGHT_INDEX]);
    }

    private ArrayList<String[]> listOfPlayers = new ArrayList<>();
}
