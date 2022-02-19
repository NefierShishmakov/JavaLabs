package ru.nsu.ccfit.shishmakov.factory;

import java.io.IOException;
import java.util.Properties;

//////////////My packages//////////////
import ru.nsu.ccfit.shishmakov.worker.Worker;
import ru.nsu.ccfit.shishmakov.utils.CONSTANTS;

public class Factory {
    public Factory() throws IOException {
        this.prop = new Properties();
        var config = Factory.class.getResourceAsStream(CONSTANTS.CONFIG_FILE_PATH);
        if (config != null)
        {
            prop.load(config);
            config.close();
        }
    }

    public Worker getWorker(String type) throws ReflectiveOperationException
    {
        return (Worker) Class.forName(prop.getProperty(type)).getDeclaredConstructor().newInstance();
    }

    private final Properties prop;
}
