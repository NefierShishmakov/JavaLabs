package ru.nsu.ccfit.shishmakov.worker;

import ru.nsu.ccfit.shishmakov.context.Context;
import ru.nsu.ccfit.shishmakov.exceptions.DivisionByZeroException;
import ru.nsu.ccfit.shishmakov.utils.constants.ErrorConstants;
import ru.nsu.ccfit.shishmakov.utils.constants.LoggersConstants;

import java.util.logging.Logger;

public class DivisionWorker implements Worker {
    private static final double ZERO_DOUBLE = 0.0;

    @Override
    public void work(String[] commandArgs, Context context) throws DivisionByZeroException
    {
        logger.info(className + LoggersConstants.START_LOGGER);

        double firstValue = context.pop();
        double secondValue = context.pop();

        if (secondValue == ZERO_DOUBLE)
            throw new DivisionByZeroException(ErrorConstants.DIVISION_BY_ZERO_ERROR + ErrorConstants.ERROR_MESSAGE);

        double divisionResult = firstValue / secondValue;

        context.push(divisionResult);

        logger.info(className + LoggersConstants.END_LOGGER);
    }

    private static final String className = DivisionWorker.class.getName();
    private static final Logger logger = Logger.getLogger(className);
}
