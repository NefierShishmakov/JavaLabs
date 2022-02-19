package ru.nsu.ccfit.shishmakov.tests;

/*Test suite is used to bundle a few unit test cases and run them together*/
import junit.framework.TestSuite;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ru.nsu.ccfit.shishmakov.exceptions.DivisionByZeroException;
import ru.nsu.ccfit.shishmakov.exceptions.VariableExistenceException;
import ru.nsu.ccfit.shishmakov.exceptions.VariableSyntaxException;
import ru.nsu.ccfit.shishmakov.worker.*;
import ru.nsu.ccfit.shishmakov.context.Context;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.EmptyStackException;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class Tester extends TestSuite {
    // These variables needed to test printSimple method!!!!
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;


    // @Before is needed to set something before running methods that have @Test tag
    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    // @After is needed to set something after running methods that have @Test tag
    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    // Methods that marked @test are our tests.
    @Test
    public void addSimple()
    {
        AdditionWorker worker = new AdditionWorker();
        Context context = new Context();
        Random random = new Random();

        double firstRandValue = random.nextDouble() * random.nextInt(100, 300);
        double secondRandValue = random.nextDouble() * random.nextInt(50,200);
        double sumResult = firstRandValue + secondRandValue;

        context.setVariable("a", firstRandValue);
        context.setVariable("b", secondRandValue);
        context.push("a");
        context.push("b");
        worker.work(null, context);

        assertEquals(sumResult, context.peek(), 0.0000000000000000000001);
    }

    @Test
    public void subSimple()
    {
        SubtractionWorker worker = new SubtractionWorker();
        Context context = new Context();
        Random random = new Random();

        double firstRandValue = random.nextDouble(100,300);
        double secondRandValue = random.nextDouble(300,1000);
        double subResult = secondRandValue - firstRandValue;

        context.setVariable("a", firstRandValue);
        context.setVariable("b", secondRandValue);
        context.push("a");
        context.push("b");
        worker.work(null, context);

        assertEquals(subResult, context.peek(), 0.0000000000000000000001);
    }

    @Test
    public void mulSimple()
    {
        MultiplicationWorker worker = new MultiplicationWorker();
        Context context = new Context();
        Random random = new Random();

        double firstRandValue = random.nextDouble(100,300);
        double secondRandValue = random.nextDouble(300,1000);
        double mulResult = secondRandValue * firstRandValue;

        context.setVariable("a", firstRandValue);
        context.setVariable("b", secondRandValue);
        context.push("a");
        context.push("b");
        worker.work(null, context);

        assertEquals(mulResult, context.peek(), 0.0000000000000000000001);
    }

    @Test
    public void divSimple() throws Exception
    {
        DivisionWorker worker = new DivisionWorker();
        Context context = new Context();
        Random random = new Random();

        double firstRandValue = random.nextDouble(100,300);
        double secondRandValue = random.nextDouble(300,1000);
        double divResult = secondRandValue / firstRandValue;

        context.setVariable("a", firstRandValue);
        context.setVariable("b", secondRandValue);
        context.push("a");
        context.push("b");
        worker.work(null, context);

        assertEquals(divResult, context.peek(), 0.0000000000000000000001);
    }

    @Test
    public void sqrtSimple()
    {
        SqrtWorker worker = new SqrtWorker();
        Context context = new Context();
        Random random = new Random();

        double randValue = random.nextDouble(1000);
        double randResult = Math.sqrt(randValue);

        context.setVariable("a", randValue);
        context.push("a");
        worker.work(null, context);

        assertEquals(randResult, context.peek(), 0.0000000000000000000001);
    }

    @Test
    public void printSimple()
    {
        PrintWorker worker = new PrintWorker();
        Context context = new Context();

        Random random = new Random();

        double randValue = random.nextDouble(100, 300);

        context.setVariable("a", randValue);
        context.push("a");

        worker.work(null, context);
        assertEquals(randValue, Double.parseDouble(outContent.toString()), 0.000000000000000001);
    }

    @Test
    public void defineFirstTest()
    {
        DefineWorker worker = new DefineWorker();
        Context context = new Context();

        String[] commandArgs = {"DEFINE", "123f", "34.2"};
        assertThrows(VariableSyntaxException.class, ()->{
            worker.work(commandArgs, context);
        });
    }

    @Test
    public void defineSecondTest()
    {
        DefineWorker worker = new DefineWorker();
        Context context = new Context();

        String[] commandArgs = {"DEFINE", "wer", "34.2fgfgf"};
        assertThrows(NumberFormatException.class, ()->{
            worker.work(commandArgs, context);
        });
    }

    @Test
    public void pushTest()
    {
        PushWorker worker = new PushWorker();
        Context context = new Context();

        String[] commandArgs = {"PUSH", "lalala"};
        assertThrows(VariableExistenceException.class, ()->{
            worker.work(commandArgs, context);
        });
    }

    @Test
    public void popTest()
    {
        PopWorker worker = new PopWorker();
        Context context = new Context();

        assertThrows(EmptyStackException.class, ()->{
            worker.work(null, context);
        });
    }

    ////Testing methods to throw exception////
    @Test
    public void addEmptyStackException()
    {
        AdditionWorker worker = new AdditionWorker();
        Context context = new Context();

        assertThrows(EmptyStackException.class, ()->{
            worker.work(null, context);
        });
    }

    @Test
    public void subEmptyStackException()
    {
        SubtractionWorker worker = new SubtractionWorker();

        Context context = new Context();

        assertThrows(EmptyStackException.class, ()->{
            worker.work(null, context);
        });
    }

    @Test
    public void mulEmptyStackException()
    {
        MultiplicationWorker worker = new MultiplicationWorker();

        Context context = new Context();

        assertThrows(EmptyStackException.class, ()->{
            worker.work(null, context);
        });
    }

    @Test
    public void divEmptyStackException()
    {
        DivisionWorker worker = new DivisionWorker();

        Context context = new Context();

        assertThrows(EmptyStackException.class, ()->{
            worker.work(null, context);
        });
    }

    @Test
    public void divByZeroException()
    {
        DivisionWorker worker = new DivisionWorker();
        Context context = new Context();

        Random random = new Random();

        context.setVariable("a", 0.0);
        context.setVariable("b", random.nextDouble(100, 1000));

        context.push("a");
        context.push("b");

        assertThrows(DivisionByZeroException.class, ()->{
            worker.work(null, context);
        });
    }

    @Test
    public void sqrtEmptyStackException()
    {
        SqrtWorker worker = new SqrtWorker();
        Context context = new Context();

        assertThrows(EmptyStackException.class, ()->{
            worker.work(null, context);
        });
    }
}
