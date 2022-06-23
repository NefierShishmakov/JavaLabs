package ru.nsu.ccfit.shishmakov.carfactory.carfabric.carstorage;

import ru.nsu.ccfit.shishmakov.carfactory.carfabric.car.Car;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Stack;

public class CarStorage {
    private static final String CAR_SUPPLY_MESSAGE = "carSupply";

    public CarStorage(int capacity)
    {
        this.storage = new Stack<>();
        this.storage.ensureCapacity(capacity);
        this.capacity = capacity;
        this.support = new PropertyChangeSupport(this);
    }

    public void addListener(PropertyChangeListener listener)
    {
        this.support.addPropertyChangeListener(listener);
    }

    public void removeListener(PropertyChangeListener listener)
    {
        this.support.removePropertyChangeListener(listener);
    }

    public synchronized Car getCar(int dealerId) throws InterruptedException
    {
        while (true)
        {
            if (this.storage.empty())
            {
                wait();
            }
            else
            {
                break;
            }
        }

        Car car = this.storage.pop();
        car.setDealerId(dealerId);
        this.support.firePropertyChange(CAR_SUPPLY_MESSAGE, car, this.storage.size());
        notify();

        return car;
    }

    public synchronized void putCar(Car car) throws  InterruptedException
    {
        while (true)
        {
            if (this.storage.size() >= this.capacity)
            {
                wait();
            } else
            {
                break;
            }
        }

        ++this.carCounter;
        this.storage.push(car);
        notify();
    }

    public int capacity()
    {
        return this.capacity;
    }

    public int size()
    {
        return this.storage.size();
    }

    public int getTotalCount()
    {
        return this.carCounter;
    }

    private final Stack<Car> storage;
    private final int capacity;
    private final PropertyChangeSupport support;
    private int carCounter;
}