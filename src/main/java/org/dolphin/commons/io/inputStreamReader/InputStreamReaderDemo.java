package org.dolphin.commons.io.inputStreamReader;

import java.io.OutputStreamWriter;

/**
 * 转换流
 * InputStreamReader
 * 字节流转换字符流的桥梁
 * OutputStreamWriter
 * char
 * @author yaokai
 *
 */
public class InputStreamReaderDemo {

	public static void main(String[] args) throws Exception {
//		InputStreamReader isr = new InputStreamReader(new FileInputStream("D:"+File.separator+"test.txt"));
		//读取控制台输入
//		InputStreamReader isr = new InputStreamReader(System.in);
//		char[] cbuf = new char[1024];
//		isr.read(cbuf);
//		System.out.println(new String(cbuf));
//		isr.close();
		
		//向控制台写入
		OutputStreamWriter osw = new OutputStreamWriter(System.out);
		String msg = "hi,hello world!";
		char[] cbuf = msg.toCharArray();
		osw.write(cbuf, 0, cbuf.length);
		osw.close();
	}

}
