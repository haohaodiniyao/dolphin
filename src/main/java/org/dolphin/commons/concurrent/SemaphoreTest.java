package org.dolphin.commons.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Semaphore(信号量)控制并发线程数
 * @author yaokai
 *
 */
public class SemaphoreTest {
	private static Semaphore s = new Semaphore(2);
	private static ExecutorService es = Executors.newFixedThreadPool(100);
	public static void main(String[] args) {
		for(int i=0;i<10;i++){
			es.submit(new Runnable(){

				@Override
				public void run() {
					try {
						s.acquire();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("exeutor..."+"可用许可证数量:"+s.availablePermits()+";等待获取许可证的线程数:"+s.getQueueLength()+";是否有线程等待获取许可证:"+s.hasQueuedThreads());
					s.release();
					
				}
				
			});
		}
	}

}
