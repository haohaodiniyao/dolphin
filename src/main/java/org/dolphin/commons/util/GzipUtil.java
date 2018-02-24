package org.dolphin.commons.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GzipUtil {
	public static byte[] uncompress(byte[] b) throws IOException {
		InputStream is = new ByteArrayInputStream(b);
		GZIPInputStream gin = new GZIPInputStream(is);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buf = new byte[1024];
		int len;
		while ((len = gin.read(buf)) > 0) {
			out.write(buf, 0, len);
		}
		gin.close();
		out.close();
		return out.toByteArray();
	}

	public static byte[] compress(byte[] b) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		GZIPOutputStream gos = new GZIPOutputStream(baos);
		gos.write(b);
		gos.close();
		return baos.toByteArray();
	}

	public static void main(String[] args) throws IOException {
		String str = "你好，世界，hello world";
		byte[] b = compress(str.getBytes("UTF-8"));
		System.out.println(new String(uncompress(b)));
	}
}
