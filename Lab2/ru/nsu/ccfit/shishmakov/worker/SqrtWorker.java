package ru.nsu.ccfit.shishmakov.worker;

import ru.nsu.ccfit.shishmakov.context.Context;
import ru.nsu.ccfit.shishmakov.utils.constants.LoggersConstants;

import java.util.logging.Logger;

public class SqrtWorker implements Worker {
    @Override
    public void work(String[] commandArgs, Context context)
    {
        logger.info(className + LoggersConstants.START_LOGGER);

        double value = context.pop();

        double sqrtResult = Math.sqrt(value);
        context.push(sqrtResult);

        logger.info(className + LoggersConstants.END_LOGGER);
    }

    private static final String className = SqrtWorker.class.getName();
    private static final Logger logger = Logger.getLogger(className);
}
