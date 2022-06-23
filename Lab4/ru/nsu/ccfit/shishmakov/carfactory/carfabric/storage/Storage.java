package ru.nsu.ccfit.shishmakov.carfactory.carfabric.storage;

import ru.nsu.ccfit.shishmakov.carfactory.carfabric.carparts.Detail;

import java.util.Stack;

public class Storage<T extends Detail> {

    public Storage(int capacity)
    {
        this.storage = new Stack<T>();
        this.storage.ensureCapacity(capacity);
        this.capacity = capacity;
        this.detailCounter = 0;
    }

    public synchronized T getDetail() throws InterruptedException
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

        T detail = this.storage.pop();
        notify();

        return detail;
    }

    public synchronized void putDetail(T detail) throws InterruptedException
    {
        while (true)
        {
            if (this.storage.size() >= this.capacity)
            {
                wait();
            }
            else
            {
                break;
            }
        }

        ++this.detailCounter;
        this.storage.push(detail);
        notify();
    }

    public int capacity()
    {
        return this.capacity;
    }

    public int getTotalCount()
    {
        return this.detailCounter;
    }

    public int size()
    {
        return this.storage.size();
    }

    private final Stack<T> storage;
    private final int capacity;
    private int detailCounter;
}
