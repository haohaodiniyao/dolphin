package org.dolphin.commons.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class SocketChannelDemo {

	public static void main(String[] args) throws Exception{
		SocketChannel socketChannel = SocketChannel.open();
		socketChannel.connect(new InetSocketAddress("http://www.baidu.com",80));
		
		ByteBuffer buf = ByteBuffer.allocate(48);
		int bytesRead = socketChannel.read(buf);
		while(bytesRead != -1){
			System.out.println(new String(buf.array()));
			buf.clear();
			bytesRead = socketChannel.read(buf);
		}
		
		socketChannel.close();
	}
}
