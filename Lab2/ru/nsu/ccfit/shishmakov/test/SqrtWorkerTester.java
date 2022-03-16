package ru.nsu.ccfit.shishmakov.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import ru.nsu.ccfit.shishmakov.context.Context;
import ru.nsu.ccfit.shishmakov.worker.SqrtWorker;

import java.util.EmptyStackException;
import java.util.Random;

/**
 * The first word in tests is the short name of worker(Addition - add, Subtraction - sub).
 * All workers are located in the package "ru.nsu.ccfit.shishmakov.worker"
 */

public class SqrtWorkerTester {
    @Test
    public void sqrtSimpleTest() throws Exception
    {
        SqrtWorker worker = new SqrtWorker();
        Context context = new Context();
        Random random = new Random();

        double randValue = random.nextDouble(1000);
        double sqrtResult = Math.sqrt(randValue);

        context.setVariable("a", Double.toString(randValue), false);
        context.push("a");


        worker.work(null, context);

        Assertions.assertEquals(sqrtResult, context.peek(), 0.0000000000000000000001);
    }

    @Test
    public void sqrtEmptyStackExceptionTest()
    {
        SqrtWorker worker = new SqrtWorker();
        Context context = new Context();

        Assertions.assertThrows(EmptyStackException.class, ()->{
            worker.work(null, context);
        });
    }
}
