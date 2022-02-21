package ru.nsu.ccfit.shishmakov.worker;
import ru.nsu.ccfit.shishmakov.context.Context;
import ru.nsu.ccfit.shishmakov.utils.CONSTANTS;

import java.util.logging.Logger;

public class AdditionWorker implements Worker {
    /**
     * AdditionWorker pops two numbers from stack which is located in context and sums them.
     * The result number is pushed to stack
     */

    @Override
    public void work(String[] commandArgs, Context context)
    {
        logger.info(className + CONSTANTS.START_LOGGER);

        double firstValue = context.pop();
        double secondValue = context.pop();

        double additionResult = firstValue + secondValue;
        context.push(additionResult);

        logger.info(className + CONSTANTS.END_LOGGER);
    }

    private static final String className = AdditionWorker.class.getName();
    private static final Logger logger = Logger.getLogger(className);
}
