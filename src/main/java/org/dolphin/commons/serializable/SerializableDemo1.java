package org.dolphin.commons.serializable;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
 
import java.io.*;
/**
 * https://github.com/hollischuang/java-demo/tree/master/src/main/java/com/hollischaung/serialization/SerializableDemos
 * http://www.cnblogs.com/xdp-gacl/p/3777987.html
 * 一个类要想被序列化必须实现Serializable接口
 */
public class SerializableDemo1 {
 
    public static void main(String[] args) {
//        User1 user = new User1();
//        user.setName("haohaodiniyao");
//        user.setAge(28);
// 
//        //Write Obj to File
//        ObjectOutputStream oos = null;
//        try {
//            System.out.println("开始序列化："+user);
//            oos = new ObjectOutputStream(new FileOutputStream("D:/person.txt"));
//            oos.writeObject(user);
//            System.out.println("序列化成功!");
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            IOUtils.closeQuietly(oos);
//        }
 
        //Read Obj from File
        File file = new File("D:/person.txt");
        ObjectInputStream ois = null;
        try {
            System.out.println("开始反序列化");
            ois = new ObjectInputStream(new FileInputStream(file));
            User1 newUser = (User1) ois.readObject();
            System.out.println("反序列化成功："+newUser);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(ois);
            try {
            	//删除文件
                FileUtils.forceDelete(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
 
    }
}