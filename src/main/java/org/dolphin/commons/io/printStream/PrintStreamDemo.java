package org.dolphin.commons.io.printStream;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class PrintStreamDemo {

	public static void main(String[] args) throws Exception {
		FileOutputStream fos = new FileOutputStream(new File("D://test123/bbb.txt"));
		PrintStream ps = new PrintStream(fos,false);
		String msg = "hi,hello world!";
		ps.write(msg.getBytes("UTF-8"));
		ps.write(msg.getBytes("UTF-8"));
		ps.close();
		fos.close();
	}

}
