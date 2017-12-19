package org.dolphin.commons.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 * FileInputStream 文件作为数据源
 * FileOutputStream
 * @author yaokai
 *
 */
public class FileStream {

	public static void main(String[] args) throws IOException {
		FileInputStream fis = new FileInputStream("D://test123/test456/123.txt");
		FileOutputStream fos = new FileOutputStream("D://test123/test456/456.txt");
		int len = 0;
		byte[] buf = new byte[1024];
		while((len = fis.read(buf))>0){
			fos.write(buf, 0, len);
		}
		fos.close();
		fis.close();
	}

}
