package ru.nsu.ccfit.shishmakov.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import ru.nsu.ccfit.shishmakov.context.Context;
import ru.nsu.ccfit.shishmakov.worker.AdditionWorker;

import java.util.EmptyStackException;
import java.util.Random;

/**
 * The first word in tests is the short name of worker(Addition - add, Subtraction - sub).
 * All workers are located in the package "ru.nsu.ccfit.shishmakov.worker"
 */

public class AddWorkerTester {
    @Test
    public void addSimpleTest() throws Exception
    {
        AdditionWorker worker = new AdditionWorker();
        Context context = new Context();
        Random random = new Random();

        double firstRandValue = random.nextDouble(100, 300);
        double secondRandValue = random.nextDouble(50, 200);
        double sumResult = firstRandValue + secondRandValue;

        context.setVariable("a", Double.toString(firstRandValue), false);
        context.setVariable("b", Double.toString(secondRandValue), false);
        context.push("a");
        context.push("b");

        worker.work(null, context);

        Assertions.assertEquals(sumResult, context.peek(), 0.0000000000000000000001);
    }

    @Test
    public void addEmptyStackExceptionTest()
    {
        AdditionWorker worker = new AdditionWorker();
        Context context = new Context();

        Assertions.assertThrows(EmptyStackException.class, ()->{
            worker.work(null, context);
        });
    }
}
