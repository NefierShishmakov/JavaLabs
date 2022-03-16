package ru.nsu.ccfit.shishmakov.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import ru.nsu.ccfit.shishmakov.context.Context;
import ru.nsu.ccfit.shishmakov.exceptions.VariableExistenceException;
import ru.nsu.ccfit.shishmakov.exceptions.VariableSyntaxException;
import ru.nsu.ccfit.shishmakov.worker.DefineWorker;

import java.util.Random;

/**
 * The first word in tests is the short name of worker(Addition - add, Subtraction - sub).
 * All workers are located in the package "ru.nsu.ccfit.shishmakov.worker"
 */

public class DefineWorkerTester {
    @Test
    public void defineFirstSimpleTest()
    {
        DefineWorker worker = new DefineWorker();
        Context context = new Context();
        Random random = new Random();
        String[] args = {"DEFINE", "a", Double.toString(random.nextDouble(300, 1000))};

        Assertions.assertDoesNotThrow(()->{
            worker.work(args, context);
        });
    }

    @Test
    public void defineSecondSimpleTest() throws Exception
    {
        DefineWorker worker = new DefineWorker();
        Context context = new Context();
        Random random = new Random();
        context.setVariable("a", Double.toString(random.nextDouble(300, 1000)), false);
        String[] args = {"DEFINE", "c", "a"};

        Assertions.assertDoesNotThrow(()->{
            worker.work(args, context);
        });
    }

    @Test
    public void defineVariableSyntaxExceptionTest()
    {
        DefineWorker worker = new DefineWorker();
        Context context = new Context();

        String[] commandArgs = {"DEFINE", "123f", "34.2"};
        Assertions.assertThrows(VariableSyntaxException.class, ()->{
            worker.work(commandArgs, context);
        });
    }

    @Test
    public void defineVariableExistenceExceptionTest()
    {
        DefineWorker worker = new DefineWorker();
        Context context = new Context();

        String[] commandArgs = {"DEFINE", "wer", "34.2fgfgf"};
        Assertions.assertThrows(VariableExistenceException.class, ()->{
            worker.work(commandArgs, context);
        });
    }
}
