package org.dolphin.commons.io.randomAccessFile;

import java.io.File;
import java.io.RandomAccessFile;
/**
 * 随机读写文件(可以执行指针读写位置)
 * @author yaokai
 *
 */
public class RandomAccessFileDemo {

	public static void main(String[] args) throws Exception {
		File file = new File("D:"+File.separator+"test.txt");
		RandomAccessFile random = new RandomAccessFile(file,"rw");
//		String name = "zhangsan";
//		byte[] buf = name.getBytes("UTF-8");
//		random.write(buf, 0, buf.length);
//		String line = random.readLine();
//		System.out.println(line);
		random.seek(3);
		byte[] buf1 = new byte[1024];
		random.read(buf1);
		System.out.println(new String(buf1));
		random.close();
	}

}
