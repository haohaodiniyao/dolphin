package com.tuniu.ngsp.ddg.util.constants;

import com.tuniu.ngsp.ddg.util.threedes.DES3Util;


/**
 * 閰嶇疆绫诲瀷
 * 
 * @author zhouchangqing
 * 
 */
public enum ConfigType {
	// =====================浠ヤ笅閫氱敤銆佸姞瀵嗛厤缃�==common=true==isDes3=true==============
	GLOBAL(99, "global", ".properties", "%s(%s){0,1}%s", "閫氱敤閰嶇疆", true, false,
			"!", true), SYSTEM(0, "system", ".properties", "%s(%s){0,1}%s",
			"绯荤粺閰嶇疆", true, false, "!", true),
	// =======================浠ヤ笅閫氱敤闆嗗悎銆佸姞瀵嗛厤缃�==group=true==isDes3=true==========
	JDBC(98, "ddbc", ".properties", "%s(%s){0,1}%s", "鏁版嵁搴撻厤缃�", true, true, "!",
			true),
	// =====================浠ヤ笅闈為�氱敤閰嶇疆闈炲姞瀵嗛厤缃�==common=false==isDes3=false=======
	PROPERTIES(1, null, ".properties", ".*properties", "涓氬姟閰嶇疆prop", false,
			false, "!", false), XML(2, null, ".xml", ".*xml", "涓氬姟閰嶇疆xml", false,
			false, "!", false);
	private int value;
	private String prefix;
	private String subfix;
	private String desc;
	// 鏄惁鏄�氱敤閰嶇疆
	private boolean common;
	// 鏄惁鏄�氱敤缁勯厤缃柟寮�
	private boolean group;
	// 鍒嗗壊绗﹀彿
	private String separator;

	private String matchPattern;
	// 鏄惁閫氳繃des3鍔犲瘑DES3Util.class
	private boolean isDes3;

	private ConfigType(int value, String prefix, String subfix,
			String matchPattern, String desc, boolean common, boolean group,
			String separator, boolean isDes3) {
		this.value = value;
		this.prefix = prefix;
		this.subfix = subfix;
		this.desc = desc;
		this.matchPattern = matchPattern;
		this.common = common;
		this.group = group;
		this.separator = separator;
		this.isDes3 = isDes3;
	}

	/**
	 * 鑾峰彇鍔犲瘑鏂囦欢鍚�
	 * 
	 * @param type
	 * @return
	 */
	public static String getFileName(ConfigType type) {
		if (!type.isCommon()) {
			return "default" + type.getSubfix();
		}
		String fileName = type.getPrefix();
		if (type.isDes3()) {
			fileName = fileName + DES3Util.ENCRYPT_MATCH_KEY;
		}
		fileName = fileName + type.getSubfix();
		return fileName;
	}

	public static ConfigType valueOf(int value) {
		ConfigType[] instances = ConfigType.values();
		for (ConfigType instance : instances) {
			if (instance.value == value) {
				return instance;
			}
		}
		return SYSTEM;
	}

	public static ConfigType nameOf(String name) {
		ConfigType[] instances = ConfigType.values();
		for (ConfigType instance : instances) {
			if (instance.name().equals(name)) {
				return instance;
			}
		}
		return SYSTEM;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getSubfix() {
		return subfix;
	}

	public void setSubfix(String subfix) {
		this.subfix = subfix;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getMatchPattern() {
		return matchPattern;
	}

	public void setMatchPattern(String matchPattern) {
		this.matchPattern = matchPattern;
	}

	public boolean isCommon() {
		return common;
	}

	public void setCommon(boolean common) {
		this.common = common;
	}

	public boolean isGroup() {
		return group;
	}

	public void setGroup(boolean group) {
		this.group = group;
	}

	public String getSeparator() {
		return separator;
	}

	public void setSeparator(String separator) {
		this.separator = separator;
	}

	public boolean isDes3() {
		return isDes3;
	}

	public void setDes3(boolean isDes3) {
		this.isDes3 = isDes3;
	}

}
