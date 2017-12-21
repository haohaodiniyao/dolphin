package org.dolphin.commons.nio;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioTest {

	public static void main(String[] args) throws Exception {
		String[] infos = {"hi",",","hello"," ","world","!"};
		FileOutputStream fos = new FileOutputStream("D:"+File.separator+"test123.txt",true);
		FileChannel fc = fos.getChannel();
		ByteBuffer buf = ByteBuffer.allocate(1024);
		for(int i=0;i<infos.length;i++){
			buf.put(infos[i].getBytes());
		}
		buf.flip();
		fc.write(buf);
		fc.close();
		fos.close();
	}

}
