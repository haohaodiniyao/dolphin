package org.dolphin.commons.config;

public class RemotingConfig {
	private String url;
	private String remotingCode;
	private String method;
	private byte remotingType;
	private boolean isBase64;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getRemotingCode() {
		return remotingCode;
	}
	public void setRemotingCode(String remotingCode) {
		this.remotingCode = remotingCode;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public byte getRemotingType() {
		return remotingType;
	}
	public void setRemotingType(byte remotingType) {
		this.remotingType = remotingType;
	}
	public boolean isBase64() {
		return isBase64;
	}
	public void setBase64(boolean isBase64) {
		this.isBase64 = isBase64;
	}
	
}
