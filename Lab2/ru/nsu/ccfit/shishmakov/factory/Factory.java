package ru.nsu.ccfit.shishmakov.factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import ru.nsu.ccfit.shishmakov.worker.Worker;

public class Factory {
    private static final String CONFIG_FILE_PATH = "config.properties";

    public Factory() throws IOException {
        InputStream config = Factory.class.getClassLoader().getResourceAsStream(CONFIG_FILE_PATH);
        if (config != null)
        {
            this.prop = new Properties();
            prop.load(config);
            config.close();
        }
    }

    public Worker getWorker(String type) throws ReflectiveOperationException
    {
        return (Worker) Class.forName(prop.getProperty(type)).getDeclaredConstructor().newInstance();
    }

    private Properties prop;
}
