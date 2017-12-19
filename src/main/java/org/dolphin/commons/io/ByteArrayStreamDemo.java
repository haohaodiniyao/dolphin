package org.dolphin.commons.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
/**
 * ByteArrayInputStream 字节数组作为数据源
 * ByteArrayOutputStream
 * @author yaokai
 *
 */
public class ByteArrayStreamDemo {

	public static void main(String[] args) throws IOException {
		byte[] bytes = "hi,hello world!".getBytes();
		ByteArrayInputStream is = new ByteArrayInputStream(bytes);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		byte[] buf = new byte[1024];
		int len = 0;
		while((len = is.read(buf))>0){
			os.write(buf, 0, len);
		}
		System.out.println(os.toString("UTF-8"));
		os.close();
		is.close();
	}

}
