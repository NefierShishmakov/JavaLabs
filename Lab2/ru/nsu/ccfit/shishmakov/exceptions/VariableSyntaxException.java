package ru.nsu.ccfit.shishmakov.exceptions;

public class VariableSyntaxException extends VariableException {
    public VariableSyntaxException(String message)
    {
        super(message);
    }

    public VariableSyntaxException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
