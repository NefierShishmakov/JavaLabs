package ru.nsu.ccfit.shishmakov.executor;

import ru.nsu.ccfit.shishmakov.exceptions.CommandException;
import ru.nsu.ccfit.shishmakov.utils.CONSTANTS;
import ru.nsu.ccfit.shishmakov.factory.Factory;
import ru.nsu.ccfit.shishmakov.parser.InputFileParser;
import ru.nsu.ccfit.shishmakov.context.Context;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CalcExecutor {
    public CalcExecutor(InputFileParser parser, Scanner scanner) throws CommandException, IOException {
        this.commandsList = parser.getCommandsList(scanner);
        this.factory = new Factory();
        this.context = new Context();
    }

    public void execute()  {
        for (String[] list: this.commandsList)
        {
            try {
                this.factory.getWorker(list[CONSTANTS.START_INDEX]).work(list, this.context);
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }
//VariableException | DivisionByZeroException | EmptyStackException | NumberFormatException | ReflectiveOperationException
    private final Factory factory;
    private final ArrayList<String[]> commandsList;
    private final Context context;
}
