package ru.nsu.ccfit.shishmakov.minesweeper.command;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface Command {
    void execute() throws IOException;
}
