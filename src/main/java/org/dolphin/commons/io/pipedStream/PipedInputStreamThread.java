package org.dolphin.commons.io.pipedStream;

import java.io.IOException;
import java.io.PipedInputStream;

public class PipedInputStreamThread extends Thread{
	private PipedInputStream pipedInputStream;
	public PipedInputStreamThread(PipedInputStream pipedInputStream){
		this.pipedInputStream = pipedInputStream;
	}
	@Override
	public void run() {
		byte[] buf = new byte[1024];
		try {
			int len = pipedInputStream.read(buf);
			System.out.println("接收消息:"+new String(buf,0,len,"UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				pipedInputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}