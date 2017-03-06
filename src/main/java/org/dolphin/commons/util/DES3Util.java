package com.tuniu.ngsp.ddg.util.threedes;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tuniu.ngsp.ddg.manager.data.Version;
import com.tuniu.ngsp.ddg.util.md5.MD5Util;

public class DES3Util {
	private static final Logger log = LoggerFactory.getLogger(DES3Util.class);
	// 鍒ゆ柇鏄惁鍔犲瘑鏍囪瘑
	public static final String ENCRYPT_MATCH_KEY = "_des3";
	// 鍔犲瘑IV 鍙傛暟
	public static final byte[] KEY_IV = { 1, 2, 3, 4, 5, 6, 7, 8 };
	// 绠楁硶鍚嶇О
	private static final String KEY_ALGORITHM = "desede";
	// 绠楁硶鍚嶇О/鍔犲瘑妯″紡/濉厖鏂瑰紡
	private static final String CIPHER_ALGORITHM = "desede/CBC/PKCS5Padding";

	/**
	 * 鐢熸垚KEY
	 * 
	 * @param versionVo
	 * @param f
	 * @return
	 */
	public static String generateKey(Version versionVo, String fileName) {
		String key=MD5Util.MD5(versionVo.toString() + fileName);
		if(key.length()<48){
			int num=48-key.length();
			for(int i=0;i<num;i++){
				key=key+"0";
			}
		}
		return key;
	}

	/**
	 * CBC鍔犲瘑
	 * 
	 * @param key
	 *            瀵嗛挜
	 * @param KEY_IV
	 *            IV
	 * @param data
	 *            鏄庢枃
	 * @return Base64缂栫爜鐨勫瘑鏂�
	 * @throws Exception
	 */
	public static byte[] des3EncodeCBC(String key, byte[] keyiv, byte[] data)
			throws Exception {
		Cipher cipher = initDES3EncrType(key, keyiv);
		byte[] bOut = cipher.doFinal(data);
		return bOut;
	}

	/**
	 * CBC瑙ｅ瘑
	 * 
	 * @param key
	 *            瀵嗛挜
	 * @param KEY_IV
	 *            IV
	 * @param data
	 *            Base64缂栫爜鐨勫瘑鏂�
	 * @return 鏄庢枃
	 * @throws Exception
	 */
	public static byte[] des3DecodeCBC(String key, byte[] keyiv, byte[] data)
			throws Exception {
		Cipher cipher = initDES3DecrType(key, keyiv);
		byte[] bOut = cipher.doFinal(data);
		return bOut;
	}

	/**
	 * 瀵规枃浠惰繘琛屽姞瀵�
	 * 
	 * @param sourceFile
	 * @param sKey
	 *            蹇呴』涓洪暱搴�>=48鐨勫瓧绗︿覆
	 * @param KEY_IV
	 *            蹇呴』涓洪暱搴�=8 鐨勫瓧鑺傛暟缁�
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptFile(File sourceFile, String sKey, byte[] keyiv)
			throws Exception {
		// 鏂板缓涓存椂鍔犲瘑鏂囦欢
		InputStream inputStream = null;
		ByteArrayOutputStream outputStream = null;
		try {
			inputStream = new FileInputStream(sourceFile);
			outputStream = new ByteArrayOutputStream(1024);
			Cipher cipher = initDES3EncrType(sKey, keyiv);
			// 浠ュ姞瀵嗘祦鍐欏叆鏂囦欢
			CipherInputStream cipherInputStream = new CipherInputStream(
					inputStream, cipher);
			byte[] buffer = new byte[1024];
			int nRead = 0;
			while ((nRead = cipherInputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, nRead);
				outputStream.flush();
			}
			cipherInputStream.close();
			return outputStream.toByteArray();
		} catch (Exception e) {
			log.error("銆恉dg-client銆戝姞瀵嗘枃浠跺け璐ワ紒", e);
			throw e;
		} finally {
			try {
				inputStream.close();
				outputStream.close();
			} catch (Exception e) {
				log.error("銆恉dg-client銆戝姞瀵嗘祦鍏抽棴澶辫触锛�", e);
			}
		}
	}

	public static void encryptFile(File sourceFile, File targetFile,
			String sKey, byte[] keyiv) throws Exception {
		// 鏂板缓涓存椂鍔犲瘑鏂囦欢
		InputStream inputStream = null;
		FileOutputStream outputStream = null;
		try {
			inputStream = new FileInputStream(sourceFile);
			outputStream = new FileOutputStream(targetFile);
			Cipher cipher = initDES3EncrType(sKey, keyiv);
			// 浠ュ姞瀵嗘祦鍐欏叆鏂囦欢
			CipherInputStream cipherInputStream = new CipherInputStream(
					inputStream, cipher);
			byte[] buffer = new byte[1024];
			int nRead = 0;
			while ((nRead = cipherInputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, nRead);
				outputStream.flush();
			}
			cipherInputStream.close();
		} catch (Exception e) {
			log.error("銆恉dg-client銆戝姞瀵嗘枃浠跺け璐ワ紒", e);
			throw e;
		} finally {
			try {
				inputStream.close();
				outputStream.close();
			} catch (Exception e) {
				log.error("銆恉dg-client銆戝姞瀵嗘祦鍏抽棴澶辫触锛�", e);
			}
		}
	}

	/**
	 * 瑙ｅ瘑鏂囦欢
	 * 
	 * @param sourceFile
	 * @param sKey
	 *            蹇呴』涓洪暱搴�>=48鐨勫瓧绗︿覆
	 * @param KEY_IV
	 *            蹇呴』涓洪暱搴�=8 鐨勫瓧鑺傛暟缁�
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptFile(File sourceFile, String sKey, byte[] keyiv)
			throws Exception {
		InputStream inputStream = null;
		ByteArrayOutputStream outputStream = null;
		try {
			Cipher cipher = initDES3DecrType(sKey, keyiv);
			inputStream = new FileInputStream(sourceFile);
			outputStream = new ByteArrayOutputStream(1024);
			CipherOutputStream cipherOutputStream = new CipherOutputStream(
					outputStream, cipher);
			byte[] buffer = new byte[1024];
			int nRead = 0;
			while ((nRead = inputStream.read(buffer)) >= 0) {
				cipherOutputStream.write(buffer, 0, nRead);
			}
			cipherOutputStream.close();
			return outputStream.toByteArray();
		} catch (Exception e) {
			log.error("銆恉dg-client銆戣В瀵嗘枃浠跺け璐ワ紒", e);
			throw e;
		} finally {
			try {
				inputStream.close();
				outputStream.close();
			} catch (IOException e) {
				log.warn("銆恉dg-client銆戣В瀵嗘祦鍏抽棴澶辫触锛�", e);
			}
		}
	}

	/**
	 * 
	 * 鐢熸垚瀵嗛挜key瀵硅薄
	 * 
	 * @param KeyStr
	 *            瀵嗛挜瀛楃涓�
	 * @return 瀵嗛挜瀵硅薄
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws Exception
	 */
	private static Key keyGenerator(String keyStr) throws Exception {
		byte input[] = HexString2Bytes(keyStr);
		DESedeKeySpec KeySpec = new DESedeKeySpec(input);
		SecretKeyFactory KeyFactory = SecretKeyFactory
				.getInstance(KEY_ALGORITHM);
		return ((Key) (KeyFactory
				.generateSecret(((java.security.spec.KeySpec) (KeySpec)))));
	}

	private static int parse(char c) {
		if (c >= 'a')
			return (c - 'a' + 10) & 0x0f;
		if (c >= 'A')
			return (c - 'A' + 10) & 0x0f;
		return (c - '0') & 0x0f;
	}

	// 浠庡崄鍏繘鍒跺瓧绗︿覆鍒板瓧鑺傛暟缁勮浆鎹�
	public static byte[] HexString2Bytes(String hexstr) {
		byte[] b = new byte[hexstr.length() / 2];
		int j = 0;
		for (int i = 0; i < b.length; i++) {
			char c0 = hexstr.charAt(j++);
			char c1 = hexstr.charAt(j++);
			b[i] = (byte) ((parse(c0) << 4) | parse(c1));
		}
		return b;
	}

	private static Cipher initDES3EncrType(String sKey, byte[] keyiv) {
		Cipher cipher = null;
		try {
			Security.addProvider(new BouncyCastleProvider());
			Key deskey = keyGenerator(sKey);
			cipher = Cipher.getInstance(CIPHER_ALGORITHM);
			IvParameterSpec ips = new IvParameterSpec(keyiv);
			cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
			return cipher;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cipher;
	}

	private static Cipher initDES3DecrType(String sKey, byte[] keyiv) {
		Cipher cipher = null;
		try {
			Key deskey = keyGenerator(sKey);
			cipher = Cipher.getInstance(CIPHER_ALGORITHM);
			IvParameterSpec ips = new IvParameterSpec(keyiv);
			cipher.init(Cipher.DECRYPT_MODE, deskey, ips);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cipher;
	}
}
