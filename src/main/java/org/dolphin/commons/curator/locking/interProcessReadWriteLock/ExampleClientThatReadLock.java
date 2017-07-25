package org.dolphin.commons.curator.locking.interProcessReadWriteLock;

import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;
import org.dolphin.commons.curator.locking.FakeLimitedResource;

public class ExampleClientThatReadLock {
    private final InterProcessReadWriteLock lock;
    private final InterProcessMutex readLock;
    private final FakeLimitedResource resource;
    private final String clientName;

    public ExampleClientThatReadLock(CuratorFramework client, String lockPath, FakeLimitedResource resource, String clientName)
    {
        this.resource = resource;
        this.clientName = clientName;
        lock = new InterProcessReadWriteLock(client, lockPath);
        readLock = lock.readLock();
    }

    public void     doWork(long time, TimeUnit unit) throws Exception
    {
        if ( !readLock.acquire(time, unit) )
        {
//            throw new IllegalStateException(clientName + " could not acquire the readLock");
        	System.out.println(clientName + " could not acquire the readLock");
        }else{
            try
            {
                System.out.println(clientName + " has the readLock");
                resource.use();
                Thread.sleep(3*1000);
            }
            finally
            {
                System.out.println(clientName + " releasing the readLock");
                readLock.release(); // always release the lock in a finally block
            }        	
        }
    }
}
