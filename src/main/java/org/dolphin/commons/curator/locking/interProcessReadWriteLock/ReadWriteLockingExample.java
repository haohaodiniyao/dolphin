package org.dolphin.commons.curator.locking.interProcessReadWriteLock;
import org.apache.curator.utils.CloseableUtils;
import org.dolphin.commons.curator.locking.FakeLimitedResource;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.test.TestingServer;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ReadWriteLockingExample
{
    private static final int        QTY = 10;
    private static final int        REPETITIONS = QTY * 10;

    private static final String     PATH = "/examples/readwritelocks";

    public static void main(String[] args) throws Exception
    {
        // all of the useful sample code is in ExampleClientThatLocks.java

        // FakeLimitedResource simulates some external resource that can only be access by one process at a time
        final FakeLimitedResource   resource = new FakeLimitedResource();

        ExecutorService             service = Executors.newFixedThreadPool(QTY);
        final TestingServer         server = new TestingServer();
        try
        {
            for ( int i = 1; i <= QTY; ++i )
            {
                final int       index = i;
                Callable<Void>  task = new Callable<Void>()
                {
                    @Override
                    public Void call() throws Exception
                    {
//                        CuratorFramework        client = CuratorFrameworkFactory.newClient(server.getConnectString(), new ExponentialBackoffRetry(1000, 3));
                    	CuratorFramework        client = CuratorFrameworkFactory.newClient("192.168.157.128:2181,192.168.157.129:2181,192.168.157.130:2181", new ExponentialBackoffRetry(1000, 3));
                        try
                        {
                            client.start();
                            if(index % 2==0){
                                ExampleClientThatReadLock      read_example = new ExampleClientThatReadLock(client, PATH, resource, "Client " + index);
//                              for ( int j = 0; j < REPETITIONS; ++j )
//                              {
                                read_example.doWork(10, TimeUnit.SECONDS);
//                              }	
                            }else{
                                ExampleClientThatWriteLock      write_example = new ExampleClientThatWriteLock(client, PATH, resource, "Client " + index);
//                              for ( int j = 0; j < REPETITIONS; ++j )
//                              {
                                write_example.doWork(10, TimeUnit.SECONDS);
//                              }	                            	
                            }
                        }
                        catch ( InterruptedException e )
                        {
                            Thread.currentThread().interrupt();
                        }
                        catch ( Exception e )
                        {
                            e.printStackTrace();
                            // log or do something
                        }
                        finally
                        {
                            CloseableUtils.closeQuietly(client);
                        }
                        return null;
                    }
                };
                service.submit(task);
            }

            service.shutdown();
            service.awaitTermination(10, TimeUnit.MINUTES);
        }
        finally
        {
            CloseableUtils.closeQuietly(server);
        }
    }
}