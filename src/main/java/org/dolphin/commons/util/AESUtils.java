package org.dolphin.commons.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
/**
 * AES加密和解密
 * @author yaokai
 *
 */
public class AESUtils {
	private static final String CHARSET_NAME = "UTF-8";
	// iv偏移量，ECB模式不需要填写
	private static final String IV_STRING = "16-Bytes--String";
	/**
	 * 生成AES key
	 * @param password
	 * @return
	 */
	public static String generateAESKey(String password){
		if(StringUtils.isEmpty(password)){
			return null;
		}
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, new SecureRandom(password.getBytes(CHARSET_NAME)));
			SecretKey secretKey = kgen.generateKey();
			byte[] encodeFormat = secretKey.getEncoded();
			return Base64.encodeBase64String(encodeFormat);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 加密(AES/CBC/PKCS5Padding)
	 * 
	 * @param content
	 * @param key
	 * @return
	 */
	public static String encryptBase64String(String content, String key) {
		if (StringUtils.isEmpty(content) || StringUtils.isEmpty(key)) {
			return null;
		}
		try {
			byte[] byteContent = content.getBytes(CHARSET_NAME);
			byte[] encodeFormat = key.getBytes(CHARSET_NAME);
			SecretKeySpec secretKeySpec = new SecretKeySpec(encodeFormat, "AES");
			byte[] initParam = IV_STRING.getBytes(CHARSET_NAME);
			IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);
			// 指定加密的算法、工作模式和填充方式
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
			byte[] encryptedBytes = cipher.doFinal(byteContent);
			// 同样对加密后数据进行 base64 编码
			return Base64.encodeBase64String(encryptedBytes);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解密(AES/CBC/PKCS5Padding)
	 * 
	 * @param content
	 * @param key
	 * @return
	 */
	public static String decrypt(String content, String key) {
		if (StringUtils.isEmpty(content) || StringUtils.isEmpty(key)) {
			return null;
		}
		try {
			byte[] encrytBytes = Base64.decodeBase64(content);
			byte[] decodeFormat = key.getBytes(CHARSET_NAME);
			SecretKeySpec secretKeySpec = new SecretKeySpec(decodeFormat, "AES");
			byte[] initParam = IV_STRING.getBytes(CHARSET_NAME);
			IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
			byte[] result = cipher.doFinal(encrytBytes);
			return new String(result, CHARSET_NAME);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		String key = generateAESKey("1234567890123456");
//		String encodeStr = encryptBase64String("美丽心情123!@#$%^&*()_-`~helloworld", key);
//		System.out.println("加密后->"+encodeStr);
		String decodeStr = decrypt("488fRWkqjJxDJKmEdF9lSg==", "1234567890123456");
		System.out.println("解密后->"+decodeStr);
	}

}
