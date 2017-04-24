package org.dolphin.commons.util.constants;

public enum ApprovalStatus {
	OFFLINE(0,"离线"),ONLINE(1,"在线"),UNKNOW(-1,"未知");
	private int value;
	private String desc;
	
	private ApprovalStatus(int value, String desc) {
		this.value = value;
		this.desc = desc;
	}
	
	public static int getValue(String desc){
		for(ApprovalStatus instance:values()){
			if(instance.getDesc().equals(desc)){
				return instance.getValue();
			}
		}
		return ApprovalStatus.UNKNOW.getValue();
	}
	public static String getDesc(int value){
		for(ApprovalStatus instance:values()){
			if(instance.getValue() == value){
				return instance.getDesc();
			}
		}
		return ApprovalStatus.UNKNOW.getDesc();
	}	
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
}
