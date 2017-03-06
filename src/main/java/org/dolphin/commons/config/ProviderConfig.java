package org.dolphin.commons.config;

import java.util.List;

public class ProviderConfig {
	private String address;
	private String code;
	private String name;
	private String basePath;
	private List<RemotingConfig> remotingConfigs;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBasePath() {
		return basePath;
	}
	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}
	public List<RemotingConfig> getRemotingConfigs() {
		return remotingConfigs;
	}
	public void setRemotingConfigs(List<RemotingConfig> remotingConfigs) {
		this.remotingConfigs = remotingConfigs;
	}
	
}
