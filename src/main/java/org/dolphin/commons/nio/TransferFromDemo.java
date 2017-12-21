package org.dolphin.commons.nio;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class TransferFromDemo {

	public static void main(String[] args) throws Exception {
		RandomAccessFile fromFile = new RandomAccessFile("D:"+File.separator+"testaaa.txt","rw");
		FileChannel fromChannel = fromFile.getChannel();
		
		RandomAccessFile toFile = new RandomAccessFile("D:"+File.separator+"testbbb.txt","rw");
		FileChannel toChannel = toFile.getChannel();
		
		long position = 0;
		long count = toChannel.size();
		
		toChannel.transferTo(position, count, fromChannel);
		
		toChannel.close();
		fromChannel.close();
	}

}
