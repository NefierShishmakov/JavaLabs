package ru.nsu.ccfit.shishmakov.exceptions;

public class WrongCommandArgumentsException extends CommandException {
    public WrongCommandArgumentsException(String message)
    {
        super(message);
    }

    public WrongCommandArgumentsException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
