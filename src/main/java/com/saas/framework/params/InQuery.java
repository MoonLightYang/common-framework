package com.saas.framework.params;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InQuery extends SuperParams {

	private List<Integer> items;

}
