package org.dolphin.commons.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * CyclicBarrier可以循环使用的同步屏障
 * @author yaokai
 *
 */
public class CyclicBarrierTest {
	private static ExecutorService es = Executors.newCachedThreadPool();
	private static CyclicBarrier c = new CyclicBarrier(2,new Runnable(){

		@Override
		public void run() {
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+" all end...");	
		}
		
	});
	public static void main(String[] args) {
		new MyTask().start();
		new MyTask().start();
		try {
			TimeUnit.SECONDS.sleep(20);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c.reset();
		new MyTask().start();
		new MyTask().start();
	}
	
	static class MyTask extends Thread{

		@Override
		public void run() {
			try {
				TimeUnit.SECONDS.sleep(2);
				System.out.println("sleep 2s end...");
				try {
					c.await();
				} catch (BrokenBarrierException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}
