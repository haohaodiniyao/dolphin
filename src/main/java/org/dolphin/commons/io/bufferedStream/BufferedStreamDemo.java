package org.dolphin.commons.io.bufferedStream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
/**
 * 字节流和缓冲流
 * FileInputStream BufferedInputStream
 * FileOutputStream BufferedOutputStream
 * 自带缓冲区的流 默认8192字节8k
 * @author yaokai
 *
 */
public class BufferedStreamDemo {

	public static void main(String[] args) throws Exception {
		FileInputStream fis = new FileInputStream("D://test123/test456/123.txt");
		BufferedInputStream bis = new BufferedInputStream(fis);
		FileOutputStream fos = new FileOutputStream("D://test123/test456/aaa.txt");
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		byte[] buf = new byte[1024];
		int len = 0;
		while((len = bis.read(buf))>0){
			bos.write(buf, 0, len);
		}
		bos.close();
		fos.close();
		bis.close();
		fis.close();
	}

}
