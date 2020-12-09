package com.saas.framework.view;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OptionsPlus {

	protected String text;
	protected Integer value;
	protected Integer direction;

	public OptionsPlus(String text, Integer value, Integer direction) {
		this.text = text;
		this.value = value;
		this.direction = direction;
	}
}
