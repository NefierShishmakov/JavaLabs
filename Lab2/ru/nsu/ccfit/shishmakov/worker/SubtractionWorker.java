package ru.nsu.ccfit.shishmakov.worker;

import ru.nsu.ccfit.shishmakov.context.Context;
import ru.nsu.ccfit.shishmakov.utils.constants.LoggersConstants;

import java.util.logging.Logger;

public class SubtractionWorker implements Worker {
    @Override
    public void work(String[] commandArgs, Context context)
    {
        logger.info(className + LoggersConstants.START_LOGGER);

        double firstValue = context.pop();
        double secondValue = context.pop();

        double subtractionResult = firstValue - secondValue;
        context.push(subtractionResult);

        logger.info(className + LoggersConstants.END_LOGGER);
    }

    private static final String className = SubtractionWorker.class.getName();
    private static final Logger logger = Logger.getLogger(className);
}
