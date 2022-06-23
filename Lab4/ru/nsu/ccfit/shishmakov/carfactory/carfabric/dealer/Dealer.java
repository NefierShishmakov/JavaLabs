package ru.nsu.ccfit.shishmakov.carfactory.carfabric.dealer;

import ru.nsu.ccfit.shishmakov.carfactory.carfabric.car.Car;
import ru.nsu.ccfit.shishmakov.carfactory.carfabric.carstorage.CarStorage;
import ru.nsu.ccfit.shishmakov.carfactory.utils.constants.ErrorConstants;

public class Dealer extends Thread {

    private static final String DEALER_NAME = "Dealer Thread";

    public Dealer(CarStorage carStorage, int delay, int id)
    {
        super(DEALER_NAME + id);
        this.carStorage = carStorage;
        this.delay = delay;
        this.id = id;
    }

    public int getDelay()
    {
        return this.delay;
    }

    public void setDelay(int delay)
    {
        this.delay = delay;
    }

    public void run()
    {
        while (true)
        {
            try
            {
                this.carStorage.getCar(this.id);
                sleep(this.delay);
            }
            catch (InterruptedException e)
            {
                System.out.println(ErrorConstants.DEALER_INTERRUPTED_ERROR);
                return;
            }
        }
    }

    private final CarStorage carStorage;
    private int delay;
    private final int id;
    private Car lastCar;
}