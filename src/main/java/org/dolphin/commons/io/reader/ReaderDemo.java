package org.dolphin.commons.io.reader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * 缓冲流
 * 字符输入流(读取) FileReader BufferedReader
 * 字符输出流(写入) FileWriter BufferedWrIter
 * @author yaokai
 *
 */
public class ReaderDemo {

	public static void main(String[] args) throws Exception {
//		FileReader reader = new FileReader(new File("D:"+File.separator+"test.txt"));
//		char[] cbuf = new char[1024];
//		reader.read(cbuf);
//		System.out.println(new String(cbuf));
//		reader.close();
		
		FileWriter writer = new FileWriter(new File("D:"+File.separator+"test.txt"));
//		String msg = "hi,hello world!";
//		writer.write(msg.toCharArray());
//		writer.close();
		
//		BufferedReader bufferedReader = new BufferedReader(reader);
//		//可以读取一行
//		System.out.println(bufferedReader.readLine());
//		bufferedReader.close();
		
		BufferedWriter bufferedWriter = new BufferedWriter(writer);
		bufferedWriter.write("hi,hello world!");
		//可以换行
		bufferedWriter.newLine();
		bufferedWriter.write("hi,hello world!");
		bufferedWriter.close();
	}

}
