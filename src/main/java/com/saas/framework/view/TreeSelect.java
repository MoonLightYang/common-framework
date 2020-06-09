package com.saas.framework.view;

import java.util.ArrayList;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class TreeSelect extends Options {

	@Getter
	@Setter
	private ArrayList<Options> childs;

}
