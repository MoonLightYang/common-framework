package com.saas.framework.enumer;

import com.fasterxml.jackson.annotation.JsonValue;

public interface IEnum {

	@JsonValue
	Integer getValue();

	String getText();
}
