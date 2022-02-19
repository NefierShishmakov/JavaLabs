package ru.nsu.ccfit.shishmakov.worker;

import ru.nsu.ccfit.shishmakov.context.Context;
import ru.nsu.ccfit.shishmakov.utils.CONSTANTS;

import java.util.EmptyStackException;
import java.util.logging.Logger;

public class SqrtWorker implements Worker {
    @Override
    public void work(String[] commandArgs, Context context)
    {
        logger.info(className + CONSTANTS.START_LOGGER);

        double value;

        try {
            value = context.pop();
        } catch (EmptyStackException ex)
        {
            throw new EmptyStackException();
        }

        double sqrtResult = Math.sqrt(value);
        context.push(sqrtResult);

        logger.info(className + CONSTANTS.END_LOGGER);
    }

    private static final String className = SqrtWorker.class.getName();
    private static final Logger logger = Logger.getLogger(className);
}
