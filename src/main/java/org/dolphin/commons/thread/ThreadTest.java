package org.dolphin.commons.thread;

import java.util.concurrent.TimeUnit;

public class ThreadTest implements Runnable {
	private Thread thread;
	public ThreadTest(Thread thread){
		this.thread = thread;
	}
	@Override
	public void run() {
		System.out.println(Thread.currentThread()+"#start");
		String str = Thread.currentThread().getName();
		if("t1".equals(str)){
			try {
				TimeUnit.SECONDS.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if("t2".equals(str)){
			try {
				TimeUnit.SECONDS.sleep(5);
				thread.interrupt();
				TimeUnit.SECONDS.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(Thread.currentThread()+"#end");
	}
	public Thread getThread() {
		return thread;
	}
	public void setThread(Thread thread) {
		this.thread = thread;
	}

}
