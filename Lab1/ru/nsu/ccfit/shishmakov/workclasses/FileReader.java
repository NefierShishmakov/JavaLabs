package ru.nsu.ccfit.shishmakov.workclasses;

import ru.nsu.ccfit.shishmakov.additional.CONSTANTS;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileInputStream;

public class FileReader {
    public FileReader(String fileName)
    {
         this.fileName = fileName;
    }

    public ArrayList<String> readFile() throws IOException
    {
        FileInputStream file;
        try {
            file = new FileInputStream(this.fileName);
        } catch (FileNotFoundException ex)
        {
            throw new FileNotFoundException(CONSTANTS.ERROR_MESSAGE + CONSTANTS.FILE_ERROR + fileName);
        }

        Scanner scanner = new Scanner(file, StandardCharsets.UTF_8);

        ArrayList<String> lines = new ArrayList<>();

        while (scanner.hasNextLine())
        {
            lines.add(scanner.nextLine());
        }

        scanner.close();

        try {
            file.close();
        } catch (IOException ex)
        {
            throw new IOException();
        }

        return lines;
    }

    private final String fileName;
}
