package org.dolphin.commons.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
/**
 * MD5加密
 * @author yaokai
 *
 */
public class MD5Utils {
	private static final String KEY_MD = "MD5";
	public static String md5Hex(String inputStr){
		if(StringUtils.isEmpty(inputStr)){
			return null;
		}
//		try {
//			MessageDigest md = MessageDigest.getInstance(KEY_MD);
//			byte[] inputByte = inputStr.getBytes("utf-8");
//			md.update(inputByte);
//			byte[] hashCode = md.digest();
//			StringBuffer sb = new StringBuffer();
//			for(byte b:hashCode){
//				sb.append(Character.forDigit(b>>4&0xf, 16));
//				sb.append(Character.forDigit(b&0xf, 16));
//			}
//			return sb.toString();
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
		
		return DigestUtils.md5Hex(inputStr);
	}
	public static void main(String[] args){
		//8468d8ad1d4a6bb49666cf2a71ae88e5
		System.out.println(md5Hex("美丽心情"));
	}
}
