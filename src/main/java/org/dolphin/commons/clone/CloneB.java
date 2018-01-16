package org.dolphin.commons.clone;

public class CloneB implements Cloneable {
	private CloneA cloneA = new CloneA(100);

	@Override
	protected Object clone() throws CloneNotSupportedException {
		CloneB cloneB = null;
		try{
			cloneB = (CloneB)super.clone();
		}catch(CloneNotSupportedException e){
			e.printStackTrace();
		}
		return cloneB;
	}
	
	public CloneA getCloneA() {
		return cloneA;
	}

	public void setCloneA(CloneA cloneA) {
		this.cloneA = cloneA;
	}

	public static void main(String[] args){
		CloneB cloneB = new CloneB();
		System.out.println(cloneB.getCloneA().getA());
		try {
			CloneB cloneB2 = (CloneB)cloneB.clone();
			cloneB.cloneA.setA(101);
			System.out.println(cloneB2.getCloneA().getA());
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}
}
