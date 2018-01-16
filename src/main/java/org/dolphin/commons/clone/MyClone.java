package org.dolphin.commons.clone;

public class MyClone implements Cloneable {
	private int a;
	@Override
	protected Object clone() throws CloneNotSupportedException {
		MyClone myClone = null;
	    try{
	    	myClone = (MyClone)super.clone();
	    }catch(CloneNotSupportedException e){
	    	e.printStackTrace();
	    }
		return myClone;
	}
    public static void main(String[] args){
    	MyClone o = new MyClone();
    	o.a = 100;
    	MyClone o2 = null;
    	try {
			o2 = (MyClone)o.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
    	//100
    	System.out.println(o2.a);
    	o2.a = 101;
    	//100
    	System.out.println(o.a);
    	//101
    	System.out.println(o2.a);
    }
}
