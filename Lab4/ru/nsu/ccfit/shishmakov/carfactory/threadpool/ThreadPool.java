package ru.nsu.ccfit.shishmakov.carfactory.threadpool;

import ru.nsu.ccfit.shishmakov.carfactory.utils.constants.ErrorConstants;

import java.util.concurrent.LinkedBlockingQueue;

public class ThreadPool {
    private static final String WORKER = "WORKER ";

    public ThreadPool(int numThreads)
    {
        this.queue = new LinkedBlockingQueue<>();
        this.threads = new PoolWorker[numThreads];

        for (int i = 0; i < numThreads; i++)
        {
            this.threads[i] = new PoolWorker(WORKER + i);
        }
    }

    public void startThreadPool()
    {
        for (PoolWorker worker : this.threads)
        {
            worker.start();
        }
    }

    public void interruptThreadPool()
    {
        for (PoolWorker worker : this.threads)
        {
            worker.interrupt();
        }
    }

    public int getTaskCountInQueue()
    {
        synchronized (this.queue)
        {
            return this.queue.size();
        }
    }

    public void execute(Runnable task)
    {
        synchronized (this.queue)
        {
            this.queue.add(task);
            this.queue.notify();
        }

    }

    private class PoolWorker extends Thread {

        public PoolWorker(String name){
            super(name);
        }

        public void run()
        {
            Runnable task;

            while (true)
            {
                synchronized (queue)
                {
                    try
                    {
                        if (queue.isEmpty())
                        {
                            queue.wait();
                        }
                    }
                    catch (InterruptedException e)
                    {
                        System.out.println(ErrorConstants.WORKER_INTERRUPTED_ERROR);
                        return;
                    }

                    task = queue.poll();
                }

                try
                {
                    assert task != null;
                    task.run();

                    if (this.isInterrupted())
                    {
                        break;
                    }
                }
                catch (RuntimeException e)
                {
                    System.out.println(ErrorConstants.WORKER_INTERRUPTED_ERROR);
                    return;
                }
            }
        }
    }

    private final LinkedBlockingQueue<Runnable> queue;
    private final PoolWorker[] threads;
}
