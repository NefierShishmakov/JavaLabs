package ru.nsu.ccfit.shishmakov.carfactory.carfabric.mansupplier;

import ru.nsu.ccfit.shishmakov.carfactory.carfabric.carparts.Detail;
import ru.nsu.ccfit.shishmakov.carfactory.carfabric.storage.Storage;
import ru.nsu.ccfit.shishmakov.carfactory.utils.constants.ErrorConstants;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.atomic.AtomicInteger;

public class ManSupplier<T extends Detail> extends Thread {
    private static final String SUPPLIER_NAME = "Supplier Thread";
    private static final AtomicInteger numGen = new AtomicInteger(0);

    public ManSupplier(Storage<T> storage, int delay, Class<T> detailClass)
    {
        super(SUPPLIER_NAME);
        assert storage != null;
        assert delay > 0;

        this.storage = storage;
        this.delay = delay;
        this.detailClass = detailClass;
    }

    public void setDelay(int delay)
    {
        this.delay = delay;
    }

    public int getDelay()
    {
        return this.delay;
    }

    public void run()
    {
        while (true)
        {
            try
            {
                sleep(this.delay);
                this.storage.putDetail(this.detailClass.getDeclaredConstructor(Integer.class).newInstance(numGen.incrementAndGet()));
            }
            catch (InterruptedException e)
            {
                System.out.println(ErrorConstants.SUPPLIER_INTERRUPTED_ERROR);
                return;
            }
            catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e)
            {
                throw new RuntimeException(e);
            }
        }
    }

    private final Storage<T> storage;
    private final Class<T> detailClass;
    private int delay;
}