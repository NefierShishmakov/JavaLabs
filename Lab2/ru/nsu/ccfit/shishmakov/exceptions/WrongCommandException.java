package ru.nsu.ccfit.shishmakov.exceptions;

public class WrongCommandException extends CommandException {
    public WrongCommandException(String message)
    {
        super(message);
    }

    public WrongCommandException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
