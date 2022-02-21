package ru.nsu.ccfit.shishmakov.worker;

import ru.nsu.ccfit.shishmakov.context.Context;
import ru.nsu.ccfit.shishmakov.exceptions.DivisionByZeroException;
import ru.nsu.ccfit.shishmakov.utils.CONSTANTS;

import java.util.logging.Logger;

public class DivisionWorker implements Worker {
    @Override
    public void work(String[] commandArgs, Context context) throws DivisionByZeroException
    {
        logger.info(className + CONSTANTS.START_LOGGER);

        double firstValue = context.pop();
        double secondValue = context.pop();

        if (secondValue == CONSTANTS.ZERO_DOUBLE)
            throw new DivisionByZeroException(CONSTANTS.DIVISION_BY_ZERO_ERROR + CONSTANTS.ERROR_MESSAGE);

        double divisionResult = firstValue / secondValue;

        context.push(divisionResult);

        logger.info(className + CONSTANTS.END_LOGGER);
    }

    private static final String className = DivisionWorker.class.getName();
    private static final Logger logger = Logger.getLogger(className);
}
