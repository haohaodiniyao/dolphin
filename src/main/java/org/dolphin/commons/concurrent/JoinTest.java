package org.dolphin.commons.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * join用于让当前执行线程等待join线程执行结束
 * @author yaokai
 *
 */
public class JoinTest {

	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new Runnable(){

			@Override
			public void run() {
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("sleep 2s end...");
			}
			
		});
		Thread t2 = new Thread(new Runnable(){

			@Override
			public void run() {
				try {
					TimeUnit.SECONDS.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("sleep 5s end...");
			}
			
		});
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println("all end...");
	}

}
