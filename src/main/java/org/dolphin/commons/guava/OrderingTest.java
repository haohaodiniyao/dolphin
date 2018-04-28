package org.dolphin.commons.guava;

import java.util.List;

import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.common.collect.Lists;
import org.apache.curator.shaded.com.google.common.collect.Ordering;

public class OrderingTest {

	public static void main(String[] args) {
		AbstractFactoryBean
//		List<String> list = Lists.newArrayList();
//		list.add("ABCF");
//		list.add("784-5735353389");
//		list.add("784-5735353386");
//		list.add("ABCD");
//		Ordering<String> natural = Ordering.natural();
//		list = natural.sortedCopy(list);
//		System.out.println(list);
//		for(String str:list){
//			System.out.println(str);
//		}
		try {
			preconditionsTestMethod();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static void preconditionsTestMethod() {
		Preconditions.checkNotNull(null);
	}

}
