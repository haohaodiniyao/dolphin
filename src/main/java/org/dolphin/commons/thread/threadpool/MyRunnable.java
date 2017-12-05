package org.dolphin.commons.thread.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class MyRunnable implements Runnable {
	private int index;
	private ExecutorService es;
	public MyRunnable(int index,ExecutorService es){
		this.index = index;
		this.es = es;
	}
	@Override
	public void run() {
		System.out.println(index+"######"+es.toString());
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public ExecutorService getEs() {
		return es;
	}
	public void setEs(ExecutorService es) {
		this.es = es;
	}

}
