package com.saas.framework.enumer;

public enum YesOrNo implements IEnum {

	NO(0, "否"), YES(1, "是");

	private int value;

	private String text;

	YesOrNo(int value, String text) {
		this.value = value;
		this.text = text;
	}

	public Integer getValue() {
		return value;
	}

	public String getText() {
		return text;
	}

}
