package ru.nsu.ccfit.shishmakov.worker;

import ru.nsu.ccfit.shishmakov.context.Context;
import ru.nsu.ccfit.shishmakov.exceptions.VariableExistenceException;
import ru.nsu.ccfit.shishmakov.utils.constants.ErrorConstants;
import ru.nsu.ccfit.shishmakov.utils.constants.LoggersConstants;
import ru.nsu.ccfit.shishmakov.utils.constants.WorkersConstants;

import java.util.logging.Logger;

public class PushWorker implements Worker {
    @Override
    public void work(String[] commandArgs, Context context) throws VariableExistenceException
    {
        logger.info(className + LoggersConstants.START_LOGGER);

        String varName = commandArgs[WorkersConstants.FIRST_COMMAND_ARG];
        if (!context.isVariableExists(varName))
        {
            throw new VariableExistenceException(ErrorConstants.VARIABLE_EXISTENCE_ERROR + varName
                    + ErrorConstants.ERROR_MESSAGE);
        }

        context.push(varName);

        logger.info(className + LoggersConstants.END_LOGGER);
    }

    private static final String className = PushWorker.class.getName();
    private static final Logger logger = Logger.getLogger(className);
}
