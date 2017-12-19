package org.dolphin.commons.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.SequenceInputStream;
/**
 * 将2个输入流合并为一个输入流
 * @author yaokai
 *
 */
public class SequenceStreamDemo {

	public static void main(String[] args) throws Exception {
		FileInputStream fis1 = new FileInputStream("D://test123/test456/123.txt");
		FileInputStream fis2 = new FileInputStream("D://test123/test456/456.txt");
		SequenceInputStream sis = new SequenceInputStream(fis1,fis2);
		FileOutputStream fos = new FileOutputStream("D://test123/test456/789.txt");
		int len = 0;
		byte[] buf = new byte[1024];
		while((len = sis.read(buf))>0){
			fos.write(buf, 0, len);
		}
		fos.close();
		sis.close();
		fis2.close();
		fis1.close();
	}

}
