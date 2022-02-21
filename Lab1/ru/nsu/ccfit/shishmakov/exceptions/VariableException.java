package ru.nsu.ccfit.shishmakov.exceptions;

public class VariableException extends Exception {
    public VariableException(String message)
    {
        super(message);
    }

    public VariableException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
