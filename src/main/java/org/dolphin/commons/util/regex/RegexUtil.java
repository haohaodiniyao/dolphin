package org.dolphin.commons.util.regex;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class RegexUtil {
	public static boolean checkIP(String ip){
		String regex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
		if(StringUtils.isEmpty(ip)){
			return false;
		}
		if(!Pattern.matches(regex, ip)){
			return false;
		}
		return true;
	}
	
	public static boolean checkURL(String url){
		if(StringUtils.isEmpty(url) || StringUtils.isBlank(url)){
			return false;
		}
		try {
			new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
