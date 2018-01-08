package org.dolphin.commons.jdk.jdk_1_5_0.boxing;
/**
 * jdk1.5
 * 自动装箱与拆箱
 * @author yaokai
 *
 */
public class AutoboxingDemo {

	public static void main(String[] args) {
		//自动装箱
		Integer a1 = 200;
		Integer a2 = 200;
		//false
		//分析:根据源码valueOf值大于等于-128小于等于127返回指向IntegerCache.cache中已经存在的对象的引用；否则创建一个新的Integer对象
		System.out.println("(a1==a2)="+(a1==a2));
		
		Integer a3 = 100;
		Integer a4 = 100;
		//true
		System.out.println("(a3==a4)="+(a3==a4));
		
		Integer a5 = new Integer(100);
		Integer a6 = new Integer(100);
		//false
		System.out.println("(a5==a6)="+(a5==a6));
//		Integer b1 = 1;
//		Integer b2 = 2;
//		Long b3 = 3L;
		//Incompatible operand types Integer and Long
//		System.out.println(b1==b3);

		
		
//		Integer a1 = 1;
//		Integer a2 = 2;
//		Long a3 = 3L;
		//false
		//分析:根据源码equals不会进行类型转换，类型不一致直接返回false
		//b1和b2先进行拆箱，计算，装箱为Integer,然后equals
//		System.out.println(a3.equals(a1+a2));
		
		
	     /**
          D:\ceshi>javac Hello.java
	      

	      D:\ceshi>javap -c Hello.class
	      Compiled from "Hello.java"
	      public class Hello {
	        public Hello();
	          Code:
	             0: aload_0
	             1: invokespecial #1                  // Method java/lang/Object."<init>":
	      ()V
	             4: return

	        public static void main(java.lang.String[]);
	          Code:
	             0: iconst_1
	             1: invokestatic  #2                  // Method java/lang/Integer.valueOf:     //装箱a1
	      (I)Ljava/lang/Integer;
	             4: astore_1
	             5: iconst_2
	             6: invokestatic  #2                  // Method java/lang/Integer.valueOf:     //装箱a2
	      (I)Ljava/lang/Integer;
	             9: astore_2
	            10: ldc2_w        #3                  // long 3l
	            13: invokestatic  #5                  // Method java/lang/Long.valueOf:(J)     //装箱a3
	      Ljava/lang/Long;
	            16: astore_3
	            17: getstatic     #6                  // Field java/lang/System.out:Ljava/
	      io/PrintStream;
	            20: aload_3
	            21: aload_1
	            22: invokevirtual #7                  // Method java/lang/Integer.intValue     //a1拆箱
	      :()I 
	            25: aload_2
	            26: invokevirtual #7                  // Method java/lang/Integer.intValue     //a2拆箱  
	      :()I
	            29: iadd
	            30: invokestatic  #2                  // Method java/lang/Integer.valueOf:     //求和然后装箱
	      (I)Ljava/lang/Integer;
	            33: invokevirtual #8                  // Method java/lang/Long.equals:(Lja     //equals
	      va/lang/Object;)Z
	            36: invokevirtual #9                  // Method java/io/PrintStream.printl
	      n:(Z)V
	            39: return
	      }

*/
		
		Integer b1 = 1;
		Integer b2 = 2;
		Long b3 = 3L;
		//true
		/**
		 * b1,b2拆箱求和，b3拆箱
		 * 数值比较
		 */
		System.out.println((b1+b2)==b3);
		
	}

}
