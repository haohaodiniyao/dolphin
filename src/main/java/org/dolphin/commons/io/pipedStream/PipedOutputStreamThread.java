package org.dolphin.commons.io.pipedStream;

import java.io.IOException;
import java.io.PipedOutputStream;
import java.util.concurrent.TimeUnit;

public class PipedOutputStreamThread extends Thread{
	private PipedOutputStream pipedOutputStream;
	public PipedOutputStreamThread(PipedOutputStream pipedOutputStream){
		this.pipedOutputStream = pipedOutputStream;
	}
	@Override
	public void run() {
		try {
			String msg = "线程间通信...";
			TimeUnit.SECONDS.sleep(5);
			pipedOutputStream.write(msg.getBytes("UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				pipedOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}