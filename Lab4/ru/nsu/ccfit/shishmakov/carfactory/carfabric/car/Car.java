package ru.nsu.ccfit.shishmakov.carfactory.carfabric.car;

import ru.nsu.ccfit.shishmakov.carfactory.carfabric.carparts.Accessory;
import ru.nsu.ccfit.shishmakov.carfactory.carfabric.carparts.Body;
import ru.nsu.ccfit.shishmakov.carfactory.carfabric.carparts.Engine;

public class Car
{
    public Car(Body body, Engine engine, Accessory accessory)
    {
        this.body = body;
        this.engine = engine;
        this.accessory = accessory;
    }

    public void setDealerId(int id)
    {
        this.dealerId = id;
    }

    public int getDealerId()
    {
        return this.dealerId;
    }

    public String getCarId()
    {
        return String.valueOf(this.body.getId()) + this.engine.getId() + this.accessory.getId();
    }

    public int getBodyId()
    {
        return this.body.getId();
    }

    public int getEngineId()
    {
        return this.engine.getId();
    }

    public int getAccessoryId()
    {
        return this.accessory.getId();
    }

    private final Body body;
    private final Engine engine;
    private final Accessory accessory;
    private int dealerId;
}
