package com.saas.framework.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OptionsChecked {
	
	private Integer menuId;
	protected String text;
	protected Integer value;
	protected Integer checked = 1; // 1：选中，0：未选中

}
