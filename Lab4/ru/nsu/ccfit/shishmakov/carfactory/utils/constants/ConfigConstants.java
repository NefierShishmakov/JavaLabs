package ru.nsu.ccfit.shishmakov.carfactory.utils.constants;

public final class ConfigConstants {
    private ConfigConstants() {}

    public static final String OPEN_CONFIG_ERROR_MESSAGE = "Can't open config. Default setting applied.";
    public static final String OPTION_ERROR_MESSAGE = "Options does not exists.";
    public static final String OPTION_REGEX = "=";
    public static final String INVALID_OPTION_ERROR_MESSAGE = "Invalid option in line #";

    public static final int DEFAULT_STORAGE_SIZE = 100;
    public static final int DEFAULT_ACCESSORY_SUPPLIES = 2;
    public static final int DEFAULT_WORKERS = 3;
    public static final int DEFAULT_DEALERS = 2;
    public static final int DEFAULT_LOG_MODE = 0;

    public static final String STORAGE_BODY_SIZE = "storageBodySize";
    public static final String STORAGE_ENGINE_SIZE = "storageEngineSize";
    public static final String STORAGE_ACCESSORY_SIZE = "storageAccessorySize";
    public static final String ACCESSORY_SUPPLIES = "accessorySupplies";
    public static final String STORAGE_CAR_SIZE = "storageCarSize";
    public static final String WORKERS = "workers";
    public static final String LOG_MODE = "logMode";
    public static final String DEALERS = "dealers";

    public static final int LOG_MODE_IS_ON = 1;

    public static final String CONFIG_PATH_NAME = "src/main/resources/config.txt";
}
