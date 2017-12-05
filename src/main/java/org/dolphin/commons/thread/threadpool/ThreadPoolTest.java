package org.dolphin.commons.thread.threadpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {

	public static void main(String[] args) {
		int corePoolSize = 3;
		int maximumPoolSize = 6;
		long keepAliveTime = 0;
		BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(10);
		ThreadFactory threadFactory = Executors.defaultThreadFactory();
		ExecutorService es = new MyThreadPoolExecutor(corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                TimeUnit.SECONDS,
                workQueue,
                threadFactory,
                new AbortPolicy());
		
		es.submit(new Runnable(){

			@Override
			public void run() {
//                while(true){
//    				Runtime runtime = Runtime.getRuntime();
//    				int availableProcessors = runtime.availableProcessors();
//    				long freeMemory = runtime.freeMemory();
//    				long totalMemory = runtime.totalMemory();
//    				System.out.println("totalMemory-freeMemory="+(totalMemory-freeMemory));
//    				try {
//						TimeUnit.SECONDS.sleep(1);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//                }
				
			}
        	
        });
		for(int i=1;i<100;i++){
			es.submit(new MyRunnable(i,es));
		}
		es.shutdown();
        
	}

}
