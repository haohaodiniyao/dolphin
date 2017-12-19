package org.dolphin.commons.io.objectStream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
/**
 * ObjectOutputStream
 * ObjectInputStream
 * @author yaokai
 *
 */
public class ObjectStreamDemo {
	public static void main(String[] args) throws Exception{
		//对象序列化写到物理文件中
		FileOutputStream fos = new FileOutputStream("D://test123/789.txt");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		for(int i=0;i<5;i++){
			Student s = new Student(i,"name"+i);
			oos.writeObject(s);
		}
		oos.close();
		fos.close();
		//对象反序列化从物理文件中读取
		FileInputStream fis = new FileInputStream("D://test123/789.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);
		for(int i=0;i<5;i++){
			Student s = (Student)ois.readObject();
			System.out.println(s);
		}
		ois.close();
		fis.close();
	}
}
