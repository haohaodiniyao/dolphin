package org.dolphin.commons;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class JunitDemo {

	public static void main(String[] args) throws Exception{
		
		
		final ThreadLocal<Long> longLocal = new ThreadLocal<Long>();
		
		final ThreadLocal<String> stringLocal = new ThreadLocal<String>();
		
		Thread t1 = new Thread(new Runnable() {

			public void run() {
				longLocal.set(Thread.currentThread().getId());
				stringLocal.set(Thread.currentThread().getName());
				try {
					Thread.currentThread().sleep(1000*1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		
		Thread t2 = new Thread(new Runnable() {

			public void run() {
				longLocal.set(Thread.currentThread().getId());
				stringLocal.set(Thread.currentThread().getName());
				try {
					Thread.currentThread().sleep(1000*1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		
		
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		
		
		System.out.println(longLocal);
		System.out.println(stringLocal);
		
//		Thread.currentThread().setName("my main thread dump");
		
//		ExecutorService es = Executors.newFixedThreadPool(3);
//		
//		for(int i=0;i<5;i++){
//			es.submit(new Runnable(){
//
//				@Override
//				public void run() {
//					
//					try {
//						TimeUnit.SECONDS.sleep(20);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					
//				}
//				
//			});
//		}
//		
//		try {
//			TimeUnit.SECONDS.sleep(900);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
}
