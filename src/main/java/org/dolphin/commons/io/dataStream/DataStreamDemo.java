package org.dolphin.commons.io.dataStream;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * 数据流(方便操作java数据类型)
 * DataInputStream
 * DataOutputStream
 * @author yaokai
 *
 */
public class DataStreamDemo {

	public static void main(String[] args) throws Exception {
		DataInputStream dis = new DataInputStream(new FileInputStream("D:"+File.separator+"test.txt"));
		DataOutputStream dos = new DataOutputStream(new FileOutputStream("D:"+File.separator+"test.txt"));
		dos.writeBoolean(true);
		dos.writeDouble(100.00d);
		System.out.println("boolean:"+dis.readBoolean()+",double:"+dis.readDouble());
		dos.close();
		dis.close();
	}

}
