package ru.nsu.ccfit.shishmakov.carfactory.utils.confighandler;

import ru.nsu.ccfit.shishmakov.carfactory.utils.constants.ConfigConstants;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class ConfigHandler {
    public ConfigHandler()
    {
        this.options = new HashMap<>();
        options.put(ConfigConstants.STORAGE_BODY_SIZE, ConfigConstants.DEFAULT_STORAGE_SIZE);
        options.put(ConfigConstants.STORAGE_ENGINE_SIZE, ConfigConstants.DEFAULT_STORAGE_SIZE);
        options.put(ConfigConstants.STORAGE_ACCESSORY_SIZE, ConfigConstants.DEFAULT_STORAGE_SIZE);
        options.put(ConfigConstants.STORAGE_CAR_SIZE, ConfigConstants.DEFAULT_STORAGE_SIZE);
        options.put(ConfigConstants.ACCESSORY_SUPPLIES, ConfigConstants.DEFAULT_ACCESSORY_SUPPLIES);
        options.put(ConfigConstants.WORKERS, ConfigConstants.DEFAULT_WORKERS);
        options.put(ConfigConstants.DEALERS, ConfigConstants.DEFAULT_DEALERS);
        options.put(ConfigConstants.LOG_MODE, ConfigConstants.DEFAULT_LOG_MODE);

    }

    public void loadConfigOptions(String pathname){
        Scanner scanner;

        try
        {
            scanner = new Scanner(new File(pathname));
        }
        catch (FileNotFoundException e)
        {
            System.err.println(ConfigConstants.OPEN_CONFIG_ERROR_MESSAGE);
            return;
        }

        int line = 0;

        while (scanner.hasNextLine())
        {
            ++line;

            try
            {
                String[] optionAndVal = scanner.nextLine().split(ConfigConstants.OPTION_REGEX);

                if (this.options.containsKey(optionAndVal[0]))
                {
                    int val = Integer.parseInt(optionAndVal[1]);

                    if (val <= 0)
                    {
                        throw new RuntimeException();
                    }

                    this.options.put(optionAndVal[0], val);
                }
            }
            catch (RuntimeException e)
            {
                System.err.println(ConfigConstants.INVALID_OPTION_ERROR_MESSAGE + line);
            }
        }
    }

    public int getOption(String optionName)
    {
        if (this.options.containsKey(optionName))
        {
            return this.options.get(optionName);
        }
        else
        {
            throw new IllegalArgumentException(ConfigConstants.OPTION_ERROR_MESSAGE);
        }
    }

    private final HashMap<String, Integer> options;
}
