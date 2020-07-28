package com.saas.framework.params;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InQuery extends IdVersion {

	private List<Integer> items;

	public InQuery(List<Integer> items, Integer enterpriseId) {
		this.items = items;
		this.enterpriseId = enterpriseId;
	}

}
