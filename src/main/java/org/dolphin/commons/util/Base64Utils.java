package org.dolphin.commons.util;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;

public class Base64Utils {
	private static final String CHARSET_NAME = "UTF-8";

	public static String encodeString(String strData) throws UnsupportedEncodingException {
		if (strData == null)
			return null;
		return new String(Base64.encodeBase64(strData.getBytes(CHARSET_NAME)), CHARSET_NAME);
	}

	public static String decodeString(String strData) throws UnsupportedEncodingException {
		if (strData == null)
			return null;
		return new String(Base64.decodeBase64(strData.getBytes(CHARSET_NAME)), CHARSET_NAME);
	}
}
