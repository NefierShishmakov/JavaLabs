package ru.nsu.ccfit.shishmakov.worker;

import ru.nsu.ccfit.shishmakov.context.Context;
import ru.nsu.ccfit.shishmakov.exceptions.DivisionByZeroException;
import ru.nsu.ccfit.shishmakov.exceptions.VariableException;

public interface Worker {
    void work(String[] commandArgs, Context context) throws VariableException, DivisionByZeroException;
}
