package ru.nsu.ccfit.shishmakov.minesweeper.utils.background;

import javafx.util.Pair;
import ru.nsu.ccfit.shishmakov.minesweeper.utils.constants.FieldConstants;
import ru.nsu.ccfit.shishmakov.minesweeper.utils.constants.StatisticConstants;

import java.util.ArrayList;
import java.util.Scanner;

public final class Background {
    private Background() {}

    public static int getRandomIndex(int max)
    {
        return (int)Math.floor(Math.random() * (max + 1));
    }

    public static boolean indicesCorrect(int xIdx, int yIdx, int fieldWidth, int fieldHeight)
    {
        return ((xIdx > -1 && xIdx < fieldWidth) && (yIdx > -1 && yIdx < fieldHeight));
    }

    public static ArrayList<Pair<Integer, Integer>> getPairs(int firstClickX, int firstClickY, int fieldWidth, int fieldHeight)
    {
        ArrayList<Pair<Integer, Integer>> pairs = new ArrayList<>();

        for (int x = 0; x < fieldWidth; ++x)
        {
            for (int y = 0; y < fieldHeight; ++y)
            {
                if ((x < firstClickX - 1 || x > firstClickX + 1) || (y < firstClickY - 1 || y > firstClickY + 1))
                {
                    pairs.add(new Pair<>(x, y));
                }
            }
        }

        return pairs;
    }

    public static boolean fieldParamCorrect(String strParam, int param, int fieldSquare)
    {
        int left_border = FieldConstants.FIELD_PARAMS_RANGES.get(strParam)[0];
        int right_border = (strParam.equals(FieldConstants.BOMBS) ? fieldSquare: FieldConstants.FIELD_PARAMS_RANGES.get(strParam)[1]);

        return (param >= left_border && param <= right_border);
    }

    public static int getGameParameter(String strParam, Scanner scanner, int fieldSquare)
    {
        String line;
        int param = 0;

        System.out.print("Enter field " + strParam + " or \"Default\" to set default value: ");
        while (scanner.hasNextLine())
        {
            line = scanner.nextLine();
            if (line.equals(FieldConstants.DEFAULT))
            {
                param = FieldConstants.DEFAULT_PARAMETERS.get(strParam);
                break;
            }

            try
            {
                param = Integer.parseInt(line);
            } catch (NumberFormatException ex)
            {
                System.out.println("Field " + strParam + " must be integer!");
                System.out.print("Enter field " + strParam + " or \"Default\" to set default value: ");
                continue;
            }

            if (!fieldParamCorrect(strParam, param, fieldSquare))
            {
                System.out.println("Wrong field " + strParam);
            }
            else
            {
                break;
            }

            System.out.print("Enter field " + strParam + " or \"Default\" to set default value: ");
        }

        return param;
    }

    public static String getFormattedString(String[] data)
    {
        return data[StatisticConstants.PLAYER_NUMBER_INDEX] + ")" + " " +
                data[StatisticConstants.PLAYER_NAME_INDEX] + " " +
                data[StatisticConstants.FIELD_PARAMETERS_INDEX] + " " + data[StatisticConstants.FIELD_BOMBS_NUM_INDEX];
    }
}
