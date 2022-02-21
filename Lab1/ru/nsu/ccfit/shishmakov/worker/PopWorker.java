package ru.nsu.ccfit.shishmakov.worker;

import ru.nsu.ccfit.shishmakov.context.Context;
import ru.nsu.ccfit.shishmakov.utils.CONSTANTS;

import java.util.logging.Logger;

public class PopWorker implements Worker {
    @Override
    public void work(String[] commandArgs, Context context)
    {
        logger.info(className + CONSTANTS.START_LOGGER);

        context.pop();

        logger.info(className + CONSTANTS.END_LOGGER);
    }

    private static final String className = PopWorker.class.getName();
    private static final Logger logger = Logger.getLogger(className);
}
