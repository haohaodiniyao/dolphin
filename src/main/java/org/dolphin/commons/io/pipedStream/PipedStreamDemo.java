package org.dolphin.commons.io.pipedStream;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * 使用管道流进行线程间通信
 * @author yaokai
 *
 */
public class PipedStreamDemo {
	public static void main(String[] args) throws IOException{
		PipedInputStream pis = new PipedInputStream();
		PipedOutputStream pos = new PipedOutputStream();
		pos.connect(pis);
		new PipedInputStreamThread(pis).start();
		new PipedOutputStreamThread(pos).start();
	}
}
