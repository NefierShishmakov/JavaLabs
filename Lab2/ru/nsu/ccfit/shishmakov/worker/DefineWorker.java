package ru.nsu.ccfit.shishmakov.worker;

import ru.nsu.ccfit.shishmakov.context.Context;
import ru.nsu.ccfit.shishmakov.exceptions.VariableSyntaxException;
import ru.nsu.ccfit.shishmakov.utils.Background;
import ru.nsu.ccfit.shishmakov.utils.CONSTANTS;

import java.util.logging.Logger;

public class DefineWorker implements Worker {
    @Override
    public void work(String[] commandArgs, Context context) throws VariableSyntaxException
    {
        logger.info(className + CONSTANTS.START_LOGGER);

        String varName = commandArgs[CONSTANTS.FIRST_COMMAND_ARG];
        if (!Background.isVariableNameCorrect(varName))
            throw new VariableSyntaxException(CONSTANTS.VARIABLE_SYNTAX_ERROR + varName + CONSTANTS.ERROR_MESSAGE);

        String strVarValue = commandArgs[CONSTANTS.SECOND_COMMAND_ARG];
        double varValue;

        try {
            varValue = Double.parseDouble(strVarValue);
        } catch (NumberFormatException ex)
        {
            throw new NumberFormatException(CONSTANTS.VARIABLE_DATATYPE_ERROR + strVarValue + CONSTANTS.ERROR_MESSAGE);
        }

        context.setVariable(varName, varValue);

        logger.info(className + CONSTANTS.END_LOGGER);
    }

    private static final String className = DefineWorker.class.getName();
    private static final Logger logger = Logger.getLogger(className);
}
