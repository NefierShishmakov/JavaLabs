package ru.nsu.ccfit.shishmakov.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import ru.nsu.ccfit.shishmakov.context.Context;
import ru.nsu.ccfit.shishmakov.worker.PrintWorker;

import java.util.EmptyStackException;
import java.util.Random;

/**
 * The first word in tests is the short name of worker(Addition - add, Subtraction - sub).
 * All workers are located in the package "ru.nsu.ccfit.shishmakov.worker"
 */

public class PrintWorkerTester {
    @Test
    public void printSimpleTest() throws Exception
    {
        PrintWorker worker = new PrintWorker();
        Context context = new Context();
        Random random = new Random();
        context.setVariable("a", Double.toString(random.nextDouble(300, 1000)), false);
        context.push("a");

        Assertions.assertDoesNotThrow(()->{
            worker.work(null, context);
        });
    }

    @Test
    public void printEmptyStackExceptionTest()
    {
        PrintWorker worker = new PrintWorker();
        Context context = new Context();

        Assertions.assertThrows(EmptyStackException.class, ()->{
            worker.work(null, context);
        });
    }
}
