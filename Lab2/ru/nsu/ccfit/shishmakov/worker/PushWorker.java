package ru.nsu.ccfit.shishmakov.worker;

import ru.nsu.ccfit.shishmakov.context.Context;
import ru.nsu.ccfit.shishmakov.exceptions.VariableExistenceException;
import ru.nsu.ccfit.shishmakov.utils.CONSTANTS;

import java.util.logging.Logger;

public class PushWorker implements Worker {
    @Override
    public void work(String[] commandArgs, Context context) throws VariableExistenceException
    {
        logger.info(className + CONSTANTS.START_LOGGER);

        String varName = commandArgs[CONSTANTS.FIRST_COMMAND_ARG];
        if (!context.isVariableExists(varName))
        {
            throw new VariableExistenceException(CONSTANTS.VARIABLE_EXISTENCE_ERROR + varName
                    + CONSTANTS.ERROR_MESSAGE);
        }

        context.push(varName);

        logger.info(className + CONSTANTS.END_LOGGER);
    }

    private static final String className = PushWorker.class.getName();
    private static final Logger logger = Logger.getLogger(className);
}
