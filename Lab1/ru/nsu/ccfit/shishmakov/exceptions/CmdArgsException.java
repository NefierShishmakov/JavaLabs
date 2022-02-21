package ru.nsu.ccfit.shishmakov.exceptions;

public class CmdArgsException extends Exception {
    public CmdArgsException(String message)
    {
        super(message);
    }

    public CmdArgsException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
