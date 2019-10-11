package com.saas.framework.view;

import java.util.ArrayList;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class TreeSelect extends Select {

	@Getter
	@Setter
	private ArrayList<Select> childs;

}
