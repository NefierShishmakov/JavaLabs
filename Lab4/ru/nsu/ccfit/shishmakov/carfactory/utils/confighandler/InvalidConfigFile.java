package ru.nsu.ccfit.shishmakov.carfactory.utils.confighandler;

public class InvalidConfigFile extends Exception {
    InvalidConfigFile(int line)
    {
        super("Invalid config file. Problems in line #"+ line + ".");
    }
}
