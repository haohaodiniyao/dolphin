package org.dolphin.commons.reflect;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.dolphin.commons.httpclient.RestWrapper;

public class ReflectUtils {

	public static void main(String[] args) throws Exception{
		RestWrapper restWrapper = new RestWrapper();
		test(restWrapper,"method","method");
		test(restWrapper,"paramSource","paramSource");
		System.out.println(restWrapper);
	}

	
	private static String upperCaseStr(String str){
		if(StringUtils.isNotEmpty(str)){
			return str.substring(0,1).toUpperCase()+str.substring(1);
		}
		return "";
	}
	private static void test(RestWrapper restWrapper,String fieldName,String fieldValue) throws Exception{
		String field= upperCaseStr(fieldName);
		System.out.println(MethodUtils.invokeMethod(restWrapper, "get"+field));
		MethodUtils.invokeMethod(restWrapper, "set"+field,fieldValue);
	}
}
