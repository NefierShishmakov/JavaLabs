package ru.nsu.ccfit.shishmakov.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import ru.nsu.ccfit.shishmakov.context.Context;
import ru.nsu.ccfit.shishmakov.exceptions.VariableExistenceException;
import ru.nsu.ccfit.shishmakov.worker.PushWorker;

import java.util.Random;

/**
 * The first word in tests is the short name of worker(Addition - add, Subtraction - sub).
 * All workers are located in the package "ru.nsu.ccfit.shishmakov.worker"
 */

public class PushWorkerTester {
    @Test
    public void pushSimpleTest() throws Exception
    {
        PushWorker worker = new PushWorker();
        Context context = new Context();
        Random random = new Random();
        context.setVariable("a", Double.toString(random.nextDouble(300, 1000)), false);
        String[] commandArgs = {"PUSH", "a"};

        Assertions.assertDoesNotThrow(()->{
            worker.work(commandArgs, context);
        });
    }

    @Test
    public void pushVariableExistenceExceptionTest()
    {
        PushWorker worker = new PushWorker();
        Context context = new Context();

        String[] commandArgs = {"PUSH", "lalala"};
        Assertions.assertThrows(VariableExistenceException.class, ()->{
            worker.work(commandArgs, context);
        });
    }
}
