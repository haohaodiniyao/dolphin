package org.dolphin.commons.nio;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChannelTest1 {

	public static void main(String[] args) throws Exception {
		RandomAccessFile file = new RandomAccessFile("D:"+File.separator+"test123.txt","rw");
		FileChannel channel = file.getChannel();
		ByteBuffer buf = ByteBuffer.allocate(48);
		int bytesRead = channel.read(buf);
		while(bytesRead != -1){
			System.out.println("bytesRead:"+bytesRead);
			buf.flip();
			System.out.println(new String(buf.array()));
			buf.clear();
			bytesRead = channel.read(buf);
		}
		
		file.close();
	}

}
