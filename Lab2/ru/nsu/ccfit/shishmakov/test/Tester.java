package ru.nsu.ccfit.shishmakov.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;


import ru.nsu.ccfit.shishmakov.context.Context;
import ru.nsu.ccfit.shishmakov.exceptions.DivisionByZeroException;
import ru.nsu.ccfit.shishmakov.exceptions.VariableExistenceException;
import ru.nsu.ccfit.shishmakov.exceptions.VariableSyntaxException;
import ru.nsu.ccfit.shishmakov.worker.*;

import java.util.EmptyStackException;
import java.util.Random;

public class Tester {
    // @Before is needed to set something before running methods that have @Test tag
    // @After is needed to set something after running methods that have @Test tag

    /**
     * The first word in tests is the short name of worker(Addition - add, Subtraction - sub).
     * All workers are located in the package "ru.nsu.ccfit.shishmakov.worker"
     */

    /**
     * Simple tests that demonstrate the work of all blocks(workers) in calculator.
     */
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
    public void mulSimpleTest() throws Exception
    {
        MultiplicationWorker worker = new MultiplicationWorker();
        Context context = new Context();
        Random random = new Random();

        double firstRandValue = random.nextDouble(100,300);
        double secondRandValue = random.nextDouble(300,1000);
        double mulResult = secondRandValue * firstRandValue;

        context.setVariable("a", Double.toString(firstRandValue), false);
        context.setVariable("b", Double.toString(secondRandValue), false);
        context.push("a");
        context.push("b");


        worker.work(null, context);

        Assertions.assertEquals(mulResult, context.peek(), 0.0000000000000000000001);
    }

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
    public void popSimpleTest() throws Exception
    {
        PopWorker worker = new PopWorker();
        Context context = new Context();
        Random random = new Random();
        context.setVariable("a", Double.toString(random.nextDouble(300, 1000)), false);
        context.push("a");

        Assertions.assertDoesNotThrow(()->{
            worker.work(null, context);
        });
    }

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

    /**
     * Testing all methods that can throw exception.
     */
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

    @Test
    public void printEmptyStackExceptionTest()
    {
        PrintWorker worker = new PrintWorker();
        Context context = new Context();

        Assertions.assertThrows(EmptyStackException.class, ()->{
            worker.work(null, context);
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

    @Test
    public void popEmptyStackExceptionTest()
    {
        PopWorker worker = new PopWorker();
        Context context = new Context();

        Assertions.assertThrows(EmptyStackException.class, ()->{
            worker.work(null, context);
        });
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

    @Test
    public void subEmptyStackExceptionTest()
    {
        SubtractionWorker worker = new SubtractionWorker();

        Context context = new Context();

        Assertions.assertThrows(EmptyStackException.class, ()->{
            worker.work(null, context);
        });
    }

    @Test
    public void mulEmptyStackExceptionTest()
    {
        MultiplicationWorker worker = new MultiplicationWorker();

        Context context = new Context();

        Assertions.assertThrows(EmptyStackException.class, ()->{
            worker.work(null, context);
        });
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
