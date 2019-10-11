package com.saas.framework.enumer;

import com.fasterxml.jackson.annotation.JsonValue;

public interface IEnum {

	Integer getValue();

	@JsonValue
	String getText();
}
