package org.dolphin.commons.jdk.jdk_1_5_0.generic;

import java.util.ArrayList;
import java.util.List;
/**
 * 泛型高级之通配符
 * @author yaokai
 *
 */
public class GenericDemo {

	
	//编译报错前后不一致
//	List<Object> list = new ArrayList<String>();
	//
	List<? extends Object> list = new ArrayList<String>();
	List<? super String> list2 = new ArrayList<Object>();
     
	public static void main(String[] args){
		System.out.println(0b11);
		System.out.println(0x11);
		System.out.println(011);
	}
}
