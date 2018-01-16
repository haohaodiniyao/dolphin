package org.dolphin.commons.clone;

public class CloneA implements Cloneable{
	private int a;

	public CloneA(int a) {
		this.a = a;
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		CloneA cloneA = null;
		try{
			cloneA = (CloneA)super.clone();
		}catch(CloneNotSupportedException e){
			e.printStackTrace();
		}
		return cloneA;
	}
	
}
