package ru.nsu.ccfit.shishmakov.carfactory.carfabric.carmanufactory;

import ru.nsu.ccfit.shishmakov.carfactory.carfabric.car.Car;
import ru.nsu.ccfit.shishmakov.carfactory.carfabric.carparts.Accessory;
import ru.nsu.ccfit.shishmakov.carfactory.carfabric.carparts.Body;
import ru.nsu.ccfit.shishmakov.carfactory.carfabric.carparts.Engine;
import ru.nsu.ccfit.shishmakov.carfactory.carfabric.carstorage.CarStorage;
import ru.nsu.ccfit.shishmakov.carfactory.carfabric.storage.Storage;
import ru.nsu.ccfit.shishmakov.carfactory.threadpool.ThreadPool;

public class CarManuFactory {
    private static final String INTERRUPT_ERROR = "INTERRUPT";

    public CarManuFactory(int numWorkers, CarStorage carStorage, Storage<Body> bodyStorage,
                          Storage<Engine> engineStorage, Storage<Accessory> accessoryStorage)
    {
        this.threadPool = new ThreadPool(numWorkers);
        this.carStorage = carStorage;
        this.bodyStorage = bodyStorage;
        this.engineStorage = engineStorage;
        this.accessoryStorage = accessoryStorage;
    }

    public void addCarOrder()
    {
        this.threadPool.execute(() ->
        {
            try
            {
                this.carStorage.putCar(new Car(this.bodyStorage.getDetail(),
                        this.engineStorage.getDetail(), this.accessoryStorage.getDetail()));
            }
            catch (InterruptedException e)
            {
                throw new RuntimeException(INTERRUPT_ERROR);
            }
        });
    }

    public void startManufactory()
    {
        this.threadPool.startThreadPool();
    }

    public void interruptManufactory()
    {
        this.threadPool.interruptThreadPool();
    }

    public int getOrdersCount()
    {
        return this.threadPool.getTaskCountInQueue();
    }

    private final ThreadPool threadPool;
    private final CarStorage carStorage;
    private final Storage<Body> bodyStorage;
    private final Storage<Engine> engineStorage;
    private final Storage<Accessory> accessoryStorage;
}
