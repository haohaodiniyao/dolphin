package org.dolphin.commons.thread;

import java.util.concurrent.TimeUnit;

public class MyThread {

	public static void main(String[] args) {
//		for (int i = 1; i <= 4; i++) {
//			Thread thread = new Thread(new ThreadGroup("testthreadgroup"), new Runnable() {
//
//				@Override
//				public void run() {
//					try {
//						System.out.println(Thread.currentThread());
//						Thread.sleep(10 * 1000);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
//
//			}, "testthread" + i, 0);
//			thread.start();
//			System.out.println("######");
//			System.out.println("#线程ID:" + thread.getId());
//			System.out.println("#是否守护线程:" + thread.isDaemon());
//			System.out.println("#是否被中断:" + thread.isInterrupted());
//			System.out.println("#是否存活:" + thread.isAlive());
//			System.out.println("#线程名称：" + thread.getName());
//			System.out.println("#线程优先级：" + thread.getPriority());
//			System.out.println("#线程状态：" + thread.getState());
//			System.out.println("#线程组：" + thread.getThreadGroup().getName());
//			System.out.println("#当前活跃线程数："+Thread.activeCount());
//			System.out.println("######");
//		}
		
		Thread t1 = new Thread(new ThreadTest(null),"t1");

		Thread t2 = new Thread(new ThreadTest(t1),"t2");
		t2.start();
		t1.start();
	}

}
