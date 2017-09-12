package org.dolphin.commons.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

public class AESUtils {

	public static byte[] encrypt(String content,String password){
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, new SecureRandom(password.getBytes("utf-8")));
			SecretKey secretKey = kgen.generateKey();
			byte[] encodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(encodeFormat,"AES");
			Cipher cipher = Cipher.getInstance("AES");
			byte[] byteContent = content.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] result = cipher.doFinal(byteContent);
			return result;
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static byte[] decrypt(byte[] cotent,String password){
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128,new SecureRandom(password.getBytes("utf-8")));
			SecretKey secretKey = kgen.generateKey();
			byte[] decodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(decodeFormat,"AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] result = cipher.doFinal(cotent);
			return result;
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) throws Exception {
		String content = "美丽心情123456helloworld";
		String password = "hello";
		byte[] encryptResult = encrypt(content,password);
		//转16进制字符串
		String hex = Hex.encodeHexString(encryptResult);
		System.out.println(hex);
		
		byte[] encryptResult2 = Hex.decodeHex(hex.toCharArray());
		byte[] decryptResult = decrypt(encryptResult2,password);
		System.out.println(new String(decryptResult));
	}

}
