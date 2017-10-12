package org.dolphin.commons.enums;

import org.junit.Test;
/**
 * switch注意break
 * switch支持 byte short int String enum char
 * @author yaokai
 *
 */
public class EnumTest {

	@Test
	public void test() {
//		MyEnum2 color = MyEnum2.GREEN;
//		switch(color){
//		case GREEN:
//			System.out.println(MyEnum2.GREEN);
//			break;
//		default:
//			System.out.println("default");	
//			break;
//		}
		
		char a = '2';
		switch(a){
			case '1':
				System.out.println(1);
				break;
			case '2':
				System.out.println(2);	
				break;
			default:
				System.out.println("default");
				break;
		}
	}

}
