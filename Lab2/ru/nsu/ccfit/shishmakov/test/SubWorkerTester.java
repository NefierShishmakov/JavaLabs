package ru.nsu.ccfit.shishmakov.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import ru.nsu.ccfit.shishmakov.context.Context;
import ru.nsu.ccfit.shishmakov.worker.SubtractionWorker;

import java.util.EmptyStackException;
import java.util.Random;

/**
 * The first word in tests is the short name of worker(Addition - add, Subtraction - sub).
 * All workers are located in the package "ru.nsu.ccfit.shishmakov.worker"
 */

public class SubWorkerTester {
    @Test
    public void subSimpleTest() throws Exception
    {
        SubtractionWorker worker = new SubtractionWorker();
        Context context = new Context();
        Random random = new Random();

        double firstRandValue = random.nextDouble(100,300);
        double secondRandValue = random.nextDouble(300,1000);
        double subResult = secondRandValue - firstRandValue;

        context.setVariable("a", Double.toString(firstRandValue), false);
        context.setVariable("b", Double.toString(secondRandValue), false);
        context.push("a");
        context.push("b");


        worker.work(null, context);

        Assertions.assertEquals(subResult, context.peek(), 0.0000000000000000000001);
    }

    @Test
    public void subEmptyStackExceptionTest()
    {
        SubtractionWorker worker = new SubtractionWorker();

        Context context = new Context();

        Assertions.assertThrows(EmptyStackException.class, ()->{
            worker.work(null, context);
        });
    }
}
