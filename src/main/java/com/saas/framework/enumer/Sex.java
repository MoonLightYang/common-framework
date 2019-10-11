package com.saas.framework.enumer;

public enum Sex implements IEnum {

	UNKNOW(0, "未知"), MEAL(1, "男"), FEMAL(2, "女");

	private int value;

	private String text;

	Sex(int value, String text) {
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
