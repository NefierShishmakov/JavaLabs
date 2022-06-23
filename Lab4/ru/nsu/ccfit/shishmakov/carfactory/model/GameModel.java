package ru.nsu.ccfit.shishmakov.carfactory.model;

import ru.nsu.ccfit.shishmakov.carfactory.carfabric.carmanufactory.CarManuFactory;
import ru.nsu.ccfit.shishmakov.carfactory.carfabric.carparts.Accessory;
import ru.nsu.ccfit.shishmakov.carfactory.carfabric.carparts.Body;
import ru.nsu.ccfit.shishmakov.carfactory.carfabric.carparts.Engine;
import ru.nsu.ccfit.shishmakov.carfactory.carfabric.carstorage.CarStorage;
import ru.nsu.ccfit.shishmakov.carfactory.carfabric.carstoragemanager.CarStorageManager;
import ru.nsu.ccfit.shishmakov.carfactory.carfabric.dealer.Dealer;
import ru.nsu.ccfit.shishmakov.carfactory.carfabric.mansupplier.ManSupplier;
import ru.nsu.ccfit.shishmakov.carfactory.carfabric.storage.Storage;
import ru.nsu.ccfit.shishmakov.carfactory.utils.confighandler.ConfigHandler;
import ru.nsu.ccfit.shishmakov.carfactory.utils.constants.ConfigConstants;

public class GameModel {
    private static final int DEFAULT_DELAY = 1000;

    public GameModel()
    {
        this.configHandler = new ConfigHandler();
    }

    public void loadConfig(String pathname)
    {
        this.configHandler.loadConfigOptions(pathname);
    }

    public void initModel()
    {
        this.bodyStorage = new Storage<>(this.configHandler.getOption(ConfigConstants.STORAGE_BODY_SIZE));
        this.engineStorage = new Storage<>(this.configHandler.getOption(ConfigConstants.STORAGE_ENGINE_SIZE));
        this.accessoryStorage = new Storage<>(this.configHandler.getOption(ConfigConstants.STORAGE_ACCESSORY_SIZE));

        this.bodySupplier = new ManSupplier<>(this.bodyStorage, DEFAULT_DELAY, Body.class);
        this.engineSupplier = new ManSupplier<>(this.engineStorage, DEFAULT_DELAY, Engine.class);

        int accessorySuppliesCount = this.configHandler.getOption(ConfigConstants.ACCESSORY_SUPPLIES);
        this.accessorySuppliers = new ManSupplier[accessorySuppliesCount];

        for (int i = 0; i < accessorySuppliesCount; ++i)
        {
            this.accessorySuppliers[i] = new ManSupplier<>(this.accessoryStorage, DEFAULT_DELAY, Accessory.class);
        }

        this.carStorage = new CarStorage(this.configHandler.getOption(ConfigConstants.STORAGE_CAR_SIZE));
        this.carManufactory = new CarManuFactory(this.configHandler.getOption(ConfigConstants.WORKERS), this.carStorage,
                this.bodyStorage, this.engineStorage, this.accessoryStorage);

        boolean logMode = this.configHandler.getOption(ConfigConstants.LOG_MODE) == ConfigConstants.LOG_MODE_IS_ON;
        this.carStorageManager = new CarStorageManager(this.carStorage, this.carManufactory, logMode);

        int dealersCount = this.configHandler.getOption(ConfigConstants.DEALERS);
        this.dealers = new Dealer[dealersCount];

        for (int i = 0; i < dealersCount; ++i)
        {
            this.dealers[i] = new Dealer(this.carStorage, DEFAULT_DELAY, i);
        }
    }

    public void startProduction()
    {
        this.bodySupplier.start();
        this.engineSupplier.start();

        for (ManSupplier<Accessory> accessorySupplier : this.accessorySuppliers)
        {
            accessorySupplier.start();
        }

        this.carStorageManager.start();

        for (Dealer dealer : this.dealers)
        {
            dealer.start();
        }

        this.carManufactory.startManufactory();
    }

    public void interruptProduction()
    {
        this.bodySupplier.interrupt();
        this.engineSupplier.interrupt();

        for (ManSupplier<Accessory> accessorySupplier : this.accessorySuppliers)
        {
            accessorySupplier.interrupt();
        }

        this.carStorageManager.interrupt();

        for (Dealer dealer : this.dealers)
        {
            dealer.interrupt();
        }

        this.carManufactory.interruptManufactory();
    }

    public int getSupplierBodyDelay()
    {
        return this.bodySupplier.getDelay();
    }

    public void setSupplierBodyDelay(int delay)
    {
        this.bodySupplier.setDelay(delay);
    }

    public int getSupplierEngineDelay()
    {
        return this.engineSupplier.getDelay();
    }

    public void setSupplierEngineDelay(int delay)
    {
        this.engineSupplier.setDelay(delay);
    }

    public int getSupplierAccessoryDelay()
    {
        return this.accessorySuppliers[0].getDelay();
    }

    public void setSupplierAccessoryDelay(int delay)
    {
        for (ManSupplier<Accessory> supplier : this.accessorySuppliers)
        {
            supplier.setDelay(delay);
        }
    }

    public int getDealersDelay()
    {
        return this.dealers[0].getDelay();
    }

    public void setDealersDelay(int delay)
    {
        for (Dealer dealer : this.dealers)
        {
            dealer.setDelay(delay);
        }
    }

    public int getBodyStorageCapacity()
    {
        return this.bodyStorage.capacity();
    }

    public int getEngineStorageCapacity()
    {
        return this.engineStorage.capacity();
    }

    public int getAccessoryStorageCapacity()
    {
        return this.accessoryStorage.capacity();
    }

    public int getCarStorageCapacity()
    {
        return this.carStorage.capacity();
    }

    public int getManufactoryTaskCount()
    {
        return this.carManufactory.getOrdersCount();
    }

    public int getBodyStorageSize()
    {
        return this.bodyStorage.size();
    }

    public int getEngineStorageSize()
    {
        return this.engineStorage.size();
    }

    public int getAccessoryStorageSize()
    {
        return this.accessoryStorage.size();
    }

    public int getCarStorageSize()
    {
        return this.carStorage.size();
    }

    public int getTotalBodiesCount()
    {
        return this.bodyStorage.getTotalCount();
    }

    public int getTotalEnginesCount()
    {
        return this.engineStorage.getTotalCount();
    }

    public int getTotalAccessoriesCount()
    {
        return this.accessoryStorage.getTotalCount();
    }

    public int getTotalCarsCount()
    {
        return this.carStorage.getTotalCount();
    }

    private Storage<Body> bodyStorage;
    private Storage<Engine> engineStorage;
    private Storage<Accessory> accessoryStorage;

    private ManSupplier<Body> bodySupplier;
    private ManSupplier<Engine> engineSupplier;
    private ManSupplier<Accessory>[] accessorySuppliers;

    private CarStorage carStorage;
    private CarStorageManager carStorageManager;

    private CarManuFactory carManufactory;

    private Dealer[] dealers;

    private final ConfigHandler configHandler;
}
