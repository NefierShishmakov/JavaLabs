package ru.nsu.ccfit.shishmakov.workclasses;

import ru.nsu.ccfit.shishmakov.additional.CONSTANTS;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.io.FileInputStream;

public class FileReader {
    public FileReader(String fileName) throws FileNotFoundException
    {
        try {
            file = new FileInputStream(fileName);
        } catch (FileNotFoundException ex)
        {
            throw new FileNotFoundException(CONSTANTS.FILE_ERROR + fileName + CONSTANTS.ERROR_MESSAGE);
        }

        this.scanner = new Scanner(this.file, StandardCharsets.UTF_8);
    }

    public String readLine() throws IOException
    {
        if (this.scanner.hasNextLine())
        {
            return this.scanner.nextLine();
        }

        scanner.close();

        try {
            file.close();
        } catch (IOException ex)
        {
            throw new IOException();
        }

        return null;
    }

    private final Scanner scanner;
    private final FileInputStream file;
}
