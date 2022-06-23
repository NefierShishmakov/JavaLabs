package ru.nsu.ccfit.shishmakov.carfactory.carfabric.carstoragemanager;

import ru.nsu.ccfit.shishmakov.carfactory.carfabric.car.Car;
import ru.nsu.ccfit.shishmakov.carfactory.carfabric.carmanufactory.CarManuFactory;
import ru.nsu.ccfit.shishmakov.carfactory.carfabric.carstorage.CarStorage;
import ru.nsu.ccfit.shishmakov.carfactory.utils.constants.ErrorConstants;
import ru.nsu.ccfit.shishmakov.carfactory.utils.constants.LoggerConstants;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class CarStorageManager extends Thread implements PropertyChangeListener {
    private static final String STORAGE_MANAGER_NAME = "StorageManager Thread";
    private static final int ORDER_SIZE = 10;

    public CarStorageManager(CarStorage carStorage, CarManuFactory carManufactory, boolean logMode)
    {
        super(STORAGE_MANAGER_NAME);
        assert carStorage != null;
        assert carManufactory != null;

        carStorage.addListener(this);
        this.carManufactory = carManufactory;
        this.capacity = carStorage.capacity();
        this.size = carStorage.size();
        this.logMode = logMode;

        if (logMode)
        {
            try
            {
                this.logWriter = new BufferedWriter(new FileWriter(LoggerConstants.LOGGER_PATH_NAME));
            }
            catch (IOException e)
            {
                System.err.println(e.getLocalizedMessage());
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent event)
    {
        synchronized(this.monitor)
        {
            this.size = (Integer) event.getNewValue();
            this.lastCar = (Car) event.getOldValue();
            this.monitor.notify();
        }
    }

    public void run(){
        synchronized (this.monitor)
        {
            while (true)
            {
                try
                {
                    if (this.size < this.capacity && this.carManufactory.getOrdersCount() <= this.capacity)
                    {
                        for (int i = 0; i < ORDER_SIZE; ++i)
                        {
                            this.carManufactory.addCarOrder();
                        }
                    }

                    this.monitor.wait();

                    if (this.logMode)
                    {
                        writeLog();
                    }
                }
                catch (InterruptedException e)
                {
                    System.out.println(ErrorConstants.CAR_STORAGE_MANAGER_INTERRUPTED_ERROR);
                    if (this.logMode)
                    {
                        try
                        {
                            this.logWriter.close();
                        }
                        catch (IOException ignored) {}
                    }

                    return;
                }
            }
        }
    }

    private void writeLog()
    {
        Date time = new Date();

        try
        {
            this.logWriter.write(time + ": Dealer <" + this.lastCar.getDealerId() + ">: Auto <" + this.lastCar.getCarId() +
                    ">(Body " + this.lastCar.getBodyId() + " Engine " + this.lastCar.getEngineId() + " Accessory "
                    + this.lastCar.getAccessoryId() + ")\n");
            this.logWriter.flush();
        } catch (IOException e)
        {
            System.err.println(LoggerConstants.LOGGER_EXCEPTION_MESSAGE + e.getLocalizedMessage());
        }
    }

    private final CarManuFactory carManufactory;
    private final Object monitor = new Object();
    private final int capacity;
    private int size;
    private Car lastCar;
    private final boolean logMode;
    private BufferedWriter logWriter;
}
