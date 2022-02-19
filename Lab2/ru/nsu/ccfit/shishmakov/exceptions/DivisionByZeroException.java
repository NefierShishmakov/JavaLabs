package ru.nsu.ccfit.shishmakov.exceptions;

public class DivisionByZeroException extends Exception {
    public DivisionByZeroException(String message)
    {
        super(message);
    }

    public DivisionByZeroException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
