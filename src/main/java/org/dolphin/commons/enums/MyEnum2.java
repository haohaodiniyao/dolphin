package org.dolphin.commons.enums;

public enum MyEnum2 {
	RED("红色",1),
	GREEN("绿色",2),
	BLANK("白色",3),
	YELLOW("黄色",4);
	private String name;
	private int index;
	private MyEnum2(String name, int index) {
		this.name = name;
		this.index = index;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public static String getName(int index){
		for(MyEnum2 myEnum2:MyEnum2.values()){
			if(myEnum2.getIndex() == index){
				return myEnum2.getName();
			}
		}
		return null;
	}
	@Override
	public String toString(){
		return this.index+"-"+this.name;
	}
}
