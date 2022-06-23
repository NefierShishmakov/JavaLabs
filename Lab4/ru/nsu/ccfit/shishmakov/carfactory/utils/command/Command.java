package ru.nsu.ccfit.shishmakov.carfactory.utils.command;

public class Command {
    private static final int DEFAULT_DELAY_VALUE = 1000;

    public enum CommandType
    {
        DEALER_DELAY, BODY_DELAY, ENGINE_DELAY, ACCESSORY_DELAY, INCORRECT
    }

    private Command()
    {
        this.type = CommandType.INCORRECT;
        this.value = DEFAULT_DELAY_VALUE;
    }

    public static Command getInstance()
    {
        return instance;
    }

    public CommandType getType()
    {
        return this.type;
    }

    public void setType(CommandType type)
    {
        this.type = type;
    }

    public void setValue(int value)
    {
        this.value = value;
    }

    public int getValue()
    {
        return this.value;
    }

    private int value;
    private CommandType type;
    private static final Command instance = new Command();
}
