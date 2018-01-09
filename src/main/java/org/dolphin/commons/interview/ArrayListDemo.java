package org.dolphin.commons.interview;

import java.util.HashMap;
import java.util.Map;

public class ArrayListDemo {

	public static void main(String[] args) {

//		ArrayList<Integer> list = new ArrayList<>();
//		for(int i=0;i<10;i++){
//			list.add(i);
//		}
//		for(int i=0;i<5;i++){
//			list.add(i);
//		}
//		list.add(1);
//		System.out.println(list);
		HashSet
		Map<Integer,Integer> map  = new HashMap<>();
		for(int i=0;i<16;i++){
			map.put(i, i);
		}
		for(int i=16;i<32;i++){
			map.put(i, i);
		}
		map.put(32, 32);
		System.out.println(map);
	}

}
