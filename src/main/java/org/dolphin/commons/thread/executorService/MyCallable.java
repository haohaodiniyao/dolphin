package org.dolphin.commons.thread.executorService;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class MyCallable implements Callable<String> {
	private String msg;
	public MyCallable(String msg){
		this.msg = msg;
	}
	@Override
	public String call() throws Exception {
		System.out.println("msg:"+msg);
		TimeUnit.SECONDS.sleep(Integer.valueOf(msg));
		return msg;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

}
