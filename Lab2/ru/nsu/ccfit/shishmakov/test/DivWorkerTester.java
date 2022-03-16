package ru.nsu.ccfit.shishmakov.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import ru.nsu.ccfit.shishmakov.context.Context;
import ru.nsu.ccfit.shishmakov.exceptions.DivisionByZeroException;
import ru.nsu.ccfit.shishmakov.worker.DivisionWorker;

import java.util.EmptyStackException;
import java.util.Random;

/**
 * The first word in tests is the short name of worker(Addition - add, Subtraction - sub).
 * All workers are located in the package "ru.nsu.ccfit.shishmakov.worker"
 */

public class DivWorkerTester {
    @Test
    public void divSimpleTest() throws Exception
    {
        DivisionWorker worker = new DivisionWorker();
        Context context = new Context();
        Random random = new Random();

        double firstRandValue = random.nextDouble(100,300);
        double secondRandValue = random.nextDouble(300,1000);
        double divResult = secondRandValue / firstRandValue;

        context.setVariable("a", Double.toString(firstRandValue), false);
        context.setVariable("b", Double.toString(secondRandValue), false);
        context.push("a");
        context.push("b");


        worker.work(null, context);

        Assertions.assertEquals(divResult, context.peek(), 0.0000000000000000000001);
    }

    @Test
    public void divEmptyStackExceptionTest()
    {
        DivisionWorker worker = new DivisionWorker();

        Context context = new Context();

        Assertions.assertThrows(EmptyStackException.class, ()->{
            worker.work(null, context);
        });
    }

    @Test
    public void divDivisionByZeroExceptionTest() throws Exception
    {
        DivisionWorker worker = new DivisionWorker();
        Context context = new Context();

        Random random = new Random();

        context.setVariable("a", Double.toString(0.0), false);
        context.setVariable("b", Double.toString(random.nextDouble(100, 1000)), false);

        context.push("a");
        context.push("b");

        Assertions.assertThrows(DivisionByZeroException.class, ()->{
            worker.work(null, context);
        });
    }
}
