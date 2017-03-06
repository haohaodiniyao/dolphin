package org.dolphin.commons.util;

package com.tuniu.ngsp.ddg.util.regex;

import java.net.URL;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.tuniu.ngsp.ddg.util.constants.ConfigType;
import com.tuniu.ngsp.ddg.util.threedes.DES3Util;

public class RegexUtils {

	/**
	 * 鏍￠獙ip
	 * 
	 * @param Ip
	 * @return
	 */
	public static boolean checkIp(String Ip) {
		String regEx = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
				+ "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
				+ "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
				+ "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
		if (StringUtils.isEmpty(Ip)) {
			return false;
		}
		if (!Pattern.matches(regEx, Ip)) {
			return false;
		}
		return true;
	}

	/**
	 * 鏍￠獙鏄惁鏄疷RL
	 * 
	 * @param url
	 * @return false 涓嶆槸 锛宼rue 鏄�
	 */
	public static boolean checkURL(String url) {
		if (StringUtils.isEmpty(url) || StringUtils.isBlank(url)) {
			return false;
		}
		try {
			new URL(url);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 鑾峰彇URL 涓殑鍩熷悕
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static URL getURL(String url) throws Exception {
		URL temp = null;
		try {
			temp = new URL(url);
		} catch (Exception e) {
			throw new Exception("鍩熷悕瑙ｆ瀽澶辫触,璇︾粏閿欒锛�" + e.getMessage());
		}
		if (checkIp(temp.getHost())) {
			throw new Exception("鏃犳硶浠嶶RL涓幏鍙栧煙鍚嶏紝璇RL涓槸IP");
		}
		return temp;
	}

	/**
	 * 鏍规嵁鏂囦欢鍚嶈幏鍙栨枃浠剁被鍨�
	 * 
	 * @param fileName
	 * @return
	 */
	public static ConfigType parseFileName(String fileName) {
		String regEx = String.format(ConfigType.GLOBAL.getMatchPattern(),
				ConfigType.GLOBAL.getPrefix(), DES3Util.ENCRYPT_MATCH_KEY,
				ConfigType.GLOBAL.getSubfix());
		if (Pattern.matches(regEx, fileName)) {
			return ConfigType.GLOBAL;
		}
		regEx = String.format(ConfigType.SYSTEM.getMatchPattern(),
				ConfigType.SYSTEM.getPrefix(), DES3Util.ENCRYPT_MATCH_KEY,
				ConfigType.SYSTEM.getSubfix());
		if (Pattern.matches(regEx, fileName)) {
			return ConfigType.SYSTEM;
		}
		regEx = String.format(ConfigType.JDBC.getMatchPattern(),
				ConfigType.JDBC.getPrefix(), DES3Util.ENCRYPT_MATCH_KEY,
				ConfigType.JDBC.getSubfix());
		if (Pattern.matches(regEx, fileName)) {
			return ConfigType.JDBC;
		}
		regEx = ConfigType.XML.getMatchPattern();
		if (Pattern.matches(regEx, fileName)) {
			return ConfigType.XML;
		}
		regEx = ConfigType.PROPERTIES.getMatchPattern();
		if (Pattern.matches(regEx, fileName)) {
			return ConfigType.PROPERTIES;
		}
		return null;
	}
}

