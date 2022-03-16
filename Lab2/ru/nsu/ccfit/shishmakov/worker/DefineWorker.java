package ru.nsu.ccfit.shishmakov.worker;

import ru.nsu.ccfit.shishmakov.context.Context;
import ru.nsu.ccfit.shishmakov.exceptions.VariableExistenceException;
import ru.nsu.ccfit.shishmakov.exceptions.VariableSyntaxException;
import ru.nsu.ccfit.shishmakov.utils.background.Background;
import ru.nsu.ccfit.shishmakov.utils.constants.ErrorConstants;
import ru.nsu.ccfit.shishmakov.utils.constants.LoggersConstants;
import ru.nsu.ccfit.shishmakov.utils.constants.WorkersConstants;

import java.util.logging.Logger;

public class DefineWorker implements Worker {
    @Override
    public void work(String[] commandArgs, Context context) throws VariableSyntaxException, VariableExistenceException
    {
        logger.info(className + LoggersConstants.START_LOGGER);

        String varName = commandArgs[WorkersConstants.FIRST_COMMAND_ARG];
        if (!Background.isVariableNameCorrect(varName))
            throw new VariableSyntaxException(ErrorConstants.VARIABLE_SYNTAX_ERROR + varName + ErrorConstants.ERROR_MESSAGE);

        String secondArg = commandArgs[WorkersConstants.SECOND_COMMAND_ARG];

        context.setVariable(varName, secondArg, Background.isVariable(secondArg));

        logger.info(className + LoggersConstants.END_LOGGER);
    }

    private static final String className = DefineWorker.class.getName();
    private static final Logger logger = Logger.getLogger(className);
}
