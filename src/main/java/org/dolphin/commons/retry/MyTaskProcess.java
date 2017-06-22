package org.dolphin.commons.retry;

import java.util.concurrent.Callable;
/**
 * 任务执行体模块
 * @author yaokai
 *
 */
public class MyTaskProcess implements Callable<Boolean> {
	private int index;
	
	public MyTaskProcess(int index) {
		this.index = index;
	}

	@Override
	public Boolean call() throws Exception {
		System.out.println(Thread.currentThread()+"#任务"+index+"处理中...");
		return false;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}
