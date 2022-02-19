package ru.nsu.ccfit.shishmakov.exceptions;

public class VariableExistenceException extends VariableException {
    public VariableExistenceException(String message)
    {
        super(message);
    }

    public VariableExistenceException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
