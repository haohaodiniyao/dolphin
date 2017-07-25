package org.dolphin.commons.curator.locking.interProcessReadWriteLock;

import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;
import org.dolphin.commons.curator.locking.FakeLimitedResource;

public class ExampleClientThatWriteLock {
    private final InterProcessReadWriteLock lock;
    private final InterProcessMutex writeLock;
    private final FakeLimitedResource resource;
    private final String clientName;

    public ExampleClientThatWriteLock(CuratorFramework client, String lockPath, FakeLimitedResource resource, String clientName)
    {
        this.resource = resource;
        this.clientName = clientName;
        lock = new InterProcessReadWriteLock(client, lockPath);
        writeLock = lock.writeLock();
    }

    public void     doWork(long time, TimeUnit unit) throws Exception
    {
        if ( !writeLock.acquire(time, unit) )
        {
            throw new IllegalStateException(clientName + " could not acquire the writeLock");
        }
        try
        {
            System.out.println(clientName + " has the writeLock");
            resource.use();
            Thread.sleep(3*1000);
        }
        finally
        {
            System.out.println(clientName + " releasing the writeLock");
            writeLock.release(); // always release the lock in a finally block
        }
    }
}
