package org.dolphin.commons.io;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;

/**
 * common-io test
 * @author yaokai
 *
 */
public class CommonsIOTest {

	public static void main(String[] args) throws IOException {
		//移动源目录到目标目录，并且创建目标目录
//		FileUtils.moveToDirectory(new File("D://test-dir/hello123.txt"), new File("D://test-dir123"), true);
		//移动源目录文件到目标目录，并且创建目标目录
//		FileUtils.moveFileToDirectory(new File("D://test-dir/hello123.txt"), new File("D://test-dir123"), true);
		//移动源文件到目标文件，并且删除源文件
//		FileUtils.moveFile(new File("D://test-dir/hello123.txt"), new File("D://test-dir/hello456.txt"));
		//移动源目录及其目录下的文件到目标目录，并且创建目标目录
//		FileUtils.moveDirectoryToDirectory(new File("D://test-dir/"), new File("D://test-dir123/"), true);
		//移动源目录及其目录下文件到目标目录，并且创建目标目录
//		FileUtils.moveDirectory(new File("D://test123/test"), new File("D://test123/test456"));
		//checksum 文件校验和
		//222957957
		//3720244545
		//http://blog.csdn.net/zjli321/article/details/74908451
//		System.out.println(FileUtils.checksumCRC32(new File("D://vue.min.js")));
		//文件长度
//		System.out.println(FileUtils.sizeOf(new File("D://test123/test456/123.txt")));
		//创建父目录
//		FileUtils.forceMkdirParent(new File("D://testaaa/testbbb"));
		//递归创建目录
//		FileUtils.forceMkdir(new File("D://testaaa/testbbb/testccc"));
		//删除存在的根目录或者文件
//		FileUtils.forceDeleteOnExit(new File("D://testaaa"));
		//删除存在的根目录或者文件(只能删除叶子文件和叶子文件夹)
//		FileUtils.forceDelete(new File("D://testaaa/testbbb/123.txt"));
//		List<String> lines = new ArrayList<>();
//		for(int i=0;i<5;i++){
//			lines.add("hello world aaa"+i);
//		}
		//创建文件并且按行写入文件中(覆盖之前的内容)
//		FileUtils.writeLines(new File("D://testaaa/testbbb/123.txt"), lines);
		//累加写入文件中，不覆盖之前内容
//		FileUtils.writeLines(new File("D://testaaa/testbbb/123.txt"), lines,true);
		//设置写入行结束字符lineEnding
//		FileUtils.writeLines(new File("D://testaaa/testbbb/123.txt"), lines,"####");
		//指定编码
//		FileUtils.writeLines(new File("D://testaaa/testbbb/123.txt"), "UTF-8", lines);
//		FileUtils.writeLines(new File("D://testaaa/testbbb/123.txt"), lines, "$$$", true);
		//读文件为字节数组
//		byte[] bytes = FileUtils.readFileToByteArray(new File("D://testaaa/testbbb/123.txt"));
		//将字节数组写入文件，累加，如果文件及其父目录不存在，将被创建
//		FileUtils.writeByteArrayToFile(new File("D://testaaa/testbbb/456.txt"), bytes,true);
//		int len = bytes.length;
		//len=185
		//分段将字节数组写入文件
//		FileUtils.writeByteArrayToFile(new File("D://testaaa/testbbb/456.txt"), bytes,0,92,true);
//		FileUtils.writeByteArrayToFile(new File("D://testaaa/testbbb/456.txt"), bytes,92,93,true);
		//字符串写入文件
//		FileUtils.write(new File("D://testaaa/testbbb/456.txt"), "hi,hello world!", "UTF-8", true);
		//行遍历读取文件
//		LineIterator lineIterator = FileUtils.lineIterator(new File("D://testaaa/testbbb/456.txt"),"UTF-8");
//		while(lineIterator.hasNext()){
//			System.out.println(lineIterator.nextLine());
//		}
//		List<String> lineList = FileUtils.readLines(new File("D://testaaa/testbbb/456.txt"),"UTF-8");
//		for(String str:lineList){
//			System.out.println(str);
//		}
		
		//hi,hello world 111!
		//hi,hello world 222!
		//hi,hello world 111!
		//hi,hello world 222!
//		String str = FileUtils.readFileToString(new File("D://testaaa/testbbb/456.txt"),"UTF-8");
//		System.out.println(str);
		//阻塞检测文件是否存在，存在后立刻返回true
//		boolean isExist = FileUtils.waitFor(new File("D://testaaa/testbbb/789.txt"),100);
//		System.out.println(isExist);
		//清理目录，此目录下不能有其他目录，只能有文件
//		FileUtils.deleteDirectory(new File("D://testaaa"));
		//指定目录是否包含指定目录或者文件
//		System.out.println(FileUtils.directoryContains(new File("D://test123"), new File("D://testaaa/testbbb/testccc")));
		//删除文件或者文件夹及其子目录
//		FileUtils.deleteQuietly(new File("D://testaaa"));
		//递归删除文件夹，如果文件夹下面有文件
//		FileUtils.deleteDirectory(new File("D://testaaa"));
//		InputStream is = new FileInputStream("D://testaaa/testbbb/123.txt");
//		FileUtils.copyToFile(is, new File("D://testaaa/testbbb/456.txt"));
		//URL网页输入流写入文件
//		FileUtils.copyURLToFile(new URL("http://www.baidu.com"), new File("D://testaaa/testbbb/789.txt"));
//		FileUtils.copyDirectory(new File("D://testaaa"), new File("D://testaaa/testeee"),new FileFilter(){
//
//			@Override
//			public boolean accept(File pathname) {
//				if(pathname.isFile()){
//					return true;
//				}else{
//					return false;	
//				}
//			}
//			
//		},false);
		//遍历指定目录，指定后缀文件列表
//		Iterator<File> iterators = FileUtils.iterateFiles(new File("D://test123"), new String[]{"txt","vsd"}, true);
//		while(iterators.hasNext()){
//			System.out.println(iterators.next().getPath());
//		}
		
//		FileUtils.touch(new File("D://test123/test456/hello.txt"));
//		System.out.println(FileUtils.getTempDirectoryPath());
	}

}
