package ru.nsu.ccfit.shishmakov.worker;

import ru.nsu.ccfit.shishmakov.context.Context;
import ru.nsu.ccfit.shishmakov.utils.CONSTANTS;

import java.util.EmptyStackException;
import java.util.logging.Logger;

public class MultiplicationWorker implements Worker {
    @Override
    public void work(String[] commandArgs, Context context)
    {
        logger.info(className + CONSTANTS.START_LOGGER);

        double firstValue;
        double secondValue;

        try {
            firstValue = context.pop();
            secondValue = context.pop();
        } catch (EmptyStackException ex)
        {
            throw new EmptyStackException();
        }

        double multiplicationResult = firstValue * secondValue;
        context.push(multiplicationResult);

        logger.info(className + CONSTANTS.END_LOGGER);
    }

    private static final String className = MultiplicationWorker.class.getName();
    private static final Logger logger = Logger.getLogger(className);
}
